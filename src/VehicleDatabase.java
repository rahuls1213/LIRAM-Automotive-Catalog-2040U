import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

 /**
 * The VehicleDatabase class is responsible for loading vehicle data 
 * from a JSON file and providing access to the list of vehicles.
 */
public class VehicleDatabase {
    private static final String FILE_PATH = "data/cars.json";
    private static List<Vehicle> vehicles = new ArrayList<>();

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
    * @return List of Vehicle objects.
    */
    public static List<Vehicle> getVehicles() {
        return vehicles;
    }

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
