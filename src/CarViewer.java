import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

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
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // TOP PANEL: Search, Sorting, Home, View Favorites, and Login Buttons
        JPanel controlPanel = new JPanel();
        searchField = new JTextField(10);
        searchButton = new JButton("Search");
        homeButton = new JButton("Home");
        viewFavoritesButton = new JButton("View Favorites"); // New button to switch to favorites
        String[] sortOptions = {"Sort by Make", "Sort by Year"};
        sortDropdown = new JComboBox<>(sortOptions);
        loginButton = new JButton("Login");

        controlPanel.add(new JLabel("Search:"));
        controlPanel.add(searchField);
        controlPanel.add(searchButton);
        controlPanel.add(homeButton);
        controlPanel.add(viewFavoritesButton); // Add View Favorites button
        controlPanel.add(sortDropdown);
        controlPanel.add(loginButton);

        frame.add(controlPanel, BorderLayout.NORTH);

        // MAIN CONTENT: Vehicle Display Area
        carPanel = new JPanel();
        carPanel.setLayout(new GridLayout(CARS_PER_PAGE, 1));
        frame.add(carPanel, BorderLayout.CENTER);

        // NAVIGATION BUTTONS
        JPanel buttonPanel = new JPanel();
        prevPageButton = new JButton("Previous Page");
        nextPageButton = new JButton("Next Page");
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

    private void displayCars() {
        carPanel.removeAll();
        int start = currentPage * CARS_PER_PAGE;
        int end = Math.min(start + CARS_PER_PAGE, vehicles.size());

        for (int i = start; i < end; i++) {
            Vehicle car = vehicles.get(i);
            JPanel vehiclePanel = new JPanel(new BorderLayout());

            // Load car image
            JLabel carImage = new JLabel();
            carImage.setHorizontalAlignment(JLabel.CENTER);
            loadCarImage(car.getImageUrl(), carImage);
            vehiclePanel.add(carImage, BorderLayout.WEST);

            // Car details
            JTextArea carDetails = new JTextArea();
            carDetails.setEditable(false);
            carDetails.setText("Make: " + car.getMake() + "\nModel: " + car.getModel() +
                    "\nYear: " + car.getYear() + "\nFuel Type: " + car.getFuelType() +
                    "\nReview: " + car.getReview());

            vehiclePanel.add(carDetails, BorderLayout.CENTER);

            // BUTTONS: Add to Favorites & Compare
            JPanel buttonPanel = new JPanel();
            JButton addToFavoritesButton = new JButton("Add to Favorites");
            JButton compareButton = new JButton("Compare");

            addToFavoritesButton.addActionListener(e -> addToFavorites(car));
            compareButton.addActionListener(e -> showComparisonWindow(car));

            buttonPanel.add(addToFavoritesButton);
            buttonPanel.add(compareButton);
            vehiclePanel.add(buttonPanel, BorderLayout.SOUTH);

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
            List<Vehicle> filteredVehicles = new ArrayList<>();
            for (Vehicle car : originalVehicles) {
                if (car.getMake().toLowerCase().contains(query) || car.getModel().toLowerCase().contains(query)) {
                    filteredVehicles.add(car);
                }
            }
            if (filteredVehicles.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No cars found!");
                return;
            }
            vehicles = filteredVehicles;
        }
        currentPage = 0;
        displayCars();
    }

    private void sortCars() {
        vehicles = new ArrayList<>(originalVehicles);
        String selectedOption = (String) sortDropdown.getSelectedItem();
        if (selectedOption.equals("Sort by Make")) {
            Collections.sort(vehicles, Comparator.comparing(Vehicle::getMake));
        } else if (selectedOption.equals("Sort by Year")) {
            Collections.sort(vehicles, Comparator.comparingInt(Vehicle::getYear));
        }
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

    private void showComparisonWindow(Vehicle selectedCar) {
        JDialog comparisonDialog = new JDialog(frame, "Compare Vehicles", true);
        comparisonDialog.setSize(600, 400);
        comparisonDialog.setLayout(new GridLayout(1, 2));

        JPanel leftPanel = createCarDetailsPanel(selectedCar);
        JPanel rightPanel = new JPanel(new BorderLayout());

        JComboBox<Vehicle> comparisonDropdown = new JComboBox<>(originalVehicles.toArray(new Vehicle[0]));
        JButton compareButton = new JButton("Compare");

        compareButton.addActionListener(e -> {
            Vehicle selectedComparison = (Vehicle) comparisonDropdown.getSelectedItem();
            rightPanel.removeAll();
            rightPanel.add(createCarDetailsPanel(selectedComparison), BorderLayout.CENTER);
            rightPanel.revalidate();
            rightPanel.repaint();
        });

        JPanel selectionPanel = new JPanel();
        selectionPanel.add(new JLabel("Compare with: "));
        selectionPanel.add(comparisonDropdown);
        selectionPanel.add(compareButton);

        rightPanel.add(selectionPanel, BorderLayout.NORTH);
        comparisonDialog.add(leftPanel);
        comparisonDialog.add(rightPanel);

        comparisonDialog.setVisible(true);
    }

    private JPanel createCarDetailsPanel(Vehicle car) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel imageLabel = new JLabel();
        loadCarImage(car.getImageUrl(), imageLabel);
        JTextArea details = new JTextArea(car.getMake() + " " + car.getModel() + "\nYear: " + car.getYear());
        details.setEditable(false);
        panel.add(imageLabel, BorderLayout.CENTER);
        panel.add(details, BorderLayout.SOUTH);
        return panel;
    }
}
