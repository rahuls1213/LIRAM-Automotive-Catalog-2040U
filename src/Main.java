import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Apply FlatLaf Theme
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.out.println("FlatLaf not found, falling back to default.");
            e.printStackTrace();
        }

        // Load users first
        UserDatabase.loadUsers();

        // Prompt: Login or Register
        Object[] options = {"Login", "Register"};
        int choice = JOptionPane.showOptionDialog(null, "Welcome to the Automotive Catalog!\nChoose an option:",
                "Authentication", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);

        if (choice == 1) {
            // Register
            LoginScreen.showRegisterPrompt();
        }

        if (choice == 0 && LoginScreen.prompt()) {
            // Load vehicles and show GUI AFTER successful login
            VehicleDatabase.loadVehicles();
            SwingUtilities.invokeLater(() -> new CarViewer(VehicleDatabase.getVehicles()));
        } else if (choice != 0) {
            // Exit if the user closed the dialog or didn't log in
            System.exit(0);
        }
    }
}
