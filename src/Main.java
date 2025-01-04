import javax.swing.SwingUtilities;

import gui.giris;

public class Main {
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(giris::new);
    }
}
