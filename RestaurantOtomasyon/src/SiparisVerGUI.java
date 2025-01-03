import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class SiparisVerGUI {
    private JFrame frame;
    private JPanel panelGorsel;
    private RestoranYonetimi restoran;

    public SiparisVerGUI(JFrame frame, Masa masa, Menu menu, RestoranYonetimi restoran) {
        this.frame = frame;
        this.restoran = restoran;

        // Ana Panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Sol Menü (Kategoriler)
        JPanel leftMenu = new JPanel();
        leftMenu.setLayout(new BoxLayout(leftMenu, BoxLayout.Y_AXIS));
        leftMenu.setPreferredSize(new Dimension(200, frame.getHeight()));
        leftMenu.add(Box.createVerticalStrut(10)); // Boşluk ekleme

        leftMenu.add(createCategoryButton("Ana Yemekler", masa, menu, "Ana Yemek"));
        leftMenu.add(createCategoryButton("Makarna", masa, menu, "Makarna"));
        leftMenu.add(createCategoryButton("İçecekler", masa, menu, "İçecek"));
        leftMenu.add(createCategoryButton("Tatlılar", masa, menu, "Tatlı"));
        leftMenu.add(createCategoryButton("Corbalar", masa, menu, "corbalar"));


        // Sağ Panel (Görseller)
        panelGorsel = new JPanel(new GridLayout(0, 3, 20, 20)); // 3 sütunlu düzen, boşluklar eklenmiş
        panelGorsel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(panelGorsel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Başlangıçta tüm yemekleri yükle
        loadCategoryProducts("Ana Yemek", masa, menu);

        
        // Ana Paneli Düzenleme
        mainPanel.add(leftMenu, BorderLayout.WEST);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Frame Ayarları
        frame.setContentPane(mainPanel);
        frame.revalidate();
        frame.repaint();
    }

    private JButton createCategoryButton(String text, Masa masa, Menu menu, String kategori) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 50));
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(e -> loadCategoryProducts(kategori, masa, menu));
        return button;
    }

    private void loadCategoryProducts(String kategori, Masa masa, Menu menu) {
        panelGorsel.removeAll();

        Map<String, Menu.Urun> urunler = menu.getKategori(kategori);

        for (Map.Entry<String, Menu.Urun> entry : urunler.entrySet()) {
            String urun = entry.getKey();
            Menu.Urun urunObj = entry.getValue();

            // Görsel yükleme
            ImageIcon urunGorsel;
            try {
                String imagePath = "/images/" + urun.replaceAll(" ", "_").toLowerCase() + ".jpg";
                System.out.println("Yüklenen görsel: " + imagePath); // Debug için
                urunGorsel = new ImageIcon(getClass().getResource(imagePath));
                if (urunGorsel.getIconWidth() <= 0) {
                    throw new Exception("Görsel yüklenemedi");
                }
                Image scaledImage = urunGorsel.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                urunGorsel = new ImageIcon(scaledImage);
            } catch (Exception e) {
                System.err.println("Hata: " + e.getMessage());
                urunGorsel = new ImageIcon(getClass().getResource("/images/default.jpg"));
            }

            JPanel productPanel = new JPanel(new BorderLayout());
            productPanel.setBackground(new Color(255, 102, 102));

            JLabel productImageLabel = new JLabel(urunGorsel, SwingConstants.CENTER);
            JLabel productLabel = new JLabel(
                "<html><center>" + urun + "<br>" + urunObj.getFiyat() + " TL</center></html>",
                SwingConstants.CENTER
            );
            productLabel.setForeground(Color.WHITE);

            JButton siparisButton = new JButton("Ekle");
            siparisButton.addActionListener(e -> {
                addSiparis(masa, kategori, urun,urunObj.getFiyat(),urunObj.getYemkesuresi());
                new MasaYonetimGUI(frame, masa, restoran, menu);
            });

            productPanel.add(productImageLabel, BorderLayout.NORTH); // Görsel üstte
            productPanel.add(productLabel, BorderLayout.CENTER);     // Ürün bilgisi ortada
            productPanel.add(siparisButton, BorderLayout.SOUTH);     // Buton altta

            panelGorsel.add(productPanel);
        }

        panelGorsel.revalidate();
        panelGorsel.repaint();
    }

    private void addSiparis(Masa masa, String kategori, String urun, double fiyat,int hazırlanmasuresi) {
        masa.getSiparisler().add(new Siparis(kategori, urun, fiyat,hazırlanmasuresi));
    }

    public Component getPanel() {
        return frame.getContentPane();
    }
}
