import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;


public class VehicleAPIService {

  private static final  String API_URL = "*Insert Any API we might use in here";
  /**
   * Fetches vehicle data from external API
   * @return JSONArray of vehicle data, or null if an errors occurs
   */

  public static JSONArray fetchVehicleData(){
    try{
      // Open connection
      URL url = new URL(API_URL);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept", "application/json");

      //Read responsne
      Scanner scanner = new Scanner(conn.getInputStream());
      StringBuilder jsonResponse = new StringBuilder();
      while (scanner.hasNextLine()) {
        jsonResponse.append(scanner.nextLine());
      }
      scanner.close();

      //Parse JSON response
      JSONObject response = new JSONObject(jsonResponse.toString());
      return response.getJSONArray("Results");
    } catch (IOException e) {
      System.err.println("Error Fetching Vehicle Data: " +  e.getMessage());
      return null;
    }
  }
}
