
import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class SiparisVerGUI {
    public SiparisVerGUI(JFrame frame, Masa masa, Menu menu) {
        JPanel panelSiparis = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton btnAnaYemek = new JButton("Ana Yemekler");
        JButton btnIcecek = new JButton("İçecekler");
        JButton btnTatli = new JButton("Tatlılar");

        btnAnaYemek.addActionListener(e -> addSiparis(frame, masa, "Ana Yemek", menu));
        btnIcecek.addActionListener(e -> addSiparis(frame, masa, "İçecek", menu));
        btnTatli.addActionListener(e -> addSiparis(frame, masa, "Tatlı", menu));

        panelSiparis.add(btnAnaYemek);
        panelSiparis.add(btnIcecek);
        panelSiparis.add(btnTatli);

        JButton btnBack = new JButton("Geri Dön");
        btnBack.addActionListener(e -> new MasaYonetimGUI(frame, masa, null, menu));
        panelSiparis.add(btnBack);

        frame.setContentPane(panelSiparis);
        frame.revalidate();
    }

    private void addSiparis(JFrame frame, Masa masa, String kategori, Menu menu) {
        Map<String, Double> urunler = menu.getKategori(kategori);

        String[] urunListesi = urunler.keySet().toArray(new String[0]);
        String secim = (String) JOptionPane.showInputDialog(frame, "Sipariş seçin:", kategori, JOptionPane.PLAIN_MESSAGE, null, urunListesi, urunListesi[0]);

        if (secim != null) {
            double fiyat = urunler.get(secim);
            masa.getSiparisler().add(new Siparis(kategori, secim, fiyat));
            JOptionPane.showMessageDialog(frame, secim + " sipariş edildi. Fiyat: " + fiyat + " TL");
        }
    }
}
