import javax.swing.*;
import java.awt.*;

public class LandingPage {
    private JFrame frame;

    public LandingPage() {
        UserDatabase.loadUsers();
        VehicleDatabase.loadVehicles();

        Color backgroundColor = new Color(240, 240, 235); // Beige from logo
        Color buttonColor = new Color(30, 30, 30);         // Black
        Color textColor = new Color(255, 255, 255);        // White

        frame = new JFrame("Welcome to Auto Catalog");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setMinimumSize(new Dimension(600, 400));

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(backgroundColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 20, 15, 20); // Padding between items

        ImageIcon rawIcon = new ImageIcon("images/Liram1.png");
        Image scaledImage = rawIcon.getImage().getScaledInstance(300, 180, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(logoLabel, gbc);

        JLabel title = new JLabel("Welcome to LIRAM Catalog!");
        title.setFont(new Font("Serif", Font.BOLD, 26));
        title.setForeground(Color.BLACK);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 1;
        mainPanel.add(title, gbc);

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        JButton guestButton = new JButton("Continue as Guest");

        for (JButton btn : new JButton[]{loginButton, registerButton, guestButton}) {
            btn.setBackground(buttonColor);
            btn.setForeground(textColor);
            btn.setFocusPainted(false);
            btn.setFont(new Font("SansSerif", Font.BOLD, 16));
            btn.setPreferredSize(new Dimension(300, 50));
        }

        gbc.gridy = 2; mainPanel.add(loginButton, gbc);
        gbc.gridy = 3; mainPanel.add(registerButton, gbc);
        gbc.gridy = 4; mainPanel.add(guestButton, gbc);

        loginButton.addActionListener(e -> {
            boolean success = LoginScreen.prompt();
            if (success) {
                frame.dispose();
                launchApp();
            } else {
                JOptionPane.showMessageDialog(null, "❌ Login failed. Try again.");
            }
        });

        registerButton.addActionListener(e -> LoginScreen.showRegisterPrompt());
        guestButton.addActionListener(e -> {
            frame.dispose();
            launchApp();
        });

        frame.setContentPane(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void launchApp() {
        new CarViewer(VehicleDatabase.getVehicles());
    }
}