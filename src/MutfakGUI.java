import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MutfakGUI extends JPanel {
    private JTable table;
    private JScrollPane scrollPane;
    private String[] columnNames = {"Yemek Adı", "Hazırlanma Süresi", "Hazırlandı"};
    private Object[][] data;
    private JTextArea adisyonNotuArea;

    public MutfakGUI(JFrame frame, RestoranYonetimi restoran, Menu menu, Masa masa) {
        // Mutfakta bulunan yemeklerin listesini al
        List<Siparis> yemekler = masa.getSiparisler();
        data = new Object[yemekler.size()][3];

        // Yemek verilerini tabloya ekle
        for (int i = 0; i < yemekler.size(); i++) {
            data[i][0] = yemekler.get(i).getUrun();
            data[i][1] = yemekler.get(i).getHazırlanmasüresi() + "sn";
            data[i][2] = "Evet";
        }

        // Paneli düzenle
        setLayout(new BorderLayout());

        // JTable ile verileri ekle
        table = new JTable(data, columnNames);
        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

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
    }

    public void showMutfakInfo(List<Siparis> yemekler) {
        // Verileri güncelle
        for (int i = 0; i < yemekler.size(); i++) {
            System.out.println(yemekler.get(i).isHazırlandı());
            data[i][0] = yemekler.get(i).getUrun();
            data[i][1] = yemekler.get(i).getKalanHazırlanmaSüresi() + " sn";
            data[i][2] = yemekler.get(i).isHazırlandı() ? "Evet" : "Hayır";
        }

        // Tabloyu yeniden çiz
        table.repaint();
    }
}