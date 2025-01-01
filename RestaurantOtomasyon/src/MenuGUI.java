
import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MenuGUI {
    public MenuGUI(JFrame frame, Menu menu) {
        JPanel panelMenu = new JPanel(new GridLayout(4, 1, 10, 10));

        JButton btnAnaYemek = new JButton("Ana Yemekler");
        JButton btnIcecek = new JButton("İçecekler");
        JButton btnTatli = new JButton("Tatlılar");
        JButton btnBack = new JButton("Ana Menüye Dön");

        btnAnaYemek.addActionListener(e -> showKategori(frame, "Ana Yemek", menu));
        btnIcecek.addActionListener(e -> showKategori(frame, "İçecek", menu));
        btnTatli.addActionListener(e -> showKategori(frame, "Tatlı", menu));
        btnBack.addActionListener(e -> frame.getContentPane().removeAll());

        panelMenu.add(btnAnaYemek);
        panelMenu.add(btnIcecek);
        panelMenu.add(btnTatli);
        panelMenu.add(btnBack);

        frame.setContentPane(panelMenu);
        frame.revalidate();
    }

    private void showKategori(JFrame frame, String kategori, Menu menu) {
        JPanel panelKategori = new JPanel();
        panelKategori.setLayout(new BoxLayout(panelKategori, BoxLayout.Y_AXIS));

        Map<String, Double> urunler = menu.getKategori(kategori);

        for (Map.Entry<String, Double> entry : urunler.entrySet()) {
            String urun = entry.getKey();
            double fiyat = entry.getValue();

            JLabel label = new JLabel(urun + " - " + fiyat + " TL");
            panelKategori.add(label);
        }

        JButton btnBack = new JButton("Hesapla ");
        btnBack.addActionListener(e -> new MenuGUI(frame, menu));
        panelKategori.add(btnBack);

        frame.setContentPane(new JScrollPane(panelKategori));
        frame.revalidate();
    }
}
