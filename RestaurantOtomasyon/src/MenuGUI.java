import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MenuGUI {
    private JFrame frame;
    private JPanel contentPanel;
    private ArrayList<MenuItem> menuItems;

    public MenuGUI() {
        frame = new JFrame("Restaurant Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        
        // Initialize menu items
        menuItems = new ArrayList<>();
        initializeMenuItems();
        
        // Create main layout
        contentPanel = new JPanel(new BorderLayout());
        
        // Create and add left menu
        JPanel leftMenu = createLeftMenu();
        contentPanel.add(leftMenu, BorderLayout.WEST);
        
        // Create and add right content panel
        JPanel rightPanel = createRightPanel();
        contentPanel.add(rightPanel, BorderLayout.CENTER);
        
        frame.add(contentPanel);
        frame.setVisible(true);
    }

    private JPanel createLeftMenu() {
        JPanel leftMenu = new JPanel();
        leftMenu.setLayout(new BoxLayout(leftMenu, BoxLayout.Y_AXIS));
        leftMenu.setBackground(Color.DARK_GRAY);
        leftMenu.setPreferredSize(new Dimension(150, 0));

        String[] categories = {"Başlangıçlar", "Makarnalar", "Ana Yemekler", 
                             "Çorbalar", "Salatalar", "Tatlılar", "İçecekler"};

        for (String category : categories) {
            JButton btn = new JButton(category);
            btn.setMaximumSize(new Dimension(150, 40));
            btn.setBackground(Color.DARK_GRAY);
            btn.setForeground(Color.WHITE);
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            
            btn.addActionListener(e -> updateRightPanel(category));
            
            leftMenu.add(btn);
        }

        return leftMenu;
    }

    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        rightPanel.setBackground(Color.WHITE);
        updateRightPanel("Ana Yemekler"); // Default category
        return rightPanel;
    }

    private void updateRightPanel(String category) {
        JPanel rightPanel = (JPanel) contentPanel.getComponent(1);
        rightPanel.removeAll();

        for (MenuItem item : menuItems) {
            if (item.getCategory().equals(category)) {
                JPanel itemPanel = createMenuItemPanel(item);
                rightPanel.add(itemPanel);
            }
        }

        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private JPanel createMenuItemPanel(MenuItem item) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(new Color(255, 99, 71)); // Coral color background
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Image
        ImageIcon icon = new ImageIcon(item.getImagePath());
        Image image = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        panel.add(imageLabel, BorderLayout.CENTER);

        // Name
        JLabel nameLabel = new JLabel(item.getName(), SwingConstants.CENTER);
        nameLabel.setForeground(Color.WHITE);
        panel.add(nameLabel, BorderLayout.NORTH);

        // Price
        JLabel priceLabel = new JLabel(item.getPrice() + " TL", SwingConstants.CENTER);
        priceLabel.setForeground(Color.WHITE);
        panel.add(priceLabel, BorderLayout.SOUTH);

        return panel;
    }

    private void initializeMenuItems() {
        // Add sample menu items
        menuItems.add(new MenuItem("Bonfile", "Ana Yemekler", "images/bonfile.jpg", 150.0));
        menuItems.add(new MenuItem("Tavuk", "Ana Yemekler", "images/tavuk.jpg", 80.0));
        menuItems.add(new MenuItem("Balık", "Ana Yemekler", "images/balik.jpg", 120.0));
        menuItems.add(new MenuItem("Kabak Çorbası", "Çorbalar", "images/kabak_corbasi.jpg", 45.0));
        menuItems.add(new MenuItem("Pancar Çorbası", "Çorbalar", "images/pancar_corbasi.jpg", 45.0));
        menuItems.add(new MenuItem("Domates Çorbası", "Çorbalar", "images/domates_corbasi.jpg", 40.0));
        menuItems.add(new MenuItem("Roka Salatası", "Salatalar", "images/roka_salatasi.jpg", 55.0));
        menuItems.add(new MenuItem("Sezar Salatası", "Salatalar", "images/sezar_salatasi.jpg", 65.0));
        menuItems.add(new MenuItem("Mevsim Salatası", "Salatalar", "images/mevsim_salatasi.jpg", 50.0));
    }

    // MenuItem class
    private class MenuItem {
        private String name;
        private String category;
        private String imagePath;
        private double price;

        public MenuItem(String name, String category, String imagePath, double price) {
            this.name = name;
            this.category = category;
            this.imagePath = imagePath;
            this.price = price;
        }

        public String getName() { return name; }
        public String getCategory() { return category; }
        public String getImagePath() { return imagePath; }
        public double getPrice() { return price; }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuGUI());
    }
}