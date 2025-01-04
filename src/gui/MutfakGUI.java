package gui;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import controllers.*;
import models.Masa;
import models.Menu;
import models.Siparis;

import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MutfakGUI extends JPanel {
    private JTable table;
    private JScrollPane scrollPane;
    private String[] columnNames = {"Yemek Adı", "Hazırlanma Süresi", "Durum"};
    private Object[][] data;
    private JTextArea adisyonNotuArea;

    public MutfakGUI(JFrame frame, RestoranYonetimi restoran, Menu menu, Masa masa) {
        // Mutfakta bulunan yemeklerin listesini al
        List<Siparis> yemekler = masa.getSiparisler();
        data = new Object[yemekler.size()][3];

        // Yemek verilerini tabloya ekle
        for (int i = 0; i < yemekler.size(); i++) {
            data[i][0] = yemekler.get(i).getUrun();
            data[i][1] = yemekler.get(i).getHazırlanmasüresi() + " sn";
            data[i][2] = yemekler.get(i).isHazırlandı() ? "Hazırlandı" : "Hazırlanıyor";
        }

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

        // Her 1 saniyede bir showMutfakInfo fonksiyonunu çağır
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> showMutfakInfo(yemekler));
            }
        }, 1000, 1000);

        // Set cell renderer for the "Durum" column
        table.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if ("Hazırlandı".equals(value)) {
                    cell.setForeground(new Color(0, 128, 0)); 
                } else {
                    cell.setForeground(new Color(128, 100, 0));
                }
                return cell;
            }
        });
    }

    public void showMutfakInfo(List<Siparis> yemekler) {
        // Verileri güncelle
        for (int i = 0; i < yemekler.size(); i++) {
            System.out.println(yemekler.get(i).isHazırlandı());
            data[i][0] = yemekler.get(i).getUrun();
            data[i][1] = yemekler.get(i).getKalanHazırlanmaSüresi() + " sn";
            data[i][2] = yemekler.get(i).isHazırlandı() ? "Hazırlandı" : "Hazırlanıyor";
        }

        // Tabloyu yeniden çiz
        table.repaint();
    }
}