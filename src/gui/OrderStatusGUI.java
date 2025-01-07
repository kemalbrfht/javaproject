package gui;

import controllers.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import models.Table;
import models.Menu;
import models.Order;

public class OrderStatusGUI {
    private JPanel mainPanel;
    private JList<String> orderList;
    private DefaultListModel<String> listModel;
    private JLabel totalPriceLabel; // Field to display the total price

    public OrderStatusGUI(JFrame frame, RestaurantManagement restaurantManagement, Menu menu, Table table) {
        mainPanel = new JPanel(new BorderLayout());

        // Order List
        listModel = new DefaultListModel<>();
        orderList = new JList<>(listModel);
        orderList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(orderList);

        // "Delete" Button
        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> {
            int[] selectedIndices = orderList.getSelectedIndices(); // Get selected indices
            if (selectedIndices.length > 0) {
                for (int i = selectedIndices.length - 1; i >= 0; i--) {
                    table.getOrders().remove(selectedIndices[i]); // Remove selected orders
                }
                updateOrderList(table); // Update the list
                updateTotalPrice(table); // Update the total price

                JOptionPane.showMessageDialog(frame, "Selected orders have been deleted!");
            } else {
                JOptionPane.showMessageDialog(frame, "Please select an order to delete!");
            }
        });

        // Total Price Field
        totalPriceLabel = new JLabel("Total: 0.0 TL", SwingConstants.RIGHT);
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalPriceLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Order Panel
        JPanel orderPanel = new JPanel(new BorderLayout());
        orderPanel.add(listScrollPane, BorderLayout.CENTER);
        orderPanel.add(btnDelete, BorderLayout.SOUTH);

        // Main Panel
        mainPanel.add(new JLabel("Table " + table.getTableNo() + " - Order Status", SwingConstants.CENTER),
                BorderLayout.NORTH);
        mainPanel.add(orderPanel, BorderLayout.CENTER);
        mainPanel.add(totalPriceLabel, BorderLayout.SOUTH); // Add total price to the bottom of the main panel

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> orderList.repaint()); // Only repaint the list
            }
        }, 1000, 1000);

        // Initialize Order List
        updateOrderList(table);
        updateTotalPrice(table); // Display the initial total price
    }

    private void updateOrderList(Table table) {
        listModel.clear(); // Reset the list
        for (Order order : table.getOrders()) {
            String orderText = String.format("%-20s %10s %10.2f TL",
                    order.getCategory() + ": " + order.getProduct(), "", order.getPrice());
            listModel.addElement(orderText); // Add items to the list
        }

        orderList.setCellRenderer(new ListCellRenderer<String>() {
            private final DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

            @Override
            public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                Order order = table.getOrders().get(index);

                if (isSelected) {
                    renderer.setBackground(list.getSelectionBackground());
                    renderer.setForeground(list.getSelectionForeground());
                } else {
                    if (order.isPrepared()) {
                        renderer.setBackground(new Color(200, 250, 200));
                    } else {
                        renderer.setBackground(new Color(250, 230, 200));
                    }
                    renderer.setForeground(list.getForeground());
                }

                return renderer;
            }
        });

        orderList.revalidate();
        orderList.repaint();
    }

    private void updateTotalPrice(Table table) {
        double totalPrice = table.calculateBill();
        totalPriceLabel.setText("Total: " + totalPrice + " TL");
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}