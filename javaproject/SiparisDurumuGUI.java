
import javax.swing.*;
import java.awt.*;

public class SiparisDurumuGUI {
    public SiparisDurumuGUI(JFrame frame, Masa masa) {
        JPanel panelDurum = new JPanel();
        panelDurum.setLayout(new BoxLayout(panelDurum, BoxLayout.Y_AXIS));

        if (masa.getSiparisler().isEmpty()) {
            JLabel label = new JLabel("Bu masada henüz sipariş yok.");
            panelDurum.add(label);
        } else {
            for (Siparis siparis : masa.getSiparisler()) {
                JLabel label = new JLabel(siparis.getKategori() + ": " + siparis.getUrun() + " (" + siparis.getFiyat() + " TL)");
                panelDurum.add(label);
            }
        }

        JButton btnBack = new JButton("Geri Dön");
        btnBack.addActionListener(e -> new MasaYonetimGUI(frame, masa, null, null));
        panelDurum.add(btnBack);

        frame.setContentPane(panelDurum);
        frame.revalidate();
    }
}
