import javax.swing.*;

/**
 * LoginScreen provides static utility methods for user login and registration
 * via simple modal input dialogs using Java Swing.
 *
 * This class supports the login/registration workflow in the LandingPage and CarViewer GUIs.
 *
 * Uses {@link UserAuthService} to handle secure password verification,
 * and interacts with {@link Session} to update the active user session.
 */
public class LoginScreen {

    /**
     * Displays a login dialog to collect a username and password.
     * If credentials are valid, the user session is updated.
     *
     * @return true if login is successful, false otherwise (cancelled or invalid credentials)
     */
    public static boolean prompt() {
        // Ask for username input
        String username = JOptionPane.showInputDialog("Username:");
        if (username == null || username.isBlank()) {
            return false; // User cancelled or entered nothing
        }

        // Ask for password input
        String password = JOptionPane.showInputDialog("Password:");
        if (password == null || password.isBlank()) {
            return false; // User cancelled or entered nothing
        }

        // Attempt authentication
        boolean success = UserAuthService.login(username, password);
        if (success) {
            // Save user in session if successful
            Session.currentUser = UserDatabase.getUser(username);  // Track the logged-in user
            JOptionPane.showMessageDialog(null, "Login successful!");
        }
        return success;
    }

    /**
     * Displays a registration dialog to create a new user account.
     * Validates input and notifies the user of success or failure.
     */
    public static void showRegisterPrompt() {
        // Ask for username
        String username = JOptionPane.showInputDialog("Choose a Username:");
        if (username == null || username.isBlank()) return;

        // Ask for password
        String password = JOptionPane.showInputDialog("Choose a Password:");
        if (password == null || password.isBlank()) return;

        // Try to register using UserAuthService
        if (UserAuthService.register(username, password)) {
            JOptionPane.showMessageDialog(null, "Registration successful!");
        } else {
            JOptionPane.showMessageDialog(null, "That username already exists or is invalid.");
        }
    }
}
