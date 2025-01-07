package gui;

import javax.swing.*;

import controllers.*;
import models.Table;
import models.Menu;

import java.awt.*;

public class TablesGUI {
    private JFrame frame;
    private RestaurantManagement restaurant;
    private Menu menu;
    private JButton[] tableButtons;

    public TablesGUI(JFrame frame, RestaurantManagement restaurant, Menu menu) {
        this.frame = frame;
        this.restaurant = restaurant;
        this.menu = menu;

        JPanel panelTables = new JPanel();
        panelTables.setLayout(new GridLayout(5, 4, 10, 10)); // Grid layout for 20 tables

        tableButtons = new JButton[restaurant.getTables().size()];

        for (int i = 0; i < restaurant.getTables().size(); i++) {
            Table table = restaurant.getTables().get(i);
            JButton btnTable = new JButton();
            tableButtons[i] = btnTable;
            updateTableButton(btnTable, table); // Update based on the initial state of the table

            int finalI = i; // Necessary for use within lambda
            btnTable.addActionListener(e -> handleTableClick(table, finalI));
            panelTables.add(btnTable);
        }
        JLabel lblRevenue = new JLabel("Revenue: " + restaurant.getRevenue() + " TL");
        lblRevenue.setHorizontalAlignment(SwingConstants.CENTER);
        panelTables.add(lblRevenue);

        frame.setContentPane(panelTables);
        frame.revalidate();
    }

    // Updates the table button based on its status
    private void updateTableButton(JButton button, Table table) {
        button.setText("T " + table.getTableNo() + "\n" + (table.isOccupied() ? " Occupied" : " Available"));
        button.setBackground(table.isOccupied() ? new Color(190, 0, 0) : new Color(0, 190, 0));
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        button.setForeground(Color.WHITE);
    }

    // Handles actions when a table button is clicked
    private void handleTableClick(Table table, int index) {
        if (!table.isOccupied()) {
            // If an available table is clicked
            table.openTable();
            updateTableButton(tableButtons[index], table);
            JOptionPane.showMessageDialog(frame, "Table " + table.getTableNo() + " is now open.");
            handleTableClick(table, index);
        } else {
            // If an occupied table is clicked, open the management screen
            new TableManagementGUI(frame, table, restaurant, menu);
        }
    }

    public Component getPanel() {
        return frame.getContentPane();
    }
}