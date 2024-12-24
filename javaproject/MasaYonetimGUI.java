
import javax.swing.*;
import java.awt.*;

public class MasaYonetimGUI {
    public MasaYonetimGUI(JFrame frame, Masa masa, RestoranYonetimi restoran, Menu menu) {
        JPanel masaDetay = new JPanel(new GridLayout(4, 1, 10, 10));

        JButton btnSiparisVer = new JButton("Sipariş Ver");
        JButton btnSiparisDurumu = new JButton("Sipariş Durumu");
        JButton btnHesapOde = new JButton("Hesap Öde");
        JButton btnBack = new JButton("Geri Dön");

        btnSiparisVer.addActionListener(e -> new SiparisVerGUI(frame, masa, menu));
        btnSiparisDurumu.addActionListener(e -> new SiparisDurumuGUI(frame, masa));
        btnHesapOde.addActionListener(e -> {
            double toplamHesap = masa.hesaplaHesap();
            int confirm = JOptionPane.showConfirmDialog(frame, "Toplam hesap: " + toplamHesap + " TL\nHesap ödensin mi?", "Ödeme Yap", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                masa.masaKapat();
                JOptionPane.showMessageDialog(frame, "Ödeme alındı. Masa " + masa.getMasaNo() + " boşaltıldı.");
                new MasalarGUI(frame, restoran, menu); // Masalar ekranına geri dön
            }
        });

        btnBack.addActionListener(e -> new MasalarGUI(frame, restoran, menu)); // Masalar ekranına dön

        masaDetay.add(btnSiparisVer);
        masaDetay.add(btnSiparisDurumu);
        masaDetay.add(btnHesapOde);
        masaDetay.add(btnBack);

        frame.setContentPane(masaDetay);
        frame.revalidate();
    }
}
