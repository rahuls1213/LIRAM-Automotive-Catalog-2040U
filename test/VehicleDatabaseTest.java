import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;

public class VehicleDatabaseTest {

    private static List<Vehicle> vehicles;

    @BeforeAll
    public static void setup() {
        try {
            VehicleDatabase.loadVehicles();
            vehicles = VehicleDatabase.getVehicles();
        } catch (Exception e) {
            fail("Error loading vehicles: " + e.getMessage());
        }
    }

    @Test
    public void testVehicleListNotEmpty() {
        assertNotNull(vehicles, "Vehicle list should not be null");
        assertEquals(50, vehicles.size(), "Vehicle list should contain 50 vehicles");
    }

    @Test
    public void testToyotaCorolla() {
        Vehicle car = getVehicleByMakeModel("Toyota", "Corolla");
        assertNotNull(car, "Toyota Corolla should exist in the database");
        assertEquals(2022, car.getYear());
        assertEquals("Gasoline", car.getFuelType());
        assertEquals("images/corolla.jpg", car.getImageUrl());
        assertEquals("Reliable and fuel-efficient. A great choice!", car.getReview());
    }

    @Test
    public void testTeslaModel3() {
        Vehicle car = getVehicleByMakeModel("Tesla", "Model 3");
        assertNotNull(car, "Tesla Model 3 should exist in the database");
        assertEquals(2023, car.getYear());
        assertEquals("Electric", car.getFuelType());
        assertEquals("images/model3.jpg", car.getImageUrl());
        assertEquals("A fantastic EV with cutting-edge technology.", car.getReview());
    }

    @Test
    public void testVehicleDatabaseNotEmpty() {
        System.out.println("Vehicles loaded: " + vehicles.size());
        assertFalse(vehicles.isEmpty(), "Vehicle database should not be empty");
    }

    private Vehicle getVehicleByMakeModel(String make, String model) {
        for (Vehicle v : vehicles) {
            if (v.getMake().equals(make) && v.getModel().equals(model)) {
                return v;
            }
        }
        return null;
    }
}
