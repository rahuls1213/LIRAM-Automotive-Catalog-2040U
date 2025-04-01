import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CarViewer {
    private JFrame frame;
    private JPanel carPanel;
    private JPanel controlPanel, buttonPanel;
    private JTextField searchField;
    private JComboBox<String> sortDropdown;
    private JButton nextPageButton, prevPageButton, searchButton, homeButton, loginButton, viewFavoritesButton;
    private List<Vehicle> vehicles;
    private List<Vehicle> originalVehicles;
    private int currentPage = 0;
    private final int CARS_PER_PAGE = 3;
    private boolean isDarkMode = false;

    // Theme Colors
    private final Color BACKGROUND_COLOR = new Color(245, 245, 220); // Beige
    private final Color CARD_COLOR = Color.WHITE;
    private final Color TEXT_PRIMARY = new Color(26, 26, 64); // Navy
    private final Color TEXT_SECONDARY = new Color(51, 51, 51);
    private final Color BUTTON_BG = new Color(26, 26, 64);
    private final Color BUTTON_TEXT = Color.WHITE;
    private final Font HEADER_FONT = new Font("SansSerif", Font.BOLD, 18);
    private final Font BODY_FONT = new Font("SansSerif", Font.PLAIN, 14);

    public CarViewer(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
        this.originalVehicles = new ArrayList<>(vehicles);

        frame = new JFrame("Car Viewer");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(BACKGROUND_COLOR);

        // Control panel
        controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));
        controlPanel.setBackground(BACKGROUND_COLOR);

        JToggleButton themeToggle = new JToggleButton("Dark Mode");
        themeToggle.setFont(BODY_FONT);
        themeToggle.setBackground(Color.WHITE);
        themeToggle.setForeground(TEXT_PRIMARY);
        themeToggle.setFocusPainted(false);
        themeToggle.setBorder(BorderFactory.createLineBorder(TEXT_PRIMARY));
        themeToggle.addActionListener(e -> toggleTheme(themeToggle));

        searchField = new JTextField(12);
        searchButton = createButton("Search");
        homeButton = createButton("Home");
        viewFavoritesButton = createButton("View Favorites");
        loginButton = createButton("Login");
        sortDropdown = new JComboBox<>(new String[]{"Sort by Make", "Sort by Year"});
        sortDropdown.setFont(BODY_FONT);

        controlPanel.add(themeToggle);
        controlPanel.add(new JLabel("Search:"));
        controlPanel.add(searchField);
        controlPanel.add(searchButton);
        controlPanel.add(homeButton);
        controlPanel.add(viewFavoritesButton);
        controlPanel.add(sortDropdown);
        controlPanel.add(loginButton);

        frame.add(controlPanel, BorderLayout.NORTH);

        carPanel = new JPanel();
        carPanel.setBackground(BACKGROUND_COLOR);
        carPanel.setLayout(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(carPanel);
        scrollPane.setBorder(null);
        frame.add(scrollPane, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        buttonPanel.setBackground(BACKGROUND_COLOR);
        prevPageButton = createButton("<");
        nextPageButton = createButton(">");
        buttonPanel.add(prevPageButton);
        buttonPanel.add(nextPageButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        prevPageButton.addActionListener(e -> changePage(-1));
        nextPageButton.addActionListener(e -> changePage(1));
        searchButton.addActionListener(e -> searchCars());
        homeButton.addActionListener(e -> resetToHomePage());
        sortDropdown.addActionListener(e -> sortCars());
        viewFavoritesButton.addActionListener(e -> viewFavorites());

        displayCars();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void displayCars() {
        carPanel.removeAll();
        int start = currentPage * CARS_PER_PAGE;
        int end = Math.min(start + CARS_PER_PAGE, vehicles.size());

        for (int i = start; i < end; i++) {
            Vehicle car = vehicles.get(i);

            JPanel card = new JPanel(new BorderLayout(10, 10));
            // card.setMaximumSize(new Dimension(850, 160));
            card.setPreferredSize(new Dimension(0, 160)); // width = auto-grow
            card.setBackground(CARD_COLOR);
            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));

            JLabel imageLabel = new JLabel();
            imageLabel.setPreferredSize(new Dimension(200, 130));
            imageLabel.setIcon(new ImageIcon(new ImageIcon(car.getImageUrl()).getImage().getScaledInstance(200, 130, Image.SCALE_SMOOTH)));
            card.add(imageLabel, BorderLayout.WEST);

            JPanel details = new JPanel();
            details.setBackground(CARD_COLOR);
            details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));
            JLabel title = new JLabel(car.getMake() + " " + car.getModel());
            title.setFont(HEADER_FONT);
            title.setForeground(TEXT_PRIMARY);
            JLabel yearFuel = new JLabel("Year: " + car.getYear() + " | Fuel: " + car.getFuelType());
            yearFuel.setFont(BODY_FONT);
            yearFuel.setForeground(TEXT_SECONDARY);
            JLabel priceLabel = new JLabel("Price: " + car.getPrice());
            priceLabel.setFont(BODY_FONT);
            priceLabel.setForeground(TEXT_SECONDARY);
            JLabel review = new JLabel("<html><i>" + car.getReview() + "</i></html>");
            review.setFont(BODY_FONT);
            review.setForeground(TEXT_SECONDARY);
            details.add(title);
            details.add(yearFuel);
            details.add(priceLabel);
            details.add(review);
            card.add(details, BorderLayout.CENTER);

            JPanel actions = new JPanel();
            actions.setBackground(CARD_COLOR);
            JButton favButton = createButton("Add to Favorites");
            JButton compareButton = createButton("Compare");
            actions.add(favButton);
            actions.add(compareButton);
            card.add(actions, BorderLayout.SOUTH);

            favButton.addActionListener(e -> addToFavorites(car));
            compareButton.addActionListener(e -> showComparison(car));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = i - start; // row index
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(10, 0, 10, 0);

            carPanel.add(card, gbc);
        }

        carPanel.revalidate();
        carPanel.repaint();
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(BUTTON_BG);
        btn.setForeground(BUTTON_TEXT);
        btn.setFocusPainted(false);
        btn.setFont(BODY_FONT);
        btn.setBorder(BorderFactory.createEmptyBorder(6, 14, 6, 14));
        return btn;
    }

    private void toggleTheme(JToggleButton toggleButton) {
        isDarkMode = !isDarkMode;
        toggleButton.setText(isDarkMode ? "Light Mode" : "Dark Mode");
        frame.getContentPane().setBackground(isDarkMode ? Color.DARK_GRAY : BACKGROUND_COLOR);
        controlPanel.setBackground(isDarkMode ? Color.DARK_GRAY : BACKGROUND_COLOR);
        buttonPanel.setBackground(isDarkMode ? Color.DARK_GRAY : BACKGROUND_COLOR);
        carPanel.setBackground(isDarkMode ? Color.DARK_GRAY : BACKGROUND_COLOR);
        displayCars();
    }

    private void changePage(int delta) {
        int maxPage = (vehicles.size() - 1) / CARS_PER_PAGE;
        currentPage = Math.max(0, Math.min(maxPage, currentPage + delta));
        displayCars();
    }

    private void searchCars() {
        String query = searchField.getText().toLowerCase();
        vehicles = originalVehicles.stream()
                .filter(car -> car.getMake().toLowerCase().contains(query) ||
                               car.getModel().toLowerCase().contains(query))
                .toList();
        currentPage = 0;
        displayCars();
    }

    private void resetToHomePage() {
        vehicles = new ArrayList<>(originalVehicles);
        currentPage = 0;
        displayCars();
    }

    private void sortCars() {
        if (sortDropdown.getSelectedItem().equals("Sort by Make")) {
            vehicles.sort(Comparator.comparing(Vehicle::getMake));
        } else {
            vehicles.sort(Comparator.comparingInt(Vehicle::getYear));
        }
        currentPage = 0;
        displayCars();
    }

    private void viewFavorites() {
        if (Session.currentUser == null || Session.currentUser.getFavorites().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No favorite vehicles.");
            return;
        }
        vehicles = new ArrayList<>(Session.currentUser.getFavorites());
        currentPage = 0;
        displayCars();
    }

    private void addToFavorites(Vehicle vehicle) {
        if (Session.currentUser == null) {
            JOptionPane.showMessageDialog(frame, "Please log in to use favorites.");
            return;
        }
        User user = Session.currentUser;
        if (user.isFavorite(vehicle)) {
            user.removeFavorite(vehicle);
            JOptionPane.showMessageDialog(frame, vehicle.getMake() + " " + vehicle.getModel() + " removed from favorites.");
        } else {
            user.addFavorite(vehicle);
            JOptionPane.showMessageDialog(frame, vehicle.getMake() + " " + vehicle.getModel() + " added to favorites!");
        }
        UserDatabase.saveUsers();
        displayCars();
    }

    private void showComparison(Vehicle currentCar) {
        List<String> options = new ArrayList<>();
        List<Vehicle> candidates = new ArrayList<>();
        for (Vehicle v : originalVehicles) {
            if (!v.equals(currentCar)) {
                options.add(v.getMake() + " " + v.getModel());
                candidates.add(v);
            }
        }
        Object selection = JOptionPane.showInputDialog(
                frame, "Select a car to compare with:",
                "Compare Vehicles",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options.toArray(),
                options.get(0)
        );
        int index = options.indexOf(selection);
        if (index >= 0) {
            new CarComparisonWindow(currentCar, candidates.get(index));
        }
    }
}
