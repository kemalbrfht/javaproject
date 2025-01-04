package gui;

import javax.swing.*;

import controllers.*;
import models.Menu;

import java.awt.*;

public class RestoranOtomasyonuGUI {
    private JFrame frame;
    private RestoranYonetimi restoran;
    private Menu menu;

    public RestoranOtomasyonuGUI() {
        restoran = new RestoranYonetimi();
        menu = new Menu();
        frame = new JFrame("Restoran Otomasyonu");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        initMainMenu();
        frame.setVisible(true);
    }

    public void initMainMenu() {
        MasalarGUI masalarGUI = new MasalarGUI(frame, restoran, menu);
        frame.setContentPane((Container) masalarGUI.getPanel());
        frame.revalidate();
    }
}
