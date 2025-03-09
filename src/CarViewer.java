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
 * CarViewer class creates a GUI for viewing a list of vehicles.
 * Features:
 * - Displays car details including make, model, year, fuel type, and review.
 * - Loads and displays car images.
 * - Allows navigation between vehicles.
 * - Provides search and sorting functionalities.
 * - Includes a placeholder login button.
 */
public class CarViewer {
    private JFrame frame;
    private JLabel carImage;
    private JTextArea carDetails;
    private JButton nextButton, prevButton, searchButton, loginButton;
    private JTextField searchField;
    private JComboBox<String> sortDropdown;
    private List<Vehicle> vehicles;
    private List<Vehicle> originalVehicles;
    private int currentIndex = 0;

    /**
     * Initializes the CarViewer GUI with a list of vehicles.
     * @param vehicles List of Vehicle objects to display.
     */
    public CarViewer(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
        this.originalVehicles = new ArrayList<>(vehicles); // Store original list

        frame = new JFrame("Car Viewer");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // TOP PANEL: Search, Sorting, and Login
        JPanel controlPanel = new JPanel();
        searchField = new JTextField(10);
        searchButton = new JButton("Search");
        String[] sortOptions = {"Sort by Make", "Sort by Year"};
        sortDropdown = new JComboBox<>(sortOptions);
        loginButton = new JButton("Login");

        controlPanel.add(new JLabel("Search:"));
        controlPanel.add(searchField);
        controlPanel.add(searchButton);
        controlPanel.add(sortDropdown);
        controlPanel.add(loginButton);

        frame.add(controlPanel, BorderLayout.NORTH); // Keeps the top bar

        // MAIN CONTENT: Image & Details
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        // Car Image (Displayed below search bar)
        carImage = new JLabel();
        carImage.setHorizontalAlignment(JLabel.CENTER);
        contentPanel.add(carImage, BorderLayout.NORTH);

        // Car Details (Displayed below image)
        carDetails = new JTextArea();
        carDetails.setEditable(false);
        contentPanel.add(new JScrollPane(carDetails), BorderLayout.CENTER);

        frame.add(contentPanel, BorderLayout.CENTER); // Adds content to the center

        // NAVIGATION BUTTONS: Previous & Next
        JPanel buttonPanel = new JPanel();
        prevButton = new JButton("Previous");
        nextButton = new JButton("Next");
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        frame.add(buttonPanel, BorderLayout.SOUTH); // Adds navigation buttons at the bottom

        // ACTION LISTENERS
        prevButton.addActionListener(e -> showPreviousCar());
        nextButton.addActionListener(e -> showNextCar());
        searchButton.addActionListener(e -> searchCars());
        sortDropdown.addActionListener(e -> sortCars());

        // Display first vehicle
        showCarDetails(currentIndex);
        frame.setVisible(true);
    }

    /**
     * Displays details of the selected vehicle, including image.
     * @param index Index of the vehicle to display.
     */
    private void showCarDetails(int index) {
        if (vehicles.isEmpty()) {
            carDetails.setText("No vehicles available.");
            carImage.setIcon(null);
            return;
        }

        Vehicle car = vehicles.get(index);
        carDetails.setText("Make: " + car.getMake() + "\nModel: " + car.getModel() +
                "\nYear: " + car.getYear() + "\nFuel Type: " + car.getFuelType() +
                "\nReview: " + car.getReview());

        loadCarImage(car.getImageUrl());

        // Refresh the UI to reflect changes
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Loads and scales the vehicle image from the specified path.
     * @param imagePath The file path or classpath of the image.
     */
    private void loadCarImage(String imagePath) {
        if (imagePath == null || imagePath.isEmpty()) {
            carImage.setText("No image available.");
            return;
        }

        // Try loading as an external file
        File imageFile = new File(imagePath);
        if (imageFile.exists()) {
            ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());
            Image img = icon.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
            carImage.setIcon(new ImageIcon(img));
            return;
        }

        // Try loading from classpath
        URL imageUrl = getClass().getResource("/" + imagePath);
        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);
            Image img = icon.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
            carImage.setIcon(new ImageIcon(img));
            return;
        }

        // If not found, display error text
        carImage.setText("Image not found");
    }

    /**
     * Displays the next car in the list.
     */
    private void showNextCar() {
        if (currentIndex < vehicles.size() - 1) {
            currentIndex++;
            showCarDetails(currentIndex);
        }
    }

    /**
     * Displays the previous car in the list.
     */
    private void showPreviousCar() {
        if (currentIndex > 0) {
            currentIndex--;
            showCarDetails(currentIndex);
        }
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

        currentIndex = 0;
        showCarDetails(currentIndex);
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

        currentIndex = 0;
        showCarDetails(currentIndex);
    }
}
