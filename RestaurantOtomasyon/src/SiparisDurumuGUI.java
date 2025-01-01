import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SiparisDurumuGUI {
    private JPanel mainPanel;

    public SiparisDurumuGUI(JFrame frame, Masa masa) {
        mainPanel = new JPanel(new BorderLayout());

        // Sipariş Durumu Paneli
        JPanel panelDurum = new JPanel();
        panelDurum.setLayout(new BorderLayout());
        // Üst Panel (Masa Adı ve Yemek Ara)
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        // Masa Adı
        JLabel masaAdiLabel = new JLabel("Masa: " + masa.getMasaNo());
        masaAdiLabel.setHorizontalAlignment(SwingConstants.LEFT);
        topPanel.add(masaAdiLabel, BorderLayout.WEST);

        
        panelDurum.add(topPanel, BorderLayout.NORTH);
        if (masa.getSiparisler().isEmpty()) {
            JLabel label = new JLabel("Bu masada henüz sipariş yok.");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            panelDurum.add(label, BorderLayout.CENTER);
        } else {
            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (Siparis siparis : masa.getSiparisler()) {
                listModel.addElement(siparis.getKategori() + ": " + siparis.getUrun() + " (" + siparis.getFiyat() + " TL)");
            }
            JList<String> siparisList = new JList<>(listModel);
            siparisList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            siparisList.setVisibleRowCount(-1);
            JScrollPane listScrollPane = new JScrollPane(siparisList);
            panelDurum.add(listScrollPane, BorderLayout.CENTER);
        }



        // Alt Panel (Geri Dön Butonu)
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        //bottomPanel.add(btnBack);

        // Ana Panel'e Alt Panel ve Sipariş Durumu Paneli ekle
        mainPanel.add(panelDurum, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}