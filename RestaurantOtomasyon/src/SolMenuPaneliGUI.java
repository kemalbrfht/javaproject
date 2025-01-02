import javax.swing.*;
import java.awt.*;

public class SolMenuPaneliGUI extends JPanel {
    public SolMenuPaneliGUI(JFrame frame) {
        // Ana panel düzeni ve özellikleri
        setLayout(new BorderLayout());
        setBackground(new Color(51, 51, 51));

        // Üst Menü Paneli
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(new Color(51, 51, 51));

        // Menü Butonlarını Ekle
        menuPanel.add(createMenuButton("Masa Değiştir", "src/icons/change.png", e -> JOptionPane.showMessageDialog(frame, "Masa Değiştir Tıklandı")));
        //menuPanel.add(createMenuButton("Adisyon Ekle", "src/icons/add.png", e -> JOptionPane.showMessageDialog(frame, "Adisyon Ekle Tıklandı")));
        menuPanel.add(createMenuButton("Adisyon Notu", "src/icons/notifications.png", e -> JOptionPane.showMessageDialog(frame, "Adisyon Notu Tıklandı")));
        //menuPanel.add(createMenuButton("Müşteri Seç", "src/icons/price-tag.png", e -> JOptionPane.showMessageDialog(frame, "Müşteri Seç Tıklandı")));
        //menuPanel.add(createMenuButton("Adisyon Ayır", "src/icons/strategy.png", e -> JOptionPane.showMessageDialog(frame, "Adisyon Ayır Tıklandı")));
        menuPanel.add(createMenuButton("Mutfak", "src/icons/printer.png", e -> JOptionPane.showMessageDialog(frame, "Ödeme Tipi Tıklandı")));

        // Alt Panel (Bağımsız Butonlar)
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(new Color(51, 51, 51));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Üstten boşluk

       
        JButton btnOdeme = new JButton("Ödeme");
        btnOdeme.setPreferredSize(new Dimension(200,50));

        btnOdeme.setMaximumSize(new Dimension(200, 300));
        btnOdeme.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnOdeme.setBackground(new Color(153, 0, 0));
        btnOdeme.setForeground(Color.WHITE);
        btnOdeme.setFocusPainted(false);
        btnOdeme.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Ödeme Tıklandı"));

        bottomPanel.add(Box.createVerticalGlue()); // Butonları alta itmek için
        //bottomPanel.add(btnHesapYaz);
        bottomPanel.add(btnOdeme);

        // Ana Panel'e Alt ve Üst Panelleri Ekle
        add(menuPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Menü Butonu Oluşturucu
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
}
