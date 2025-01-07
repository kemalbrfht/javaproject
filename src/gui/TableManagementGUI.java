package gui;

import javax.swing.*;

import controllers.*;
import models.Table;
import models.Menu;

import java.awt.*;

public class TableManagementGUI {
    private JPanel mainPanel;

    public TableManagementGUI(JFrame frame, Table table, RestaurantManagement restaurant, Menu menu) {
        // Main Panel
        mainPanel = new JPanel(new BorderLayout());
        // Left Menu Panel
        LeftMenuPanelGUI leftMenuPanel = new LeftMenuPanelGUI(frame, restaurant, menu, table);

        // Center Panel (Order Status)
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(new OrderStatusGUI(frame, restaurant, menu, table).getPanel(), BorderLayout.CENTER);

        // Right Panel (Place Order)
        JPanel rightMenu = new JPanel(new GridLayout(0, 1, 10, 10));
        rightMenu.add(new PlaceOrderGUI(frame, table, menu, restaurant).getPanel());

        // Add Panels to Main Panel
        mainPanel.add(leftMenuPanel, BorderLayout.WEST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(rightMenu, BorderLayout.EAST);

        // Frame Settings
        frame.setContentPane(mainPanel);
        frame.revalidate();
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}