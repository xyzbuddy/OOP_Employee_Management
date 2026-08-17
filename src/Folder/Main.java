package Folder;

import GUI.LoginFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
    	
        // মেইন ফাইল রান করলে এখন সবার আগে LoginFrame ওপেন হবে
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
