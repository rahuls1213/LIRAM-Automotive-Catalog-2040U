import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

 /**
 * VehicleDatabase handles reading, storing, and retrieving all vehicle listings
 * from a local JSON file ("cars.json") into memory.
 *
 * This static class provides:
 * - A method to load vehicle data at startup  
 * - A method to retrieve all vehicles  
 * - A method to search for a vehicle by make, model, and year  
 *
 * Used by {@link CarViewer}, {@link LandingPage}, and {@link UserDatabase}
 * to provide data for rendering, searching, and managing favorites.
 */
public class VehicleDatabase {

    private static final String FILE_PATH = "data/cars.json"; // Source JSON file
    private static List<Vehicle> vehicles = new ArrayList<>(); // In-memory Storage

    /**
    * Loads vehicle data from a JSON file and stores it in the list.
    * Reads the file, parses the JSON, and creates Vehicle objects.
    * 
    * If an error occurs while reading the file, an error message is printed.
    */
    public static void loadVehicles() {
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
                        obj.getString("price")
                ));
            }
            System.out.println("Loaded " + vehicles.size() + " vehicles.");
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
        }
    }

    /**
    * Returns the list of vehicles loaded from the JSON file.
    * 
    * @return List of {@link Vehicle} objects
    */
    public static List<Vehicle> getVehicles() {
        return vehicles;
    }

    /**
     * Searches for a specific vehicle by make, model, and year.
     * The search is case-insensitive for make/model.
     *
     * @param make  the vehicle make (e.g., "Honda")
     * @param model the vehicle model (e.g., "Civic")
     * @param year  the vehicle year
     * @return the matching {@link Vehicle}, or null if not found
     */
    public static Vehicle findVehicle(String make, String model, int year) {
        for (Vehicle v : vehicles) {
            if (v.getMake().equalsIgnoreCase(make) &&
                v.getModel().equalsIgnoreCase(model) &&
                v.getYear() == year) {
                return v;
            }
        }
        return null;
    }    
}