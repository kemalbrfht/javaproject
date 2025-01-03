import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MutfakGUI extends JPanel {
    private JTable table;
    private JScrollPane scrollPane;
    private String[] columnNames = {"Yemek Adı", "Hazırlanma Süresi", "Hazırlandı"};
    private Object[][] data;

    public MutfakGUI(JFrame frame, RestoranYonetimi restoran, Menu menu, Masa masa) {
        // Mutfakta bulunan yemeklerin listesini al
        List<Siparis> yemekler = masa.getSiparisler();
        data = new Object[yemekler.size()][3];

        // Yemek verilerini tabloya ekle
        for (int i = 0; i < yemekler.size(); i++) {
            data[i][0] = yemekler.get(i).getUrun();
            data[i][1] = yemekler.get(i).getHazırlanmasüresi()+"sn";
            data[i][2] = "Evet";
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
    }

    public void showMutfakInfo(List<Siparis> yemekler) {
        // Verileri güncelle
        for (int i = 0; i < yemekler.size(); i++) {
            data[i][0] = yemekler.get(i).getUrun();
            data[i][1] = " 10 sn";
            data[i][2] = "Evet";
        }

        // Tabloyu yeniden çiz
        table.repaint();
    }
}
