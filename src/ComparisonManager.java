import java.util.ArrayList;
import java.util.List;

public class ComparisonManager {
    private static List<Vehicle> comparisonList = new ArrayList<>();

    public static void addForComparison(Vehicle vehicle) {
        if (!comparisonList.contains(vehicle)) {
            comparisonList.add(vehicle);
        }
    }

    public static void removeFromComparison(Vehicle vehicle) {
        comparisonList.remove(vehicle);
    }

    public static List<Vehicle> getComparisonList() {
        return new ArrayList<>(comparisonList);
    }

    public static void clearComparison() {
        comparisonList.clear();
    }
}
