import javax.swing.*;

public class LoginScreen {

    public static boolean prompt() {
        String username = JOptionPane.showInputDialog("Username:");
        String password = JOptionPane.showInputDialog("Password:");

        if (UserAuthService.login(username, password)) {
            JOptionPane.showMessageDialog(null, "✅ Login successful!");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "❌ Login failed. Try again.");
            return false;
        }
    }

    public static void showRegisterPrompt() {
        String username = JOptionPane.showInputDialog("Choose a Username:");
        String password = JOptionPane.showInputDialog("Choose a Password:");

        if (UserAuthService.register(username, password)) {
            JOptionPane.showMessageDialog(null, "✅ Registration successful!");
        } else {
            JOptionPane.showMessageDialog(null, "❌ That username already exists or input is invalid.");
        }
    }
}
