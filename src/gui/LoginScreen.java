package gui;
import java.awt.*;
import javax.swing.*;

// Correct the import statements
import models.Menu;
import controllers.RestaurantManagement;

public class LoginScreen extends JFrame {

    public LoginScreen() {
        // Set the window title
        setTitle("Restaurant Application - Login");

        // Set the application to full screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set the background image
        JLabel background = new JLabel();
        background.setLayout(new BorderLayout());
        setContentPane(background);

        // Scale and set the image
        try {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("/images/background.png"));
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Image scaledImage = originalIcon.getImage().getScaledInstance((int) screenSize.getWidth(), (int) screenSize.getHeight(), Image.SCALE_SMOOTH);
            background.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            background.setIcon(new ImageIcon(getClass().getResource("/images/default.jpg")));
        }

        // Add text on top
        JLabel animatedLabel = new JLabel("Welcome", SwingConstants.CENTER);
        animatedLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        animatedLabel.setForeground(Color.WHITE);
        background.add(animatedLabel, BorderLayout.CENTER);

        // Add a start button
        JButton startButton = new JButton("START");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(new Color(38, 34, 31));
        startButton.setFocusPainted(false);
        background.add(startButton, BorderLayout.SOUTH);

        JButton startButton2 = new JButton();
        startButton2.setForeground(Color.WHITE);
        startButton2.setBackground(new Color(38, 34, 31));
        background.add(startButton2, BorderLayout.NORTH);

        // Start the animation
        new Thread(() -> {
            try {
                while (true) {
                    for (int i = 0; i <= 255; i++) {
                        animatedLabel.setForeground(new Color(i, i, i));
                        startButton.setForeground(new Color(i, i, i));
                        Thread.sleep(10);
                    }
                    for (int i = 255; i >= 0; i--) {
                        animatedLabel.setForeground(new Color(i, i, i));
                        startButton.setForeground(new Color(i, i, i));
                        Thread.sleep(10);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // Add click event for the button
        startButton.addActionListener(e -> {
            // Launch TablesGUI on the current frame
            RestaurantManagement restaurant = new RestaurantManagement();
            Menu menu = new Menu();
            new TablesGUI(this, restaurant, menu);
        });

        // Set visibility
        setVisible(true);
    }
}