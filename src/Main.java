import javax.swing.SwingUtilities;

import gui.LoginScreen;

public class Main {
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(LoginScreen::new);
    }
}
