import java.util.ArrayList;
import java.util.List;

public class FavoritesManager {
    private static List<Vehicle> favorites = new ArrayList<>();
    
    public static void addFavorite(Vehicle vehicle) {
        if (!favorites.contains(vehicle)) {
            favorites.add(vehicle);
        }
    }
    
    public static void removeFavorite(Vehicle vehicle) {
        favorites.remove(vehicle);
    }
    
    public static List<Vehicle> getFavorites() {
        return new ArrayList<>(favorites);
    }
    
    public static boolean isFavorite(Vehicle vehicle) {
        return favorites.contains(vehicle);
    }
}
