import javax.swing.*;
import java.awt.*;

public class MasaYonetimGUI {
    private JPanel mainPanel;

    public MasaYonetimGUI(JFrame frame, Masa masa, RestoranYonetimi restoran, Menu menu) {
        // Ana Panel
        mainPanel = new JPanel(new BorderLayout());
        // Sol Menü Paneli
        SolMenuPaneliGUI solMenuPanel = new SolMenuPaneliGUI(frame, restoran, menu,masa);

        // Orta Panel (Sipariş Durumu)
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(new SiparisDurumuGUI(frame,restoran,menu, masa).getPanel(), BorderLayout.CENTER);


        // Sağ Panel (Sipariş Ver)
        JPanel rightMenu = new JPanel(new GridLayout(0, 1, 10, 10));
        rightMenu.add(new SiparisVerGUI(frame, masa, menu, restoran).getPanel());

        // Alt Panel (Hesap ve İşlemler)
        JPanel bottomPanel = new JPanel(new GridLayout(1, 3));
        JLabel totalLabel = new JLabel("Toplam: " + masa.hesaplaHesap() + " TL", SwingConstants.RIGHT);
        JButton btnPay = new JButton("Ödeme Yap");

        btnPay.addActionListener(e -> {
            double toplamHesap = masa.hesaplaHesap();
            int confirm = JOptionPane.showConfirmDialog(frame, "Toplam hesap: " + toplamHesap + " TL\nHesap ödensin mi?", "Ödeme Yap", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(frame, "Ödeme alındı. Masa " + masa.getMasaNo() + " boşaltıldı.");
                masa.masaKapat();
                new MasalarGUI(frame, restoran, menu); // Masalar ekranına geri dön
                
            }
        });
     
        //bottomPanel.add(totalLabel);
        //bottomPanel.add(btnPay);

        // Panelleri Ana Panele Ekleyin
        mainPanel.add(solMenuPanel, BorderLayout.WEST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(rightMenu, BorderLayout.EAST);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Frame Ayarları
        frame.setContentPane(mainPanel);
        frame.revalidate();
      
    }
       public JPanel getPanel() {

        return mainPanel;

    }

    
}
