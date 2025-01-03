import javax.swing.*;
import java.awt.*;

public class SiparisDurumuGUI {
    private JPanel mainPanel;
    private JList<String> siparisList;
    private DefaultListModel<String> listModel;
    private JLabel toplamFiyatLabel; // Toplam fiyatı gösterecek alan

    public SiparisDurumuGUI(JFrame frame,RestoranYonetimi restoranYonetimi,Menu menu, Masa masa) {
        mainPanel = new JPanel(new BorderLayout());

        // Sipariş Listesi
        listModel = new DefaultListModel<>();
        siparisList = new JList<>(listModel);
        siparisList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(siparisList);

        // "Sil" Butonu
        JButton btnSil = new JButton("Sil");
        btnSil.addActionListener(e -> {
            int selectedIndex = siparisList.getSelectedIndex();
            if (selectedIndex != -1) {
                masa.getSiparisler().remove(selectedIndex); // Seçili siparişi kaldır
                updateSiparisList(masa); // Listeyi güncelle
                updateToplamFiyat(masa); // Toplam fiyatı güncelle
                JOptionPane.showMessageDialog(frame, "Sipariş silindi!");
            } else {
                JOptionPane.showMessageDialog(frame, "Silmek için bir sipariş seçin!");
            }
        });
        
        // Durum Paneli
        JPanel panelDurum = new JPanel(new BorderLayout());

        // Alt Panel (Geri Dön Butonu)
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JLabel totalLabel = new JLabel("Toplam: " + masa.hesaplaHesap() + " TL");
        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JButton btnPay = new JButton("Ödeme Yap");

        btnPay.addActionListener(e -> {
            double toplamHesap = masa.hesaplaHesap();
            int confirm = JOptionPane.showConfirmDialog(frame, "Toplam hesap: " + toplamHesap + " TL\nHesap ödensin mi?", "Ödeme Yap", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(frame, "Ödeme alındı. Masa " + masa.getMasaNo() + " boşaltıldı.");
                masa.masaKapat();
                new MasalarGUI(frame, restoranYonetimi, menu); // Masalar ekranına geri dön
            }
        });

        bottomPanel.add(totalLabel);
        bottomPanel.add(btnPay);

        panelDurum.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.add(panelDurum, BorderLayout.CENTER);
       
    

        // Toplam Fiyat Alanı
        toplamFiyatLabel = new JLabel("Toplam: 0.0 TL", SwingConstants.RIGHT);
        toplamFiyatLabel.setFont(new Font("Arial", Font.BOLD, 14));
        toplamFiyatLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Sipariş Paneli
        JPanel siparisPanel = new JPanel(new BorderLayout());
        siparisPanel.add(listScrollPane, BorderLayout.CENTER);
        siparisPanel.add(btnSil, BorderLayout.SOUTH);

        // Ana Panel
        mainPanel.add(new JLabel("Masa " + masa.getMasaNo() + " - Sipariş Durumu", SwingConstants.CENTER), BorderLayout.NORTH);
        mainPanel.add(siparisPanel, BorderLayout.CENTER);
        mainPanel.add(toplamFiyatLabel, BorderLayout.SOUTH); // Toplam fiyatı ana panelin altına ekleyin

        // Sipariş Listesini Başlat
        updateSiparisList(masa);
        updateToplamFiyat(masa); // İlk toplam fiyatı göster
    }
    
    

 
    private void updateSiparisList(Masa masa) {
        listModel.clear(); // Listeyi sıfırla
        for (Siparis siparis : masa.getSiparisler()) {
            listModel.addElement(siparis.getKategori() + ": " + siparis.getUrun() + " (" + siparis.getFiyat() + " TL)");
        }
    }

   
    private void updateToplamFiyat(Masa masa) {
        double toplamFiyat = masa.hesaplaHesap();
        toplamFiyatLabel.setText("Toplam: " + toplamFiyat + " TL");
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}