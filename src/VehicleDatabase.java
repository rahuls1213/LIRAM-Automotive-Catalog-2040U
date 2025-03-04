import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class VehicleDatabase {
    private static final String FILE_PATH = "data/cars.json";
    private static List<Vehicle> vehicles = new ArrayList<>();

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
                        obj.getString("review")
                ));
            }
            System.out.println("Loaded " + vehicles.size() + " vehicles.");
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
        }
    }

    public static List<Vehicle> getVehicles() {
        return vehicles;
    }
}
