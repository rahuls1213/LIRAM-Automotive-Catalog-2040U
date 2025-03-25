import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.net.URL;

/**
 * CarViewer class creates a GUI for viewing a paginated list of vehicles.
 * Features:
 * - Displays multiple cars per page.
 * - Loads and displays car images.
 * - Allows navigation between pages of vehicles.
 * - Provides search, sorting, and a Home button to reset pagination.
 * - Inline comparison between two vehicles.
 */
public class CarViewer {
    private JFrame frame;
    private JPanel carPanel;
    private JTextField searchField;
    private JComboBox<String> sortDropdown;
    private JButton nextPageButton, prevPageButton, searchButton, homeButton, loginButton, viewFavoritesButton;
    private List<Vehicle> vehicles;
    private List<Vehicle> originalVehicles;
    private List<Vehicle> favorites = new ArrayList<>();
    private int currentPage = 0;
    private final int CARS_PER_PAGE = 3;
    private boolean viewingFavorites = false;
    private boolean isDarkMode = true;

    public CarViewer(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
        this.originalVehicles = new ArrayList<>(vehicles);

        frame = new JFrame("Car Viewer");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // TOP PANEL: Search, Sorting, Home, View Favorites, and Login Buttons
        JPanel controlPanel = new JPanel();
        JToggleButton themeToggle = new JToggleButton("Light Mode");
        themeToggle.setBackground(new Color(30, 30, 30)); // Match dark theme
        themeToggle.setForeground(new Color(187, 134, 252)); // Purple text
        themeToggle.setBorder(BorderFactory.createLineBorder(new Color(187, 134, 252), 2));

        themeToggle.addActionListener(e -> toggleTheme(themeToggle));

        controlPanel.add(themeToggle); // Add toggle button to the top panel

        searchField = new JTextField(10);
        searchButton = new JButton("Search");
        homeButton = new JButton("Home");
        viewFavoritesButton = new JButton("View Favorites");
        String[] sortOptions = {"Sort by Make", "Sort by Year"};
        sortDropdown = new JComboBox<>(sortOptions);
        loginButton = new JButton("Login");

        controlPanel.add(new JLabel("Search:"));
        controlPanel.add(searchField);
        controlPanel.add(searchButton);
        controlPanel.add(homeButton);
        controlPanel.add(viewFavoritesButton);
        controlPanel.add(sortDropdown);
        controlPanel.add(loginButton);

        frame.add(controlPanel, BorderLayout.NORTH);

        // MAIN CONTENT: Vehicle Display Area
        carPanel = new JPanel();
        carPanel.setLayout(new GridLayout(CARS_PER_PAGE, 1));
        frame.add(carPanel, BorderLayout.CENTER);

        // NAVIGATION BUTTONS
        JPanel buttonPanel = new JPanel();

        prevPageButton = new JButton(new ImageIcon("images/prev.png"));
        nextPageButton = new JButton(new ImageIcon("images/next.png"));

        // Make buttons slightly larger
        prevPageButton.setPreferredSize(new Dimension(50, 50));
        nextPageButton.setPreferredSize(new Dimension(50, 50));

        // Remove button borders for a cleaner look
        prevPageButton.setBorderPainted(false);
        nextPageButton.setBorderPainted(false);

        // Add hover effect
        prevPageButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                prevPageButton.setBackground(new Color(55, 0, 179)); // Slightly Darker Purple
                prevPageButton.setForeground(Color.WHITE);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                prevPageButton.setBackground(new Color(30, 30, 30));
                prevPageButton.setForeground(new Color(187, 134, 252));
            }
        });

        nextPageButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nextPageButton.setBackground(new Color(55, 0, 179)); // Slightly Darker Purple
                nextPageButton.setForeground(Color.WHITE);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nextPageButton.setBackground(new Color(30, 30, 30));
                nextPageButton.setForeground(new Color(187, 134, 252));
            }
        });

        // Add buttons to the panel
        buttonPanel.add(prevPageButton);
        buttonPanel.add(nextPageButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);


        buttonPanel.add(prevPageButton);
        buttonPanel.add(nextPageButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // ACTION LISTENERS
        prevPageButton.addActionListener(e -> changePage(-1));
        nextPageButton.addActionListener(e -> changePage(1));
        searchButton.addActionListener(e -> searchCars());
        sortDropdown.addActionListener(e -> sortCars());
        homeButton.addActionListener(e -> resetToHomePage());
        viewFavoritesButton.addActionListener(e -> viewFavorites());

        displayCars();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    /**
     * Displays multiple cars on the current page, including inline comparison UI.
     */
    private void displayCars() {
        carPanel.removeAll();
        int start = currentPage * CARS_PER_PAGE;
        int end = Math.min(start + CARS_PER_PAGE, vehicles.size());

        for (int i = start; i < end; i++) {
            Vehicle car = vehicles.get(i);
            JPanel vehiclePanel = new JPanel();
            vehiclePanel.setLayout(new BorderLayout());

            // Check if the car is in favorites and change appearance
            if (favorites.contains(car)) {
                vehiclePanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(255, 215, 0), 5, true), // Bright Gold Border
                        BorderFactory.createEmptyBorder(10, 10, 10, 10) // Add some spacing inside
                ));
                vehiclePanel.setBackground(new Color(50, 50, 50)); // Slightly lighter dark gray
            } else {
                if (isDarkMode) {
                    vehiclePanel.setBackground(new Color(30, 30, 30)); // Dark Mode
                    vehiclePanel.setBorder(BorderFactory.createLineBorder(new Color(187, 134, 252), 3, true)); // Purple Border
                } else {
                    vehiclePanel.setBackground(new Color(255, 255, 255)); // Light Mode
                    vehiclePanel.setBorder(BorderFactory.createLineBorder(new Color(98, 0, 238), 3, true)); // Deep Purple Border
                }
            }

            // Load car image
            JLabel carImage = new JLabel();
            carImage.setHorizontalAlignment(JLabel.CENTER);
            carImage.setPreferredSize(new Dimension(200, 150));
            loadCarImage(car.getImageUrl(), carImage);

            // Make the image clickable
            carImage.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    showImagePopup(car.getImageUrl());
                }
            });

            vehiclePanel.add(carImage, BorderLayout.WEST);



            // Car details
            JPanel detailsPanel = new JPanel();
            detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS)); // Organizes text vertically
            detailsPanel.setBackground(new Color(30, 30, 30)); // Dark Gray

            JLabel makeModelLabel = new JLabel(car.getMake() + " " + car.getModel());
            makeModelLabel.setFont(new Font("Arial", Font.BOLD, 18));
            makeModelLabel.setForeground(isDarkMode ? new Color(187, 134, 252) : new Color(30, 30, 30)); // Purple (Dark) or Black (Light)


            JLabel yearFuelLabel = new JLabel("Year: " + car.getYear() + " | Fuel: " + car.getFuelType());
            yearFuelLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            yearFuelLabel.setForeground(isDarkMode ? Color.WHITE : new Color(50, 50, 50));


            JLabel reviewLabel = new JLabel("<html><i>" + car.getReview() + "</i></html>");
            reviewLabel.setFont(new Font("Arial", Font.ITALIC, 12));
            reviewLabel.setForeground(isDarkMode ? new Color(3, 218, 198) : new Color(98, 0, 238)); // Cyan (Dark) or Deep Purple (Light)


            detailsPanel.add(makeModelLabel);
            detailsPanel.add(yearFuelLabel);
            detailsPanel.add(reviewLabel);

            vehiclePanel.add(detailsPanel, BorderLayout.CENTER);


            // BUTTONS: Add to Favorites & Compare
            JPanel buttonPanel = new JPanel();
            JButton addToFavoritesButton = new JButton("Add to Favorites");
            addToFavoritesButton.setBackground(new Color(30, 30, 30));
            addToFavoritesButton.setForeground(new Color(3, 218, 198)); // Cyan
            addToFavoritesButton.setBorder(BorderFactory.createLineBorder(new Color(3, 218, 198), 2));

            JButton compareButton = new JButton("Compare");
            compareButton.setBackground(new Color(30, 30, 30));
            compareButton.setForeground(new Color(3, 218, 198)); // Cyan
            compareButton.setBorder(BorderFactory.createLineBorder(new Color(3, 218, 198), 2));

            // INLINE COMPARISON PANEL
            JPanel comparisonContainer = new JPanel(new GridLayout(1, 2));
            JComboBox<String> comparisonDropdown = new JComboBox<>();
            JLabel comparisonLabel = new JLabel();

            // Hide comparison by default
            comparisonContainer.setVisible(false);

            compareButton.addActionListener(e -> {
                Vehicle selected = showVehicleSelectionDialog(car); // helper method below
                if (selected != null) {
                    new CarComparisonWindow(car, selected);
                }
            });
            

            // When user selects a vehicle, update comparison label
            comparisonDropdown.addActionListener(e -> {
                int selectedIndex = comparisonDropdown.getSelectedIndex();
                if (selectedIndex >= 0) {
                    Vehicle selectedVehicle = originalVehicles.get(selectedIndex);
                    comparisonLabel.setText(
                            "<html><b>Comparing:</b><br>" +
                                    car.getMake() + " " + car.getModel() +
                                    " <b>vs.</b> " + selectedVehicle.getMake() + " " + selectedVehicle.getModel() +
                                    "<br>Year: " + car.getYear() + " vs. " + selectedVehicle.getYear() +
                                    "<br>Fuel Type: " + car.getFuelType() + " vs. " + selectedVehicle.getFuelType() +
                                    "</html>");
                }
            });

            comparisonContainer.add(comparisonDropdown);
            comparisonContainer.add(comparisonLabel);

            addToFavoritesButton.addActionListener(e -> addToFavorites(car));

            buttonPanel.add(addToFavoritesButton);
            buttonPanel.add(compareButton);
            vehiclePanel.add(buttonPanel, BorderLayout.SOUTH);
            vehiclePanel.add(comparisonContainer, BorderLayout.EAST);

            vehiclePanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(10, 10, 10, 10), // Outer margin
                    BorderFactory.createLineBorder(Color.BLACK, 2, true) // Inner border
            ));


            carPanel.add(vehiclePanel);
        }

        carPanel.revalidate();
        carPanel.repaint();
    }

    private void changePage(int delta) {
        int maxPage = (vehicles.size() - 1) / CARS_PER_PAGE;
        currentPage = Math.max(0, Math.min(maxPage, currentPage + delta));
        displayCars();
    }

    private void loadCarImage(String imagePath, JLabel imageLabel) {
        if (imagePath == null || imagePath.isEmpty()) {
            imageLabel.setText("No image available.");
            return;
        }
        imageLabel.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH)));
    }

    private void searchCars() {
        String query = searchField.getText().trim().toLowerCase();
        if (query.isEmpty()) {
            vehicles = new ArrayList<>(originalVehicles);
        } else {
            vehicles = originalVehicles.stream()
                    .filter(car -> car.getMake().toLowerCase().contains(query) || car.getModel().toLowerCase().contains(query))
                    .toList();
        }
        currentPage = 0;
        displayCars();
    }

    private void sortCars() {
        vehicles.sort(sortDropdown.getSelectedItem().equals("Sort by Make") ?
                Comparator.comparing(Vehicle::getMake) : Comparator.comparingInt(Vehicle::getYear));
        currentPage = 0;
        displayCars();
    }

    private void resetToHomePage() {
        viewingFavorites = false;
        vehicles = new ArrayList<>(originalVehicles);
        currentPage = 0;
        displayCars();
    }

    private void viewFavorites() {
        if (favorites.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No favorite vehicles.");
            return;
        }
        viewingFavorites = true;
        vehicles = new ArrayList<>(favorites);
        currentPage = 0;
        displayCars();
    }

    private void addToFavorites(Vehicle vehicle) {
        if (favorites.contains(vehicle)) {
            favorites.remove(vehicle);
            JOptionPane.showMessageDialog(frame, vehicle.getMake() + " " + vehicle.getModel() + " removed from favorites.");
        } else {
            favorites.add(vehicle);
            JOptionPane.showMessageDialog(frame, vehicle.getMake() + " " + vehicle.getModel() + " added to favorites!");
        }
        displayCars(); // Refresh UI to update highlighting
        carPanel.revalidate();
        carPanel.repaint();
    }

    private void showImagePopup(String imagePath) {
        // Create a new JFrame for the popup
        JFrame popupFrame = new JFrame("Car Image");
        popupFrame.setSize(600, 400);
        popupFrame.setLayout(new BorderLayout());

        // Load and display the image
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(550, 350, Image.SCALE_SMOOTH));
        imageLabel.setIcon(icon);

        // Create a close button
        JButton closeButton = new JButton("Close");
        closeButton.setBackground(new Color(30, 30, 30));
        closeButton.setForeground(new Color(187, 134, 252));
        closeButton.setBorder(BorderFactory.createLineBorder(new Color(187, 134, 252), 2));

        closeButton.addActionListener(e -> popupFrame.dispose());

        // Add components to the popup window
        popupFrame.add(imageLabel, BorderLayout.CENTER);
        popupFrame.add(closeButton, BorderLayout.SOUTH);

        popupFrame.setLocationRelativeTo(frame); // Center relative to main window
        popupFrame.setVisible(true);
    }

    private void toggleTheme(JToggleButton toggleButton) {
        isDarkMode = !isDarkMode; // Toggle mode

        if (isDarkMode) {
            // Apply Dark Mode
            frame.getContentPane().setBackground(new Color(18, 18, 18));
            carPanel.setBackground(new Color(18, 18, 18));
            toggleButton.setText("Light Mode");
            toggleButton.setBackground(new Color(30, 30, 30));
            toggleButton.setForeground(new Color(187, 134, 252));
        } else {
            // Apply Light Mode
            frame.getContentPane().setBackground(new Color(245, 245, 245));
            carPanel.setBackground(new Color(245, 245, 245));
            toggleButton.setText("Dark Mode");
            toggleButton.setBackground(new Color(230, 230, 230));
            toggleButton.setForeground(new Color(30, 30, 30));
        }

        // Refresh UI
        displayCars();
        frame.revalidate();
        frame.repaint();
    }

    private Vehicle showVehicleSelectionDialog(Vehicle currentCar) {
        List<String> options = new ArrayList<>();
        List<Vehicle> selectableCars = new ArrayList<>();
    
        for (Vehicle v : originalVehicles) {
            if (!v.equals(currentCar)) {
                options.add(v.getMake() + " " + v.getModel());
                selectableCars.add(v);
            }
        }
    
        Object selection = JOptionPane.showInputDialog(
                frame,
                "Select a car to compare with:",
                "Choose Car",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options.toArray(),
                options.get(0)
        );
    
        int selectedIndex = options.indexOf(selection);
        return (selectedIndex >= 0) ? selectableCars.get(selectedIndex) : null;
    }    
}

