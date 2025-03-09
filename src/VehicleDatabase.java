import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The VehicleDatabase class is responsible for loading vehicle data
 * from a JSON file and providing access to the list of vehicles.
 */
public class VehicleDatabase {
    private static final String FILE_PATH = "data/cars.json";
    private static List<Vehicle> vehicles = new ArrayList<>();

    /**
     * Sorts Vehicles based on a list of criteria.
     *
     * @param criteria  List of fields to sort by (e.g., ["year", "fuel", "reliability"])
     * @param ascending Whether to sort in ascending (true) or descending (false) order
     */
    public static void sortVehicles(List<String> criteria, boolean ascending) {
        Comparator<Vehicle> comparator = (v1, v2) -> 0;

        for (String criterion : criteria) {
            switch (criterion.toLowerCase()) {
                case "year":
                    comparator = comparator.thenComparing(Vehicle::getYear);
                    break;
                case "fuel":
                    comparator = comparator.thenComparing(Vehicle::getFuelType);
                    break;
                case "reliability":
                    comparator = comparator.thenComparing(Vehicle::getReliabilityRating);
                    break;
                default:
                    System.out.println("Invalid sorting option: " + criterion);
                    return;
            }
        }

        if (!ascending) {
            comparator = comparator.reversed();
        }
        vehicles.sort(comparator);
    }

    /**
     * Implements fuzzy searching for vehicles by make or model.
     *
     * @param query Search query.
     * @return List of matching vehicles.
     */
    public static List<Vehicle> searchVehicles(String query) {
        return vehicles.stream()
                .filter(v -> v.getMake().toLowerCase().contains(query.toLowerCase()) ||
                        v.getModel().toLowerCase().contains(query.toLowerCase()))
                .toList();
    }

    public static List<Vehicle> filterVehicles(Integer year, String make, String fuelType) {
        return vehicles.stream()
                .filter(v -> (year == null || v.getYear() == year) &&
                        (make == null || v.getMake().equalsIgnoreCase(make)) &&
                        (fuelType == null || v.getFuelType().equalsIgnoreCase(fuelType)))
                .toList();
    }

    /**
     * Loads vehicle data from a JSON file and stores it in the list.
     */
    /**
     * Loads vehicle data from the API. Falls back to JSON file if API fails.
     */
    public static void loadVehicles() {
        System.out.println("Attempting to load vehicles from API...");
        loadVehiclesFromAPI();

        // If API fails and no vehicles were loaded, use JSON file
        if (vehicles.isEmpty()) {
            System.out.println("API unavailable. Loading from local JSON file...");
            loadVehiclesFromJSON();
        }
    }

    /**
     * Fetches vehicle data from the API and loads it into the list.
     */
    private static void loadVehiclesFromAPI() {
        JSONArray vehiclesData = VehicleAPIService.fetchVehicleData();

        if (vehiclesData == null) {
            System.out.println("API failed. Skipping API data loading.");
            return;
        }

        for (int i = 0; i < vehiclesData.length(); i++) {
            JSONObject obj = vehiclesData.getJSONObject(i);
            vehicles.add(new Vehicle(
                    obj.getString("Make_Name"),  // Adjust this based on API structure
                    "Unknown Model",             // Some APIs don't provide full info
                    2024,                        // Default year
                    "Unknown Fuel",              // No fuel info in NHTSA API
                    "",                          // No image URL in this API
                    "API Fetched Data",          // Mark data as API-sourced
                    5                            // Default reliability rating
            ));
        }
        System.out.println("Loaded " + vehicles.size() + " vehicles from API.");
    }


    /**
     * Fallback: Loads vehicle data from a local JSON file.
     */
    private static void loadVehiclesFromJSON() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                vehicles.add(new Vehicle(
                        obj.getString("make"),
                        obj.getString("model"),
                        obj.getInt("year"),
                        obj.getString("fuelType"),
                        obj.getString("imageUrl"),
                        obj.getString("review"),
                        obj.getInt("reliabilityRating")
                ));
            }
            System.out.println("Loaded " + vehicles.size() + " vehicles from JSON.");
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
        }
    }

    /**
     * Returns the list of vehicles loaded from the JSON file.
     *
     * @return List of Vehicle objects.
     */
    public static List<Vehicle> getVehicles() {
        return vehicles;
    }
}



