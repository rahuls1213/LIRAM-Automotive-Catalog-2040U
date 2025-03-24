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
                users.add(new User(obj.getString("username"), obj.getString("passwordHash")));
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
