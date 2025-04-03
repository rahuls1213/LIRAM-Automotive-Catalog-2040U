import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * UserDatabase handles loading, saving, and managing user account data,
 * including user credentials and favorite vehicles.
 *
 * All users are stored persistently in a local JSON file ("users.json"),
 * which is read at startup and written after registration or favorite updates.
 *
 * This class is referenced by {@link UserAuthService}, {@link Session},
 * and {@link CarViewer} to manage user authentication and personalization.
 */
public class UserDatabase {
    private static final String FILE_PATH = "data/users.json"; // Path to user data file
    private static List<User> users = new ArrayList<>(); // In-memory user list

    /**
     * Loads all users from the JSON file into memory, including their favorite vehicles.
     * If any user has favorites, they are matched using {@link VehicleDatabase}.
     */
    public static void loadUsers() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            JSONArray arr = new JSONArray(content);
            users.clear();

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                User user = new User(obj.getString("username"), obj.getString("passwordHash"));

                // Load favorites if available
                if (obj.has("favorites")) {
                    JSONArray favs = obj.getJSONArray("favorites");
                    for (int j = 0; j < favs.length(); j++) {
                        JSONObject favObj = favs.getJSONObject(j);

                        // Lookup matching Vehicle instance by make/model/year
                        Vehicle match = VehicleDatabase.findVehicle(
                                favObj.getString("make"),
                                favObj.getString("model"),
                                favObj.getInt("year")
                        );

                        // Add match to user's favorite list
                        if (match != null) {
                            user.getFavorites().add(match);
                        }
                    }
                }

                users.add(user);
            }
        } catch (IOException e) {
            System.err.println("Could not read users.json: " + e.getMessage());
        }
    }

    /**
     * Saves all users and their favorite vehicles to the JSON file for persistence.
     * This is typically triggered after registration or updating favorites.
     */
    public static void saveUsers() {
        JSONArray arr = new JSONArray();
        for (User user : users) {
            JSONObject obj = new JSONObject();
            obj.put("username", user.getUsername());
            obj.put("passwordHash", user.getPasswordHash());

            // Save user's favorites as JSON array
            JSONArray favArray = new JSONArray();
            for (Vehicle v : user.getFavorites()) {
                JSONObject favObj = new JSONObject();
                favObj.put("make", v.getMake());
                favObj.put("model", v.getModel());
                favObj.put("year", v.getYear());
                favArray.put(favObj);
            }
            obj.put("favorites", favArray);

            arr.put(obj);
        }

        try {
            Files.write(Paths.get(FILE_PATH), arr.toString(4).getBytes());
        } catch (IOException e) {
            System.err.println("Could not save users.json: " + e.getMessage());
        }
    }

    /**
     * Attempts to add a new user to the database. Fails if the username already exists.
     *
     * @param newUser the user to add
     * @return true if user is added successfully, false if username already exists
     */
    public static boolean addUser(User newUser) {
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(newUser.getUsername())) {
                return false; // Already exists
            }
        }
        users.add(newUser);
        saveUsers();
        return true;
    }

    /**
     * Retrieves a user object from the database by username (case-insensitive).
     *
     * @param username the username to search for
     * @return the User object if found, otherwise null
     */
    public static User getUser(String username) {
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) return u;
        }
        return null;
    }
}
