import java.util.*;

/**
 * User represents a registered account in the Automotive Catalog system.
 *
 * This class stores essential information about a user, including:
 * - A unique username
 * - A hashed password (MD5)
 * - A list of favorite vehicles selected by the user
 *
 * This class is referenced by {@link Session}, {@link UserDatabase}, and
 * {@link CarViewer} to manage personalized features such as login sessions
 * and favorites management.
 */
public class User {
    private String username;
    private String passwordHash;
    private List<Vehicle> favorites = new ArrayList<>();

    /**
     * Constructs a new User object with the given username and hashed password.
     *
     * @param username the unique identifier for this user
     * @param passwordHash the hashed password (generated via MD5)
     */
    public User(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    /**
     * @return the username associated with this user account
     */
    public String getUsername() { return username; }

    /**
     * @return the hashed password stored for authentication comparison
     */
    public String getPasswordHash() { return passwordHash; }

    /**
     * @return the list of vehicles marked as favorites by the user
     */
    public List<Vehicle> getFavorites() { return favorites; }

    /**
     * Adds a vehicle to the user's favorites list if it's not already present.
     *
     * @param vehicle the vehicle to add
     */
    public void addFavorite(Vehicle vehicle) {
        if (!favorites.contains(vehicle)) {
            favorites.add(vehicle);
        }
    }

    /**
     * Removes a vehicle from the user's favorites list.
     *
     * @param vehicle the vehicle to remove
     */
    public void removeFavorite(Vehicle vehicle) {
        favorites.remove(vehicle);
    }

    /**
     * Checks whether the given vehicle is already in the user's favorites.
     *
     * @param vehicle the vehicle to check
     * @return true if the vehicle is favorited, false otherwise
     */
    public boolean isFavorite(Vehicle vehicle) {
        return favorites.contains(vehicle);
    }
}
