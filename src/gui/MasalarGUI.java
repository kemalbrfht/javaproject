package gui;

import javax.swing.*;

import controllers.*;
import models.Masa;
import models.Menu;

import java.awt.*;

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
        JLabel lblCiro = new JLabel("Ciro: " + restoran.getCiro() + " TL");
        lblCiro.setHorizontalAlignment(SwingConstants.CENTER);
        panelMasalar.add(lblCiro);

        frame.setContentPane(panelMasalar);
        frame.revalidate();
    }

    // Masa butonunu duruma göre günceller
    private void updateMasaButton(JButton button, Masa masa) {
        button.setText("M " + masa.getMasaNo() + "\n" + (masa.isDolu() ? " Dolu" : " Boş"));
        button.setBackground(masa.isDolu() ? new Color(190, 0, 0) : new Color(0, 190, 0));
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        button.setForeground(Color.WHITE);
    }

    // Masa butonuna tıklandığında gerçekleşen işlemler
    private void handleMasaClick(Masa masa, int index) {
        if (!masa.isDolu()) {
            // Boş masaya tıklanırsa
            masa.masaAc();
            updateMasaButton(masaButtons[index], masa);
            JOptionPane.showMessageDialog(frame, "Masa " + masa.getMasaNo() + " açıldı.");
            handleMasaClick(masa, index);
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