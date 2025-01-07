package gui;

import controllers.*;
import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import models.Masa;
import models.Menu;
import models.Siparis;

public class MutfakGUI extends JPanel {
    private JTable table;
    private JScrollPane scrollPane;
    private String[] columnNames = {"Yemek Adı", "Hazırlanma Süresi", "Durum"};
    private Object[][] data;
    private List<Siparis> yemekler;
    private Timer timer;
    private JTextArea adisyonNotuArea;

    public MutfakGUI(JFrame frame, RestoranYonetimi restoran, Menu menu, Masa masa) {
        // Yemek listesini alın
        yemekler = masa.getSiparisler();
        updateDataArray(); // Data dizisini oluştur ve doldur

     // Paneli düzenle
     setLayout(new BorderLayout());

     // JTable ile verileri ekle
     table = new JTable(data, columnNames);
     scrollPane = new JScrollPane(table);
     add(scrollPane, BorderLayout.CENTER);

     // Mutfak Bilgilerini Gösteren Buton
     JButton btnShowMutfakInfo = new JButton("Mutfak Bilgilerini Göster");
     add(btnShowMutfakInfo, BorderLayout.SOUTH);

     // Buton tıklama olayını ayarla
     btnShowMutfakInfo.addActionListener(e -> showMutfakInfo(yemekler));
     adisyonNotuArea = new JTextArea();
     adisyonNotuArea.setEditable(false);
     adisyonNotuArea.setText("Adisyon Notu:\n" + masa.getAdisyonNotu());
     JScrollPane adisyonScrollPane = new JScrollPane(adisyonNotuArea);

     // Panel düzenine ekleme
     add(adisyonScrollPane, BorderLayout.NORTH);

     // showMutfakInfo fonksiyonunu başlangıçta çağır
     showMutfakInfo(yemekler);

        // Timer yalnızca bir kez çalıştırılır
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> showMutfakInfo(yemekler));
            }
        }, 0, 1000);
    }

    private void updateDataArray() {
        data = new Object[yemekler.size()][3];
        for (int i = 0; i < yemekler.size(); i++) {
            data[i][0] = yemekler.get(i).getUrun();
            data[i][1] = yemekler.get(i).getKalanHazırlanmaSüresi() + " sn";
            data[i][2] = yemekler.get(i).isHazırlandı() ? "Hazırlandı" : "Hazırlanıyor";
        }
    }

    public void showMutfakInfo(List<Siparis> yeniYemekler) {
        // Eğer yemekler listesinin boyutu değişmişse data dizisini yeniden oluştur
        if (yeniYemekler.size() != data.length) {
            yemekler = yeniYemekler;
            updateDataArray();
            table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        } else {
            // Mevcut datayı güncelle
            for (int i = 0; i < yemekler.size(); i++) {
                data[i][0] = yemekler.get(i).getUrun();
                data[i][1] = yemekler.get(i).getKalanHazırlanmaSüresi() + " sn";
                data[i][2] = yemekler.get(i).isHazırlandı() ? "Hazırlandı" : "Hazırlanıyor";
            }
            table.repaint();
        }
    }

    @Override
    public void finalize() {
        // Timer'ı durdur
        if (timer != null) {
            timer.cancel();
        }
    }
}