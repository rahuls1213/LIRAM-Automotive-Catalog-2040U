import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

/**
 * CarViewer class creates a GUI for viewing a paginated list of vehicles.
 * Features:
 * - Displays multiple cars per page.
 * - Loads and displays car images.
 * - Allows navigation between pages of vehicles.
 * - Provides search, sorting, and a Home button to reset pagination.
 */
public class CarViewer {
    private JFrame frame;
    private JPanel carPanel;
    private JTextField searchField;
    private JComboBox<String> sortDropdown;
    private JButton nextPageButton, prevPageButton, searchButton, homeButton, loginButton;
    private List<Vehicle> vehicles;
    private List<Vehicle> originalVehicles; // Backup of all vehicles for reset
    private int currentPage = 0;
    private final int CARS_PER_PAGE = 3; // Number of cars displayed per page

    /**
     * Initializes the CarViewer GUI with a paginated list of vehicles.
     * @param vehicles List of Vehicle objects to display.
     */
    public CarViewer(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
        this.originalVehicles = new ArrayList<>(vehicles); // Store the original list

        // Set up the main application window
        frame = new JFrame("Car Viewer");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // TOP PANEL: Search, Sorting, Home, and Login Buttons
        JPanel controlPanel = new JPanel();
        searchField = new JTextField(10);
        searchButton = new JButton("Search");
        homeButton = new JButton("Home"); // Resets the list to all vehicles
        String[] sortOptions = {"Sort by Make", "Sort by Year"};
        sortDropdown = new JComboBox<>(sortOptions);
        loginButton = new JButton("Login");

        // Add components to the control panel
        controlPanel.add(new JLabel("Search:"));
        controlPanel.add(searchField);
        controlPanel.add(searchButton);
        controlPanel.add(homeButton); // Adds Home button for resetting pagination
        controlPanel.add(sortDropdown);
        controlPanel.add(loginButton);

        // Add the top control panel to the frame
        frame.add(controlPanel, BorderLayout.NORTH);

        // MAIN CONTENT: Vehicle Display Area
        carPanel = new JPanel();
        carPanel.setLayout(new GridLayout(CARS_PER_PAGE, 1)); // Display multiple cars per page
        frame.add(carPanel, BorderLayout.CENTER);

        // NAVIGATION BUTTONS: Previous & Next Page
        JPanel buttonPanel = new JPanel();
        prevPageButton = new JButton("Previous Page");
        nextPageButton = new JButton("Next Page");
        buttonPanel.add(prevPageButton);
        buttonPanel.add(nextPageButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // ACTION LISTENERS
        prevPageButton.addActionListener(e -> changePage(-1)); // Go to previous page
        nextPageButton.addActionListener(e -> changePage(1)); // Go to next page
        searchButton.addActionListener(e -> searchCars()); // Perform search
        sortDropdown.addActionListener(e -> sortCars()); // Sort list
        homeButton.addActionListener(e -> resetToHomePage()); // Reset search & pagination

        displayCars(); // Load initial set of vehicles
        frame.setVisible(true); // Show the UI
    }

    /**
     * Displays multiple cars on the current page.
     */
    private void displayCars() {
        carPanel.removeAll(); // Clear previous cars
        int start = currentPage * CARS_PER_PAGE;
        int end = Math.min(start + CARS_PER_PAGE, vehicles.size());

        // Display cars for the current page
        for (int i = start; i < end; i++) {
            Vehicle car = vehicles.get(i);

            // Create a panel for each vehicle
            JPanel vehiclePanel = new JPanel(new BorderLayout());

            // Load car image
            JLabel carImage = new JLabel();
            carImage.setHorizontalAlignment(JLabel.CENTER);
            loadCarImage(car.getImageUrl(), carImage);
            vehiclePanel.add(carImage, BorderLayout.WEST); // Image on the left

            // Display car details
            JTextArea carDetails = new JTextArea();
            carDetails.setEditable(false);
            carDetails.setText("Make: " + car.getMake() + "\nModel: " + car.getModel() +
                    "\nYear: " + car.getYear() + "\nFuel Type: " + car.getFuelType() +
                    "\nReview: " + car.getReview());

            vehiclePanel.add(carDetails, BorderLayout.CENTER); // Details on the right
            carPanel.add(vehiclePanel);
        }

        carPanel.revalidate(); // Refresh UI
        carPanel.repaint();
    }

    /**
     * Changes the current page for pagination.
     * @param delta Page change direction (-1 for previous, +1 for next).
     */
    private void changePage(int delta) {
        int maxPage = (vehicles.size() - 1) / CARS_PER_PAGE;
        currentPage = Math.max(0, Math.min(maxPage, currentPage + delta)); // Ensure within bounds
        displayCars();
    }

    /**
     * Loads and scales the vehicle image from the specified path.
     * @param imagePath The file path or classpath of the image.
     * @param imageLabel JLabel to display the image.
     */
    private void loadCarImage(String imagePath, JLabel imageLabel) {
        if (imagePath == null || imagePath.isEmpty()) {
            imageLabel.setText("No image available.");
            return;
        }

        // Try loading as an external file
        File imageFile = new File(imagePath);
        if (imageFile.exists()) {
            ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());
            Image img = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
            return;
        }

        // Try loading from classpath
        URL imageUrl = getClass().getResource("/" + imagePath);
        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);
            Image img = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
            return;
        }

        imageLabel.setText("Image not found");
    }

    /**
     * Filters the vehicle list based on user search input.
     * Resets the list if the search field is empty.
     */
    private void searchCars() {
        String query = searchField.getText().trim().toLowerCase();
        if (query.isEmpty()) {
            vehicles = new ArrayList<>(originalVehicles);
        } else {
            List<Vehicle> filteredVehicles = new ArrayList<>();
            for (Vehicle car : originalVehicles) {
                if (car.getMake().toLowerCase().contains(query) ||
                        car.getModel().toLowerCase().contains(query)) {
                    filteredVehicles.add(car);
                }
            }
            if (filteredVehicles.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No cars found!");
                return;
            }
            vehicles = filteredVehicles;
        }

        currentPage = 0; // Reset to first page
        displayCars();
    }

    /**
     * Sorts the vehicles list based on the selected sorting option.
     */
    private void sortCars() {
        vehicles = new ArrayList<>(originalVehicles);
        String selectedOption = (String) sortDropdown.getSelectedItem();

        if (selectedOption.equals("Sort by Make")) {
            Collections.sort(vehicles, Comparator.comparing(Vehicle::getMake));
        } else if (selectedOption.equals("Sort by Year")) {
            Collections.sort(vehicles, Comparator.comparingInt(Vehicle::getYear));
        }

        currentPage = 0; // Reset to first page
        displayCars();
    }

    /**
     * Resets the list to show all vehicles and re-enables pagination.
     */
    private void resetToHomePage() {
        vehicles = new ArrayList<>(originalVehicles); // Reset to full list
        currentPage = 0; // Reset to first page
        displayCars();
    }
}
