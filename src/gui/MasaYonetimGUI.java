package gui;

import javax.swing.*;

import controllers.*;
import models.Masa;
import models.Menu;

import java.awt.*;

public class MasaYonetimGUI {
    private JPanel mainPanel;

    public MasaYonetimGUI(JFrame frame, Masa masa, RestoranYonetimi restoran, Menu menu) {
        // Ana Panel
        mainPanel = new JPanel(new BorderLayout());
        // Sol Menü Paneli
        SolMenuPaneliGUI solMenuPanel = new SolMenuPaneliGUI(frame, restoran, menu, masa);

        // Orta Panel (Sipariş Durumu)
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(new SiparisDurumuGUI(frame, restoran, menu, masa).getPanel(), BorderLayout.CENTER);

        // Sağ Panel (Sipariş Ver)
        JPanel rightMenu = new JPanel(new GridLayout(0, 1, 10, 10));
        rightMenu.add(new SiparisVerGUI(frame, masa, menu, restoran).getPanel());

        // Alt Panel (Hesap ve İşlemler)
        JPanel bottomPanel = new JPanel(new GridLayout(1, 3));
        JLabel totalLabel = new JLabel("Toplam: " + masa.hesaplaHesap() + " TL", SwingConstants.RIGHT);

        // Adisyon Notunu Mutfak'a Gönder
        JButton btnSendToMutfak = new JButton("Mutfak'a Gönder");
        btnSendToMutfak.addActionListener(e -> {
            restoran.getMutfak().adisyonNotuEkle(masa); // Adisyon notunu mutfağa gönder
            JOptionPane.showMessageDialog(frame, "Adisyon notu mutfağa gönderildi: \n" + masa.getAdisyonNotu());
        });

        // Bottom panel'e ekleme
        bottomPanel.add(totalLabel);
        bottomPanel.add(btnSendToMutfak); // Yeni buton burada ekleniyor

        // Panelleri Ana Panele Ekleyin
        mainPanel.add(solMenuPanel, BorderLayout.WEST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(rightMenu, BorderLayout.EAST);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Frame Ayarları
        frame.setContentPane(mainPanel);
        frame.revalidate();
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}