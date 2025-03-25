import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.out.println("FlatLaf not found, falling back to default.");
            e.printStackTrace();
        }

        VehicleDatabase.loadVehicles();  // ✅ Load vehicles first!
        UserDatabase.loadUsers();        // ✅ Now users can load their favorites properly

        Object[] options = {"Login / Register", "Continue as Guest"};
        int choice = JOptionPane.showOptionDialog(null,
                "Welcome to the Automotive Catalog!\nWould you like to log in?",
                "Authentication", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        boolean allowAccess = false;

        if (choice == 0) { // Login/Register path
            boolean loggedIn = false;

            while (!loggedIn) {
                Object[] authOptions = {"Login", "Register", "Cancel"};
                int authChoice = JOptionPane.showOptionDialog(null,
                        "Choose an option:",
                        "Login or Register",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        null, authOptions, authOptions[0]);

                if (authChoice == 1) {
                    LoginScreen.showRegisterPrompt();
                } else if (authChoice == 0) {
                    loggedIn = LoginScreen.prompt();
                    if (!loggedIn) {
                        JOptionPane.showMessageDialog(null, "❌ Login failed. Try again.");
                    }
                } else {
                    break; // user clicked Cancel
                }
            }

            allowAccess = loggedIn;
        } else if (choice == 1 || choice == JOptionPane.CLOSED_OPTION) {
            allowAccess = true;
        }

        if (allowAccess) {
            SwingUtilities.invokeLater(() -> new CarViewer(VehicleDatabase.getVehicles()));
        } else {
            JOptionPane.showMessageDialog(null, "Exiting application.");
            System.exit(0);
        }
    }
}