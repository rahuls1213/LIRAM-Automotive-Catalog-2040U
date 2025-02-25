import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class VehicleDatabase {
    private static final String FILE_PATH = "data/sample_vehicles.csv"; // Path to the CSV file

    // Method to read and display vehicle data
    public static void loadVehicles() {
        System.out.println("Reading vehicle database...\n");

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                // Skip the header row
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                // Split each row by commas
                String[] vehicle = line.split(",");
                String make = vehicle[0];
                String model = vehicle[1];
                String year = vehicle[2];
                String fuelType = vehicle[3];

                // Print the vehicle details
                System.out.println("Make: " + make + ", Model: " + model + ", Year: " + year + ", Fuel Type: " + fuelType);
            }
        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
        }
    }
}
