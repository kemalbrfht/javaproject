package gui;

import controllers.Kitchen;
import controllers.RestaurantManagement;
import java.awt.*;
import java.util.Map;
import javax.swing.*;
import models.Table;
import models.Menu;
import models.Order;
import models.FoodOrder;

public class PlaceOrderGUI {
    private JFrame frame;
    private JPanel visualPanel;
    private RestaurantManagement restaurant;
    private Kitchen kitchen = new Kitchen();

    public PlaceOrderGUI(JFrame frame, Table table, Menu menu, RestaurantManagement restaurant) {
        this.frame = frame;
        this.restaurant = restaurant;

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Left Menu (Categories)
        JPanel leftMenu = new JPanel();
        leftMenu.setLayout(new BoxLayout(leftMenu, BoxLayout.Y_AXIS));
        leftMenu.setPreferredSize(new Dimension(200, frame.getHeight()));
        leftMenu.add(createCategoryButton("Main Dishes", table, menu, "Main Dish"));
        leftMenu.add(createCategoryButton("Pasta", table, menu, "Pasta"));
        leftMenu.add(createCategoryButton("Drinks", table, menu, "Drink"));
        leftMenu.add(createCategoryButton("Desserts", table, menu, "Dessert"));
        leftMenu.add(createCategoryButton("Soups", table, menu, "Soup"));

        // Right Panel (Visuals)
        visualPanel = new JPanel(new GridLayout(0, 3, 20, 20)); // 3-column layout with spacing
        visualPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(visualPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Initially load all dishes
        loadCategoryProducts("Main Dish", table, menu);

        // Configure Main Panel
        mainPanel.add(leftMenu, BorderLayout.WEST);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Frame Settings
        frame.setContentPane(mainPanel);
        frame.revalidate();
        frame.repaint();
    }

    private JButton createCategoryButton(String text, Table table, Menu menu, String category) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 50));
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(e -> loadCategoryProducts(category, table, menu));
        return button;
    }

    private void loadCategoryProducts(String category, Table table, Menu menu) {
        visualPanel.removeAll();

        Map<String, Menu.Product> products = menu.getCategory(category);

        for (Map.Entry<String, Menu.Product> entry : products.entrySet()) {
            String product = entry.getKey();
            Menu.Product productObj = entry.getValue();

            // Load visual
            ImageIcon productVisual;
            try {
                String imagePath = "/images/" + product.replaceAll(" ", "_").toLowerCase() + ".jpg";
                java.net.URL imageUrl = getClass().getResource(imagePath);
                if (imageUrl == null) {
                    throw new Exception("Visual not found: " + imagePath);
                }
                productVisual = new ImageIcon(getClass().getResource(imagePath));
                if (productVisual.getIconWidth() <= 0) {
                    throw new Exception("Visual could not be loaded");
                }
                Image scaledImage = productVisual.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
                productVisual = new ImageIcon(scaledImage);
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                productVisual = new ImageIcon(getClass().getResource("/images/default.jpg"));
            }

            JPanel productPanel = new JPanel(new BorderLayout());
            productPanel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    addOrder(table, category, product, productObj.getPrice(), productObj.getPreparationTime());
                    kitchen.addMeals(table.getOrders());
                    kitchen.prepareMeals();
                    new TableManagementGUI(frame, table, restaurant, menu);
                }
            });
            productPanel.setBackground(new Color(255, 102, 102));

            JLabel productImageLabel = new JLabel(productVisual, SwingConstants.CENTER);

            JButton orderButton = new JButton("<html><center style='padding:10px;'>" + product + "<br>"
                    + productObj.getPrice() + " TL</center></html>");
            orderButton.setBackground(new Color(255, 100, 100));
            orderButton.setForeground(Color.WHITE);
            orderButton.addActionListener(e -> {
                addOrder(table, category, product, productObj.getPrice(), productObj.getPreparationTime());
                kitchen.addMeals(table.getOrders());
                kitchen.prepareMeals();
                new TableManagementGUI(frame, table, restaurant, menu);
            });

            productPanel.add(productImageLabel, BorderLayout.NORTH); // Visual on top
            productPanel.add(orderButton, BorderLayout.CENTER); // Button below

            visualPanel.add(productPanel);
        }

        visualPanel.revalidate();
        visualPanel.repaint();
    }

    private void addOrder(Table table, String category, String product, double price, int preparationTime) {
        Order order = new FoodOrder(category, product, price, preparationTime);
        table.getOrders().add(order);
    }

    public Component getPanel() {
        return frame.getContentPane();
    }
}