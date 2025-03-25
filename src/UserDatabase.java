import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class UserDatabase {
    private static final String FILE_PATH = "data/users.json";
    private static List<User> users = new ArrayList<>();

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
                        Vehicle match = VehicleDatabase.findVehicle(
                                favObj.getString("make"),
                                favObj.getString("model"),
                                favObj.getInt("year")
                        );
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

    public static void saveUsers() {
        JSONArray arr = new JSONArray();
        for (User user : users) {
            JSONObject obj = new JSONObject();
            obj.put("username", user.getUsername());
            obj.put("passwordHash", user.getPasswordHash());

            // Save favorites
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

    public static User getUser(String username) {
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) return u;
        }
        return null;
    }
}
