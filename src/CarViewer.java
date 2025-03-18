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

    public CarViewer(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
        this.originalVehicles = new ArrayList<>(vehicles);

        frame = new JFrame("Car Viewer");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // TOP PANEL: Search, Sorting, Home, View Favorites, and Login Buttons
        JPanel controlPanel = new JPanel();
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
            vehiclePanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true)); // Add a border for a card-like feel
            vehiclePanel.setBackground(new Color(30, 30, 30)); // Dark Gray Panel
            vehiclePanel.setBorder(BorderFactory.createLineBorder(new Color(187, 134, 252), 2, true)); // Soft Purple Border
            vehiclePanel.setPreferredSize(new Dimension(800, 180)); // Set fixed size for consistency


            // Load car image
            JLabel carImage = new JLabel();
            carImage.setHorizontalAlignment(JLabel.CENTER);
            carImage.setPreferredSize(new Dimension(200, 150)); // Ensure consistent image size
            loadCarImage(car.getImageUrl(), carImage);
            vehiclePanel.add(carImage, BorderLayout.WEST);


            // Car details
            JPanel detailsPanel = new JPanel();
            detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS)); // Organizes text vertically
            detailsPanel.setBackground(new Color(30, 30, 30)); // Dark Gray

            JLabel makeModelLabel = new JLabel(car.getMake() + " " + car.getModel());
            makeModelLabel.setFont(new Font("Arial", Font.BOLD, 18));
            makeModelLabel.setForeground(new Color(187, 134, 252)); // Soft Purple


            JLabel yearFuelLabel = new JLabel("Year: " + car.getYear() + " | Fuel: " + car.getFuelType());
            yearFuelLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            yearFuelLabel.setForeground(Color.WHITE); // White text


            JLabel reviewLabel = new JLabel("<html><i>" + car.getReview() + "</i></html>");
            reviewLabel.setFont(new Font("Arial", Font.ITALIC, 12));
            reviewLabel.setForeground(new Color(3, 218, 198)); // Cyan Accent


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
                comparisonDropdown.removeAllItems();
                for (Vehicle v : originalVehicles) {
                    if (!v.equals(car)) {
                        comparisonDropdown.addItem(v.getMake() + " " + v.getModel());
                    }
                }
                comparisonContainer.setVisible(true);
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
        if (!favorites.contains(vehicle)) {
            favorites.add(vehicle);
            JOptionPane.showMessageDialog(frame, vehicle.getMake() + " " + vehicle.getModel() + " added to favorites!");
        }
    }
}
