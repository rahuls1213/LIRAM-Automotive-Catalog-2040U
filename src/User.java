import java.util.*;

public class User {
    private String username;
    private String passwordHash;
    private List<Vehicle> favorites = new ArrayList<>();

    public User(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }

    public List<Vehicle> getFavorites() { return favorites; }

    public void addFavorite(Vehicle vehicle) {
        if (!favorites.contains(vehicle)) {
            favorites.add(vehicle);
        }
    }

    public void removeFavorite(Vehicle vehicle) {
        favorites.remove(vehicle);
    }

    public boolean isFavorite(Vehicle vehicle) {
        return favorites.contains(vehicle);
    }
}
