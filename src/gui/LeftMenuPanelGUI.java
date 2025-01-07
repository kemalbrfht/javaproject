package gui;
import javax.swing.*;

import controllers.*;
import models.Table;
import models.Menu;

import java.awt.*;

public class LeftMenuPanelGUI extends JPanel {
    public LeftMenuPanelGUI(JFrame frame, RestaurantManagement restaurant, Menu menu, Table table) {
        // Main panel layout and properties
        setLayout(new BorderLayout());
        setBackground(new Color(51, 51, 51));

        // Top Menu Panel
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(new Color(51, 51, 51));

        // Menu Buttons
        menuPanel.add(createMenuButton("Change Table", "src/icons/change.png", e -> new TablesGUI(frame, restaurant, menu)));
        menuPanel.add(createMenuButton("Bill Note", "src/icons/notifications.png", e -> openBillNoteDialog(frame, restaurant, table)));
        menuPanel.add(createMenuButton("Kitchen", "src/icons/printer.png", e -> {
            JPanel kitchenPanel = new KitchenGUI(frame, restaurant, menu, table);
            JOptionPane.showMessageDialog(frame, kitchenPanel, "Kitchen Screen", JOptionPane.PLAIN_MESSAGE);
        }));

        // Bottom Panel (Independent Buttons)
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(new Color(51, 51, 51));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Top margin

        // "Payment" Button
        JButton btnPayment = new JButton("Payment");
        btnPayment.setPreferredSize(new Dimension(200, 50));
        btnPayment.setMaximumSize(new Dimension(200, 300));
        btnPayment.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnPayment.setBackground(new Color(153, 0, 0));
        btnPayment.setForeground(Color.WHITE);
        btnPayment.setFocusPainted(false);
        btnPayment.addActionListener(e -> handlePayment(frame, restaurant, menu, table));

        bottomPanel.add(Box.createVerticalGlue()); // Push buttons to the bottom
        bottomPanel.add(btnPayment);

        // Add Top and Bottom Panels to Main Panel
        add(menuPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Menu Button Creator
    private JButton createMenuButton(String text, String iconPath, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 100));
        button.setBackground(new Color(102, 102, 102));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.addActionListener(action);
        if (iconPath != null && !iconPath.isEmpty()) {
            ImageIcon icon = new ImageIcon(iconPath);
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(newImg));
        }
        return button;
    }

    // Payment Handling Logic
    private void handlePayment(JFrame frame, RestaurantManagement restaurant, Menu menu, Table table) {
        // Payment Method Selection
        String[] options = {"Credit Card", "Debit Card"};
        int paymentMethod = JOptionPane.showOptionDialog(
            frame,
            "Which payment method would you like to use?",
            "Payment Method",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );

        if (paymentMethod == -1) return; // Exit if no selection

        // Payment Confirmation
        int confirm = JOptionPane.showConfirmDialog(
            frame,
            "Payment will be made using " + options[paymentMethod] + ". Do you confirm?",
            "Payment Confirmation",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(frame, "Payment successful! Closing table.");
            restaurant.addToRevenue(table.calculateBill());
            table.closeTable();
            new TablesGUI(frame, restaurant, menu); // Return to main screen
        } else {
            JOptionPane.showMessageDialog(frame, "Payment canceled.");
        }
    }

    // Bill Note Dialog
    private void openBillNoteDialog(JFrame frame, RestaurantManagement restaurant, Table table) {
        JDialog dialog = new JDialog(frame, "Bill Note", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(frame);

        JTextArea textArea = new JTextArea(table.getBillNote());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(e -> {
            table.setBillNote(textArea.getText());
            JOptionPane.showMessageDialog(dialog, "Bill note saved!");
            dialog.dispose();
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnSave, BorderLayout.SOUTH);

        dialog.add(panel);
        dialog.setVisible(true);
    }
}