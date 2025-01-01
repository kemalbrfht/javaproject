
import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class SiparisVerGUI {
    private JFrame frame;
    private JPanel panelGorsel;


    public SiparisVerGUI(JFrame frame, Masa masa, Menu menu) {
        this.frame = frame;

        // Ana Panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Sol Menü (Kategoriler)
        JPanel leftMenu = new JPanel();
        leftMenu.setLayout(new BoxLayout(leftMenu, BoxLayout.Y_AXIS));
        leftMenu.setBackground(new Color(51, 51, 51));

        leftMenu.add(createCategoryButton("Ana Yemekler", masa, menu, "Ana Yemek"));
        leftMenu.add(createCategoryButton("İçecekler", masa, menu, "İçecek"));
        leftMenu.add(createCategoryButton("Tatlılar", masa, menu, "Tatlı"));

        // Sağ Panel (Görseller)
        panelGorsel = new JPanel(new GridLayout(0, 3, 10, 10));
        panelGorsel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(panelGorsel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Başlangıçta tüm yemekleri yükle
        loadCategoryProducts("Ana Yemek", masa, menu);

        // Alt Menü (Geri Dön Butonu)
        JPanel bottomPanel = new JPanel();
        JButton btnBack = new JButton("Hesapla");
        btnBack.addActionListener(e -> new MasaYonetimGUI(frame, masa, null, menu));
        bottomPanel.add(btnBack);

        // Ana Paneli Düzenleme
        mainPanel.add(leftMenu, BorderLayout.WEST);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Frame Ayarları
        frame.setContentPane(mainPanel);
        frame.revalidate();
        frame.repaint();
    }
    private JButton createCategoryButton(String text, Masa masa, Menu menu, String kategori) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(150, 50));
        button.setBackground(new Color(102, 102, 102));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);

        button.addActionListener(e -> loadCategoryProducts(kategori, masa, menu));
        return button;
    }

    private void loadCategoryProducts(String kategori, Masa masa, Menu menu) {
        panelGorsel.removeAll();

        Map<String, Double> urunler = menu.getKategori(kategori);

        for (Map.Entry<String, Double> entry : urunler.entrySet()) {
            String urun = entry.getKey();
            double fiyat = entry.getValue();

            JPanel productPanel = new JPanel(new BorderLayout());
            productPanel.setBackground(new Color(255, 102, 102));

            JLabel productLabel = new JLabel(urun + " - " + fiyat + " TL", SwingConstants.CENTER);
            productLabel.setForeground(Color.WHITE);

            JButton siparisButton = new JButton("Ekle");
            siparisButton.addActionListener(e -> addSiparis(masa, kategori, urun, fiyat));

            productPanel.add(productLabel, BorderLayout.CENTER);
            productPanel.add(siparisButton, BorderLayout.SOUTH);

            panelGorsel.add(productPanel);
        }

        panelGorsel.revalidate();
        panelGorsel.repaint();
    }

    // Sipariş Ekle
    private void addSiparis(Masa masa, String kategori, String urun, double fiyat) {
        masa.getSiparisler().add(new Siparis(kategori, urun, fiyat));
        JOptionPane.showMessageDialog(frame, urun + " sipariş edildi. Fiyat: " + fiyat + " TL");
    }
    public Component getPanel() {
 return frame.getContentPane();

    }
}
