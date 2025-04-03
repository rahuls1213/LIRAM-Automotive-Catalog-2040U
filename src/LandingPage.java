import javax.swing.*;
import java.awt.*;


/**
 * LandingPage displays the introductory screen of the Automotive Catalog application.
 *
 * This GUI class handles:
 * - Initial data loading (users and vehicles)
 * - Displaying the project logo and title
 * - Providing options to log in, register, or continue as a guest
 *
 * It serves as the entry point to the application UI and provides a simple, visually
 * welcoming design consistent with the LIRAM Catalog theme (beige, black, white).
 *
 * Triggered on startup by the Main class.
 */
public class LandingPage {
    private JFrame frame; // Main application window

    /**
     * Constructs and displays the landing page.
     * Loads users and vehicles, shows the logo and title,
     * and renders action buttons for login, registration, or guest access.
     */
    public LandingPage() {
        // Load user and vehicle data from JSON files
        VehicleDatabase.loadVehicles();
        UserDatabase.loadUsers();
        
        // UI Theme Colors
        Color backgroundColor = new Color(240, 240, 235); // Beige
        Color buttonColor = new Color(30, 30, 30);         // Black
        Color textColor = new Color(255, 255, 255);        // White

        // Main Application Frame Setup
        frame = new JFrame("Welcome to Auto Catalog");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setMinimumSize(new Dimension(600, 400));

        // Main Content Panel with Grid Layout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(backgroundColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 20, 15, 20); // Padding between items

        // Logo (scaled from image file)
        ImageIcon rawIcon = new ImageIcon("images/Liram1.png");
        Image scaledImage = rawIcon.getImage().getScaledInstance(300, 180, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(logoLabel, gbc);

        // Title Label
        JLabel title = new JLabel("Welcome to LIRAM Catalog!");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(Color.BLACK);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 1;
        mainPanel.add(title, gbc);

        // Buttons for user options
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        JButton guestButton = new JButton("Continue as Guest");

        // Style all buttons consistently
        for (JButton btn : new JButton[]{loginButton, registerButton, guestButton}) {
            btn.setBackground(buttonColor);
            btn.setForeground(textColor);
            btn.setFocusPainted(false);
            btn.setFont(new Font("SansSerif", Font.BOLD, 16));
            btn.setPreferredSize(new Dimension(300, 50));
        }

        // Add buttons to panel with vertical spacing
        gbc.gridy = 2; mainPanel.add(loginButton, gbc);
        gbc.gridy = 3; mainPanel.add(registerButton, gbc);
        gbc.gridy = 4; mainPanel.add(guestButton, gbc);

        // Button logic: Login
        loginButton.addActionListener(e -> {
            boolean success = LoginScreen.prompt();
            if (success) {
                frame.dispose();
                launchApp();
            } else {
                JOptionPane.showMessageDialog(null, "❌ Login failed. Try again.");
            }
        });

        // Buttong logic: Register
        registerButton.addActionListener(e -> LoginScreen.showRegisterPrompt());

        // Button logic: Continue as Guest
        guestButton.addActionListener(e -> {
            frame.dispose();
            launchApp();
        });

        // Finalize and show Window
        frame.setContentPane(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Launches the main vehicle browsing UI after login or guest access.
     */
    private void launchApp() {
        new CarViewer(VehicleDatabase.getVehicles());
    }
}