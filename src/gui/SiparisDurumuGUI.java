package gui;

import javax.swing.*;

import controllers.*;
import models.Masa;
import models.Menu;
import models.Siparis;

import java.awt.*;
import java.util.TimerTask;
import java.util.Timer;

public class SiparisDurumuGUI {
    private JPanel mainPanel;
    private JList<String> siparisList;
    private DefaultListModel<String> listModel;
    private JLabel toplamFiyatLabel; // Toplam fiyatı gösterecek alan

    public SiparisDurumuGUI(JFrame frame, RestoranYonetimi restoranYonetimi, Menu menu, Masa masa) {
        mainPanel = new JPanel(new BorderLayout());

        // Sipariş Listesi
        listModel = new DefaultListModel<>();
        siparisList = new JList<>(listModel);
        siparisList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(siparisList);

        // "Sil" Butonu
        JButton btnSil = new JButton("Sil");
        btnSil.addActionListener(e -> {
            int[] selectedIndices = siparisList.getSelectedIndices();
            if (selectedIndices.length > 0) {
                for (int i = selectedIndices.length - 1; i >= 0; i--) {
                    masa.getSiparisler().remove(selectedIndices[i]); // Seçili siparişleri kaldır
                }
                updateSiparisList(masa); // Listeyi güncelle
                updateToplamFiyat(masa); // Toplam fiyatı güncelle
                JOptionPane.showMessageDialog(frame, "Seçili siparişler silindi!");
            } else {
                JOptionPane.showMessageDialog(frame, "Silmek için bir sipariş seçin!");
            }

        });

        // Toplam Fiyat Alanı
        toplamFiyatLabel = new JLabel("Toplam: 0.0 TL", SwingConstants.RIGHT);
        toplamFiyatLabel.setFont(new Font("Arial", Font.BOLD, 14));
        toplamFiyatLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Sipariş Paneli
        JPanel siparisPanel = new JPanel(new BorderLayout());
        siparisPanel.add(listScrollPane, BorderLayout.CENTER);
        siparisPanel.add(btnSil, BorderLayout.SOUTH);

        // Ana Panel
        mainPanel.add(new JLabel("Masa " + masa.getMasaNo() + " - Sipariş Durumu", SwingConstants.CENTER),
                BorderLayout.NORTH);
        mainPanel.add(siparisPanel, BorderLayout.CENTER);
        mainPanel.add(toplamFiyatLabel, BorderLayout.SOUTH); // Toplam fiyatı ana panelin altına ekleyin

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> updateSiparisList(masa));
            }
        }, 1000, 1000);

        // Sipariş Listesini Başlat
        updateSiparisList(masa);
        updateToplamFiyat(masa); // İlk toplam fiyatı göster
    }

    private void updateSiparisList(Masa masa) {
        listModel.clear(); // Listeyi sıfırla
        for (Siparis siparis : masa.getSiparisler()) {
            String siparisText = String.format("%-20s %10s %10.2f TL", siparis.getKategori() + ": " + siparis.getUrun(),
                    "", siparis.getFiyat());
            listModel.addElement(siparisText);
        }

        siparisList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                Siparis siparis = masa.getSiparisler().get(index);
                if (siparis.isHazırlandı()) {
                    renderer.setBackground(new Color(200, 250, 200));
                } else {
                    renderer.setBackground(new Color(250, 230, 200));
                }
                return renderer;
            }
        });
    }

    private void updateToplamFiyat(Masa masa) {
        double toplamFiyat = masa.hesaplaHesap();
        toplamFiyatLabel.setText("Toplam: " + toplamFiyat + " TL");
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}