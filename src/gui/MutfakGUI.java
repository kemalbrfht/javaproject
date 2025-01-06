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

    public MutfakGUI(JFrame frame, RestoranYonetimi restoran, Menu menu, Masa masa) {
        // Yemek listesini alın
        yemekler = masa.getSiparisler();
        data = new Object[yemekler.size()][3];

        // Verileri tabloya ekle
        for (int i = 0; i < yemekler.size(); i++) {
            data[i][0] = yemekler.get(i).getUrun();
            data[i][1] = yemekler.get(i).getKalanHazırlanmaSüresi() + " sn";
            data[i][2] = yemekler.get(i).isHazırlandı() ? "Hazırlandı" : "Hazırlanıyor";
        }

        setLayout(new BorderLayout());
        table = new JTable(data, columnNames);
        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Timer yalnızca bir kez çalıştırılır
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> showMutfakInfo(yemekler));
            }
        }, 0, 1000);
    }

    public void showMutfakInfo(List<Siparis> yemekler) {
        // Yemeklerin durumunu güncelle
        for (int i = 0; i < yemekler.size(); i++) {
            data[i][0] = yemekler.get(i).getUrun();
            data[i][1] = yemekler.get(i).getKalanHazırlanmaSüresi() + " sn";
            data[i][2] = yemekler.get(i).isHazırlandı() ? "Hazırlandı" : "Hazırlanıyor";
        }
        table.repaint(); // Tabloyu güncelle
    }
}
