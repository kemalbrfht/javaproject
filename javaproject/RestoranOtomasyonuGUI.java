
import javax.swing.*;
import java.awt.*;

public class RestoranOtomasyonuGUI {
    private JFrame frame;
    private RestoranYonetimi restoran;
    private Menu menu;

    public RestoranOtomasyonuGUI() {
        restoran = new RestoranYonetimi();
        menu = new Menu();
        frame = new JFrame("Restoran Otomasyonu");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        initMainMenu();
        frame.setVisible(true);
    }

    public void initMainMenu() {
        JPanel mainMenu = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton btnMasalar = new JButton("Masaları Listele");
        JButton btnMenu = new JButton("Menüyü Görüntüle");
        JButton btnCikis = new JButton("Çıkış");

        btnMasalar.addActionListener(e -> new MasalarGUI(frame, restoran, menu));
        btnMenu.addActionListener(e -> new MenuGUI(frame, menu));
        btnCikis.addActionListener(e -> System.exit(0));

        mainMenu.add(btnMasalar);
        mainMenu.add(btnMenu);
        mainMenu.add(btnCikis);

        frame.setContentPane(mainMenu);
        frame.revalidate();
    }
}
