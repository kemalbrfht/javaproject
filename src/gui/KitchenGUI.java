package gui;

import controllers.*;
import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import models.Table;
import models.Menu;
import models.Order;

public class KitchenGUI extends JPanel {
    private JTable table;
    private JScrollPane scrollPane;
    private String[] columnNames = {"Dish Name", "Preparation Time", "Status"};
    private Object[][] data;
    private List<Order> meals;
    private Timer timer;
    private JTextArea billNoteArea;

    public KitchenGUI(JFrame frame, RestaurantManagement restaurant, Menu menu, Table tableObj) {
        // Get the meal list
        meals = tableObj.getOrders();
        updateDataArray(); // Create and populate the data array

        // Configure the panel
        setLayout(new BorderLayout());

        // Add data using JTable
        table = new JTable(data, columnNames);
        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Button to display kitchen information
        JButton btnShowKitchenInfo = new JButton("Show Kitchen Information");
        add(btnShowKitchenInfo, BorderLayout.SOUTH);

        // Set button click event
        btnShowKitchenInfo.addActionListener(e -> showKitchenInfo(meals));

        // Text area for bill note
        billNoteArea = new JTextArea();
        billNoteArea.setEditable(false);
        billNoteArea.setText("Bill Note:\n" + tableObj.getBillNote());
        JScrollPane billNoteScrollPane = new JScrollPane(billNoteArea);

        // Add to panel layout
        add(billNoteScrollPane, BorderLayout.NORTH);

        // Call the showKitchenInfo function initially
        showKitchenInfo(meals);

        // Timer runs periodically
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> showKitchenInfo(meals));
            }
        }, 0, 1000);
    }

    private void updateDataArray() {
        data = new Object[meals.size()][3];
        for (int i = 0; i < meals.size(); i++) {
            data[i][0] = meals.get(i).getProduct();
            data[i][1] = meals.get(i).getRemainingPreparationTime() + " sec";
            data[i][2] = meals.get(i).isPrepared() ? "Prepared" : "Preparing";
        }
    }

    public void showKitchenInfo(List<Order> updatedMeals) {
        // If the size of the meals list has changed, recreate the data array
        if (updatedMeals.size() != data.length) {
            meals = updatedMeals;
            updateDataArray();
            table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        } else {
            // Update existing data
            for (int i = 0; i < meals.size(); i++) {
                data[i][0] = meals.get(i).getProduct();
                data[i][1] = meals.get(i).getRemainingPreparationTime() + " sec";
                data[i][2] = meals.get(i).isPrepared() ? "Prepared" : "Preparing";
            }
            table.repaint();
        }
    }

    @Override
    public void finalize() {
        // Stop the timer
        if (timer != null) {
            timer.cancel();
        }
    }
}