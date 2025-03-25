import javax.swing.*;

public class LoginScreen {
    public static boolean prompt() {
        String username = JOptionPane.showInputDialog("Username:");
        if (username == null || username.isBlank()) {
            return false; // User cancelled or entered nothing
        }

        String password = JOptionPane.showInputDialog("Password:");
        if (password == null || password.isBlank()) {
            return false; // User cancelled or entered nothing
        }

        boolean success = UserAuthService.login(username, password);
        if (success) {
            Session.currentUser = UserDatabase.getUser(username);  // ✅ Track the logged-in user
            JOptionPane.showMessageDialog(null, "✅ Login successful!");
        }
        return success;
    }

    public static void showRegisterPrompt() {
        String username = JOptionPane.showInputDialog("Choose a Username:");
        if (username == null || username.isBlank()) return;

        String password = JOptionPane.showInputDialog("Choose a Password:");
        if (password == null || password.isBlank()) return;

        if (UserAuthService.register(username, password)) {
            JOptionPane.showMessageDialog(null, "✅ Registration successful!");
        } else {
            JOptionPane.showMessageDialog(null, "❌ That username already exists or is invalid.");
        }
    }
}
