package gui;
import javax.swing.*;

import controllers.*;
import models.Masa;
import models.Menu;

import java.awt.*;

public class SolMenuPaneliGUI extends JPanel {
    public SolMenuPaneliGUI(JFrame frame, RestoranYonetimi restoran, Menu menu, Masa masa) {
        // Ana panel düzeni ve özellikleri
        setLayout(new BorderLayout());
        setBackground(new Color(51, 51, 51));

        // Üst Menü Paneli
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(new Color(51, 51, 51));

        // Menü Butonları
        menuPanel.add(createMenuButton("Masa Değiştir", "src/icons/change.png", e -> new MasalarGUI(frame, restoran, menu)));
        menuPanel.add(createMenuButton("Adisyon Notu", "src/icons/notifications.png", e -> openAdisyonNotuDialog(frame, restoran, masa)));
        menuPanel.add(createMenuButton("Mutfak", "src/icons/printer.png", e -> {
            JPanel mutfakPanel = new MutfakGUI(frame, restoran, menu, masa);
            JOptionPane.showMessageDialog(frame, mutfakPanel, "Mutfak Ekranı", JOptionPane.PLAIN_MESSAGE);
        }));

        // Alt Panel (Bağımsız Butonlar)
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(new Color(51, 51, 51));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Üstten boşluk

        // "Ödeme" Butonu
        JButton btnOdeme = new JButton("Ödeme");
        btnOdeme.setPreferredSize(new Dimension(200, 50));
        btnOdeme.setMaximumSize(new Dimension(200, 300));
        btnOdeme.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnOdeme.setBackground(new Color(153, 0, 0));
        btnOdeme.setForeground(Color.WHITE);
        btnOdeme.setFocusPainted(false);
        btnOdeme.addActionListener(e -> handleOdeme(frame, restoran, menu, masa));

        bottomPanel.add(Box.createVerticalGlue()); // Butonları alta itmek için
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

    // Ödeme İşlem Mantığı
    private void handleOdeme(JFrame frame, RestoranYonetimi restoran, Menu menu, Masa masa) {
        // Ödeme Yöntemi Seçimi
        String[] options = {"Kredi Kartı", "Banka Kartı"};
        int odemeYontemi = JOptionPane.showOptionDialog(
            frame,
            "Hangi yöntemle ödeme yapmak istiyorsunuz?",
            "Ödeme Yöntemi",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );

        if (odemeYontemi == -1) return; // Kullanıcı seçim yapmadıysa çık

        // Ödeme Onayı
        int confirm = JOptionPane.showConfirmDialog(
            frame,
            "Ödeme " + options[odemeYontemi] + " ile yapılacak. Onaylıyor musunuz?",
            "Ödeme Onayı",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(frame, "Ödeme başarılı! Masa kapatılıyor.");
            restoran.ciroyaEkle(masa.hesaplaHesap());
            masa.masaKapat();
            new MasalarGUI(frame, restoran, menu); // Ana ekrana dön
        } else {
            JOptionPane.showMessageDialog(frame, "Ödeme iptal edildi.");
        }
    }

    // Adisyon Notu Diyaloğu
    private void openAdisyonNotuDialog(JFrame frame, RestoranYonetimi restoran, Masa masa) {
        JDialog dialog = new JDialog(frame, "Adisyon Notu", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(frame);

        JTextArea textArea = new JTextArea(masa.getAdisyonNotu());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton btnSave = new JButton("Kaydet");
        btnSave.addActionListener(e -> {
            masa.setAdisyonNotu(textArea.getText());
            JOptionPane.showMessageDialog(dialog, "Adisyon notu kaydedildi!");
            dialog.dispose();
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnSave, BorderLayout.SOUTH);

        dialog.add(panel);
        dialog.setVisible(true);
    }
}