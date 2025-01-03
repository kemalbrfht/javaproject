import java.awt.*;
import javax.swing.*;

public class giris extends JFrame {

    public giris() {
        // Pencere başlığını ayarla
        setTitle("Restoran Uygulaması - Giriş");

        // Uygulamayı tam ekran yap
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Arka plan resmini ayarla
        JLabel background = new JLabel();
        background.setLayout(new BorderLayout());
        setContentPane(background);

        // Resmi ölçeklendirme ve ayarlama
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\HP\\OneDrive - ISTANBUL SAGLIK VE TEKNOLOJI UNIVERSITESI\\Masaüstü\\javaproject\\RestaurantOtomasyon\\src\\images\\background.png");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Image scaledImage = originalIcon.getImage().getScaledInstance((int) screenSize.getWidth(), (int) screenSize.getHeight(), Image.SCALE_SMOOTH);
        background.setIcon(new ImageIcon(scaledImage));

        // Üstüne yazılar ekle
       

        // Animasyonlu bir yazı ekle
        JLabel animatedLabel = new JLabel("Hoş Geldiniz", SwingConstants.CENTER);
        animatedLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        animatedLabel.setForeground(Color.WHITE);
        background.add(animatedLabel, BorderLayout.CENTER);

        // Start butonu ekle
        JButton startButton = new JButton("BAŞLAT");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(new Color(38, 34, 31));
        startButton.setFocusPainted(false);
        background.add(startButton, BorderLayout.SOUTH);

        JButton startButton2 = new JButton();
        startButton2.setForeground(Color.WHITE);
        startButton2.setBackground(new Color(38, 34, 31));
        background.add(startButton2, BorderLayout.NORTH);

        // Animasyon başlat
        new Thread(() -> {
            try {
                while (true) {
                    for (int i = 0; i <= 255; i++) {
                        animatedLabel.setForeground(new Color(i, i, i));
                        startButton.setForeground(new Color(i, i, i));
                        Thread.sleep(10);
                    }
                    for (int i = 255; i >= 0; i--) {
                        animatedLabel.setForeground(new Color(i, i, i));
                        startButton.setForeground(new Color(i, i, i));
                        Thread.sleep(10);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // Buton için tıklama olayını ekle
        startButton.addActionListener(e -> {
            // Mevcut frame üzerinde MasalarGUI'yi çalıştır
            RestoranYonetimi restoran = new RestoranYonetimi();
            Menu menu = new Menu();
            new MasalarGUI(this, restoran, menu);
        });


        // Görünürlüğü ayarla
        setVisible(true);
    }
}


//"C:\\Users\\HP\\OneDrive - ISTANBUL SAGLIK VE TEKNOLOJI UNIVERSITESI\\Masaüstü\\javaproject\\RestaurantOtomasyon\\src\\images\\background.png"("C:\\Users\\HP\\OneDrive - ISTANBUL SAGLIK VE TEKNOLOJI UNIVERSITESI\\Masaüstü\\javaproject\\RestaurantOtomasyon\\src\\images\\background.png");