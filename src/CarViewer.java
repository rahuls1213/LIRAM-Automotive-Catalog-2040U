import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * CarViewer is the main user interface for browsing, searching, sorting,
 * favoriting, and comparing vehicles within the automotive catalog system.
 *
 * This class fulfills multiple high-priority user stories such as:
 * - Browsing and viewing car listings
 * - Searching and filtering by model/make
 * - Sorting cars by attributes
 * - Adding/removing cars to a favorites list
 * - Comparing two vehicles side by side
 * - Switching between light/dark UI themes
 *
 * UI elements are styled to match the branding (beige/navy theme),
 * and functionality aligns with the project planning documents and agile iteration goals.
 */
public class CarViewer {
    // Frame and panel references
    private JFrame frame;
    private JPanel carPanel;
    private JPanel controlPanel, buttonPanel;

    // Search, sorting, login, and page navigation components
    private JTextField searchField;
    private JComboBox<String> sortDropdown;
    private JButton nextPageButton, prevPageButton, searchButton, homeButton, loginButton, viewFavoritesButton;
    
    // Vehicle data
    private List<Vehicle> vehicles;
    private List<Vehicle> originalVehicles;

    // Pagination state
    private int currentPage = 0;
    private final int CARS_PER_PAGE = 3;

    // Theme toggle
    private boolean isDarkMode = false;

    // Theme Constants
    private final Color BACKGROUND_COLOR = new Color(245, 245, 220); // Beige
    private final Color CARD_COLOR = Color.WHITE;
    private final Color TEXT_PRIMARY = new Color(26, 26, 64); // Navy
    private final Color TEXT_SECONDARY = new Color(51, 51, 51);
    private final Color BUTTON_BG = new Color(26, 26, 64);
    private final Color BUTTON_TEXT = Color.WHITE;
    private final Font HEADER_FONT = new Font("SansSerif", Font.BOLD, 18);
    private final Font BODY_FONT = new Font("SansSerif", Font.PLAIN, 14);
    private final Color NAVY_DARK = new Color(26, 26, 64);  // #1A1A40

    /**
     * Constructs the CarViewer window and initializes the user interface layout,
     * buttons, search/sort features, and event listeners.
     *
     * @param vehicles the list of vehicle objects to display initially
     */
    public CarViewer(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
        this.originalVehicles = new ArrayList<>(vehicles);

        // Frame setup
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
        themeToggle.setBackground(BUTTON_BG);
        themeToggle.setForeground(BUTTON_TEXT);
        themeToggle.setFocusPainted(false);
        themeToggle.setBorder(BorderFactory.createEmptyBorder(6, 14, 6, 14));
        themeToggle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        themeToggle.setContentAreaFilled(true);
        themeToggle.setOpaque(true);
        themeToggle.setBorderPainted(false);
        themeToggle.addActionListener(e -> toggleTheme(themeToggle));

        searchField = new JTextField(12);
        searchField.setFont(BODY_FONT);
        searchField.setBackground(NAVY_DARK);
        searchField.setForeground(Color.WHITE);
        searchField.setCaretColor(Color.WHITE);
        searchField.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));

        searchButton = createButton("Search");
        homeButton = createButton("Home");
        viewFavoritesButton = createButton("View Favorites");
        loginButton = createButton(getLoginButtonText());

        sortDropdown = new JComboBox<>(new String[]{"Sort by Make", "Sort by Year", "Sort by Price"});
        sortDropdown.setFont(BODY_FONT);
        sortDropdown.setBackground(NAVY_DARK);
        sortDropdown.setForeground(Color.WHITE);
        sortDropdown.setFocusable(false);
        sortDropdown.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));

        controlPanel.add(themeToggle);
        controlPanel.add(new JLabel("Search:"));
        controlPanel.add(searchField);
        controlPanel.add(searchButton);
        controlPanel.add(homeButton);
        controlPanel.add(viewFavoritesButton);
        controlPanel.add(sortDropdown);
        controlPanel.add(loginButton);

        loginButton.addActionListener(e -> {
            if (Session.currentUser == null) {
                boolean success = LoginScreen.prompt();
                if (success) {
                    JOptionPane.showMessageDialog(frame, "You are now logged in as " + Session.currentUser.getUsername());
                } else {
                    JOptionPane.showMessageDialog(frame, "Login failed. Try again.");
                    return; // Don't refresh UI
                }
            } else {
                int confirm = JOptionPane.showConfirmDialog(frame, "Log out from " + Session.currentUser.getUsername() + "?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    Session.currentUser = null;
                    JOptionPane.showMessageDialog(frame, "You have been logged out.");
                } else {
                    return; // Don't refresh UI
                }
            }
            loginButton.setText(getLoginButtonText());
            displayCars(); // Refresh UI to update favorites, etc.
        });        

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

    /**
     * Displays the current set of vehicles in paginated card format using a GridBag layout.
     * Each card includes the vehicle's image, info, and buttons to favorite or compare.
     */
    private void displayCars() {
        carPanel.removeAll();
        int start = currentPage * CARS_PER_PAGE;
        int end = Math.min(start + CARS_PER_PAGE, vehicles.size());

        for (int i = start; i < end; i++) {
            Vehicle car = vehicles.get(i);

            JPanel card = new JPanel(new BorderLayout(10, 10));
    
            card.setPreferredSize(new Dimension(0, 160));
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
            gbc.gridy = i - start; 
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(10, 0, 10, 0);

            carPanel.add(card, gbc);
        }

        carPanel.revalidate();
        carPanel.repaint();
    }

    /**
     * Utility method to create a reusable styled JButton with consistent look and feel.
     *
     * @param text the button label
     * @return JButton styled for the catalog's theme
     */
    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(BUTTON_BG);
        btn.setForeground(BUTTON_TEXT);
        btn.setFocusPainted(false);
        btn.setFont(BODY_FONT);
        btn.setBorder(BorderFactory.createEmptyBorder(6, 14, 6, 14));
        return btn;
    }

    /**
     * Toggles between light and dark themes and updates background colors accordingly.
     * Also restyles the toggle button itself.
     *
     * @param toggleButton the UI toggle button that triggered the theme switch
     */
    private void toggleTheme(JToggleButton toggleButton) {
        isDarkMode = !isDarkMode;
    
        Color newBg = isDarkMode ? Color.DARK_GRAY : BACKGROUND_COLOR;
    
        // Update app backgrounds
        frame.getContentPane().setBackground(newBg);
        controlPanel.setBackground(newBg);
        buttonPanel.setBackground(newBg);
        carPanel.setBackground(newBg);
    
        // Fully restyle the toggle button
        toggleButton.setText(isDarkMode ? "Light Mode" : "Dark Mode");
        toggleButton.setBackground(BUTTON_BG); 
        toggleButton.setForeground(BUTTON_TEXT); 
        toggleButton.setFont(BODY_FONT);
        toggleButton.setFocusPainted(false);
        toggleButton.setContentAreaFilled(true);
        toggleButton.setOpaque(true);
        toggleButton.setBorderPainted(false);
        toggleButton.setBorder(BorderFactory.createEmptyBorder(6, 14, 6, 14));
        toggleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    
        displayCars();
    }
        
    /**
     * Changes the current page of displayed cars by a given offset.
     * Ensures page stays within valid bounds.
     *
     * @param delta the page increment (positive or negative)
     */
    private void changePage(int delta) {
        int maxPage = (vehicles.size() - 1) / CARS_PER_PAGE;
        currentPage = Math.max(0, Math.min(maxPage, currentPage + delta));
        displayCars();
    }

    /**
     * Filters the vehicles list based on the user query entered in the search bar.
     * Matches vehicle make or model in a case-insensitive manner.
     */
    private void searchCars() {
        String query = searchField.getText().toLowerCase();
        vehicles = originalVehicles.stream()
                .filter(car -> car.getMake().toLowerCase().contains(query) ||
                               car.getModel().toLowerCase().contains(query))
                .toList();
        currentPage = 0;
        displayCars();
    }

    /**
     * Resets the car display to the original full vehicle list and goes to the first page.
     */
    private void resetToHomePage() {
        vehicles = new ArrayList<>(originalVehicles);
        currentPage = 0;
        displayCars();
    }

    /**
     * Sorts the displayed vehicles based on user-selected criteria:
     * - Make (alphabetically)
     * - Year (ascending)
     * - Price (numerically, parsed from string)
     */
    private void sortCars() {
        String selected = (String) sortDropdown.getSelectedItem();
        if ("Sort by Make".equals(selected)) {
            vehicles.sort(Comparator.comparing(Vehicle::getMake));
        } else if ("Sort by Year".equals(selected)) {
            vehicles.sort(Comparator.comparingInt(Vehicle::getYear));
        } else if ("Sort by Price".equals(selected)) {
            vehicles.sort(Comparator.comparingDouble(car -> {
                try {
                    return Double.parseDouble(car.getPrice().replaceAll("[^\\d.]", ""));
                } catch (NumberFormatException e) {
                    return Double.MAX_VALUE; // Put invalid or missing prices at the end
                }
            }));
        }
        currentPage = 0;
        displayCars();
    }    

    /**
     * Displays only the current user's favorite vehicles.
     * If not logged in or no favorites exist, shows an alert instead.
     */
    private void viewFavorites() {
        if (Session.currentUser == null || Session.currentUser.getFavorites().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No favorite vehicles.");
            return;
        }
        vehicles = new ArrayList<>(Session.currentUser.getFavorites());
        currentPage = 0;
        displayCars();
    }

    /**
     * Adds or removes a vehicle from the current user's favorites list.
     * If not logged in, prompts the user to log in first.
     *
     * @param vehicle the vehicle to add or remove from favorites
     */
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

    /**
     * Opens a comparison dialog between the selected car and another user-selected car.
     * Prompts the user to select a second car via dropdown dialog.
     *
     * @param currentCar the car currently being viewed or interacted with
     */
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

    /**
     * Returns the appropriate text label for the login/logout button based on session state.
     *
     * @return "Login" if no user is logged in, otherwise "Logout"
     */
    private String getLoginButtonText() {
        return Session.currentUser == null ? "Login" : "Logout";
    }    
}