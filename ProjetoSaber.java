

import login.FormularioLogin;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ProjetoSaber {

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FormularioLogin();
            }
        });
    }
}