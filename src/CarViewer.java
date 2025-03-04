import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class CarViewer {
    private JFrame frame;
    private JLabel carImage;
    private JTextArea carDetails;
    private JButton nextButton, prevButton, searchButton, loginButton;
    private JTextField searchField;
    private JComboBox<String> sortDropdown;
    private List<Vehicle> vehicles;
    private List<Vehicle> originalVehicles; // Backup for search reset
    private int currentIndex = 0;

     /**
     * Initializes the CarViewer GUI and sets up components including 
     * the frame, navigation buttons, search functionality, and sorting.
     * 
     * @param vehicles List of Vehicle objects to be displayed.
     */
    public CarViewer(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
        this.originalVehicles = new ArrayList<>(vehicles); // Backup the original list

        frame = new JFrame("Car Viewer");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Car Image
        carImage = new JLabel();
        carImage.setHorizontalAlignment(JLabel.CENTER);
        frame.add(carImage, BorderLayout.NORTH);

        // Car Details Text Area
        carDetails = new JTextArea();
        carDetails.setEditable(false);
        frame.add(new JScrollPane(carDetails), BorderLayout.CENTER);

        // Control Panel (Search, Sorting, Login)
        JPanel controlPanel = new JPanel();

        // Search Field
        searchField = new JTextField(10);
        searchButton = new JButton("Search");

        // Sorting Dropdown
        String[] sortOptions = {"Sort by Make", "Sort by Year"};
        sortDropdown = new JComboBox<>(sortOptions);

        // Login Button (UI Only)
        loginButton = new JButton("Login");

        // Add components to control panel
        controlPanel.add(new JLabel("Search:"));
        controlPanel.add(searchField);
        controlPanel.add(searchButton);
        controlPanel.add(sortDropdown);
        controlPanel.add(loginButton);

        frame.add(controlPanel, BorderLayout.NORTH);

        // Navigation Buttons
        JPanel buttonPanel = new JPanel();
        prevButton = new JButton("Previous");
        nextButton = new JButton("Next");
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Add Action Listeners
        prevButton.addActionListener(e -> showPreviousCar());
        nextButton.addActionListener(e -> showNextCar());
        searchButton.addActionListener(e -> searchCars());
        sortDropdown.addActionListener(e -> sortCars());

        // Show first car
        showCarDetails(currentIndex);

        frame.setVisible(true);
    }

    /**
    * Displays the details of the car at the specified index.
    * Updates the text area with vehicle details and loads the image.
    *
    * @param index The index of the car in the vehicles list.
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

        // Attempt to load the image correctly
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/" + car.getImageUrl()));
            carImage.setIcon(icon);
        } catch (Exception e) {
            carImage.setText("Image not found");
        }
    }

    /**
    * Displays the next car in the list.
    * Updates the UI if there is a next car available.
    */
    private void showNextCar() {
        if (currentIndex < vehicles.size() - 1) {
            currentIndex++;
            showCarDetails(currentIndex);
        }
    }

    /**
    * Displays the previous car in the list.
    * Updates the UI if there is a previous car available.
    */
    private void showPreviousCar() {
        if (currentIndex > 0) {
            currentIndex--;
            showCarDetails(currentIndex);
        }
    }

    /**
    * Filters the vehicle list based on the search query entered by the user.
    * If the search field is empty, it resets the list to its original state.
    */
    private void searchCars() {
        String query = searchField.getText().trim().toLowerCase();

        // Reset list if search is empty
        if (query.isEmpty()) {
            vehicles = new ArrayList<>(originalVehicles);
            currentIndex = 0;
            showCarDetails(currentIndex);
            return;
        }

        List<Vehicle> filteredVehicles = new ArrayList<>();
        for (Vehicle car : originalVehicles) { // Search in the full dataset
            if (car.getMake().toLowerCase().contains(query) || 
                car.getModel().toLowerCase().contains(query)) {
                filteredVehicles.add(car);
            }
        }

        if (filteredVehicles.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No cars found!");
        } else {
            vehicles = filteredVehicles;
            currentIndex = 0;
            showCarDetails(currentIndex);
        }
    }

    /**
    * Sorts the list of vehicles based on the selected sorting option.
    * Sorting options include sorting by make and by year.
    * After sorting, the first vehicle in the sorted list is displayed.
    */
    private void sortCars() {
        // Restore full list before sorting
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
