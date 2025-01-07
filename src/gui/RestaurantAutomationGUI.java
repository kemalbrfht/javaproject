package gui;

import javax.swing.*;

import controllers.*;
import models.Menu;

import java.awt.*;

public class RestaurantAutomationGUI {
    private JFrame frame;
    private RestaurantManagement restaurant;
    private Menu menu;

    public RestaurantAutomationGUI() {
        restaurant = new RestaurantManagement();
        menu = new Menu();
        frame = new JFrame("Restaurant Automation");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        initMainMenu();
        frame.setVisible(true);
    }

    public void initMainMenu() {
        TablesGUI tablesGUI = new TablesGUI(frame, restaurant, menu);
        frame.setContentPane((Container) tablesGUI.getPanel());
        frame.revalidate();
    }
}