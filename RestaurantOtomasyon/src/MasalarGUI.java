
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MasalarGUI {
    private JFrame frame;
    private RestoranYonetimi restoran;
    private Menu menu;
    private JButton[] masaButtons;

    public MasalarGUI(JFrame frame, RestoranYonetimi restoran, Menu menu) {
        this.frame = frame;
        this.restoran = restoran;
        this.menu = menu;

        JPanel panelMasalar = new JPanel();
        panelMasalar.setLayout(new GridLayout(5, 4, 10, 10)); // 20 masa için grid düzeni

        masaButtons = new JButton[restoran.getMasalar().size()];

        for (int i = 0; i < restoran.getMasalar().size(); i++) {
            Masa masa = restoran.getMasalar().get(i);
            JButton btnMasa = new JButton();
            masaButtons[i] = btnMasa;
            updateMasaButton(btnMasa, masa); // Masanın ilk durumuna göre güncelle

            int finalI = i; // Lambda içinde kullanmak için gerekli
            btnMasa.addActionListener(e -> handleMasaClick(masa, finalI));
            panelMasalar.add(btnMasa);
        }

        JButton btnBack = new JButton("Ana Menüye Dön");
        btnBack.addActionListener(e -> new RestoranOtomasyonuGUI().initMainMenu());
        panelMasalar.add(btnBack);

        frame.setContentPane(panelMasalar);
        frame.revalidate();
    }

    // Masa butonunu duruma göre günceller
    private void updateMasaButton(JButton button, Masa masa) {
        button.setText("M " + masa.getMasaNo() + "\n" + (masa.isDolu() ? "Dolu" : "Boş"));
        button.setBackground(masa.isDolu() ? Color.RED : Color.GREEN);
        button.setOpaque(true);
        button.setForeground(Color.WHITE);
    }

    // Masa butonuna tıklandığında gerçekleşen işlemler
    private void handleMasaClick(Masa masa, int index) {
        if (!masa.isDolu()) {
            // Boş masaya tıklanırsa
            int confirm = JOptionPane.showConfirmDialog(frame, "Bu masayı açmak istiyor musunuz?", "Masa Aç", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                masa.masaAc();
                updateMasaButton(masaButtons[index], masa);
                JOptionPane.showMessageDialog(frame, "Masa " + masa.getMasaNo() + " açıldı.");
            }
        } else {
            // Dolu masaya tıklanırsa yönetim ekranını aç
            new MasaYonetimGUI(frame, masa, restoran, menu);
        }
    }

    public Component getPanel() {
        return frame
    .getContentPane();
    }
}
