import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class VehicleTest {

    @Test
    public void testToyotaCorolla() {
        Vehicle vehicle = new Vehicle("Toyota", "Corolla", 2022, "Gasoline", "images/corolla.jpg", "Reliable and fuel-efficient. A great choice!");
        assertEquals("Toyota", vehicle.getMake());
        assertEquals("Corolla", vehicle.getModel());
        assertEquals(2022, vehicle.getYear());
        assertEquals("Gasoline", vehicle.getFuelType());
        assertEquals("images/corolla.jpg", vehicle.getImageUrl());
        assertEquals("Reliable and fuel-efficient. A great choice!", vehicle.getReview());
    }

    @Test
    public void testTeslaModel3() {
        Vehicle vehicle = new Vehicle("Tesla", "Model 3", 2023, "Electric", "images/model3.jpg", "A fantastic EV with cutting-edge technology.");
        assertEquals("Tesla", vehicle.getMake());
        assertEquals("Model 3", vehicle.getModel());
        assertEquals(2023, vehicle.getYear());
        assertEquals("Electric", vehicle.getFuelType());
        assertEquals("images/model3.jpg", vehicle.getImageUrl());
        assertEquals("A fantastic EV with cutting-edge technology.", vehicle.getReview());
    }

    @Test
    public void testHondaCivic() {
        Vehicle vehicle = new Vehicle("Honda", "Civic", 2021, "Gasoline", "images/civic.jpg", "A durable sedan with excellent mileage and handling.");
        assertEquals("Honda", vehicle.getMake());
        assertEquals("Civic", vehicle.getModel());
        assertEquals(2021, vehicle.getYear());
        assertEquals("Gasoline", vehicle.getFuelType());
        assertEquals("images/civic.jpg", vehicle.getImageUrl());
        assertEquals("A durable sedan with excellent mileage and handling.", vehicle.getReview());
    }

    @Test
    public void testFordMustang() {
        Vehicle vehicle = new Vehicle("Ford", "Mustang", 2022, "Gasoline", "images/mustang.jpg", "An American muscle car icon with a powerful engine.");
        assertEquals("Ford", vehicle.getMake());
        assertEquals("Mustang", vehicle.getModel());
        assertEquals(2022, vehicle.getYear());
        assertEquals("Gasoline", vehicle.getFuelType());
        assertEquals("images/mustang.jpg", vehicle.getImageUrl());
        assertEquals("An American muscle car icon with a powerful engine.", vehicle.getReview());
    }

    @Test
    public void testBMWM3() {
        Vehicle vehicle = new Vehicle("BMW", "M3", 2023, "Gasoline", "images/m3.jpg", "A luxury sports sedan with high performance.");
        assertEquals("BMW", vehicle.getMake());
        assertEquals("M3", vehicle.getModel());
        assertEquals(2023, vehicle.getYear());
        assertEquals("Gasoline", vehicle.getFuelType());
        assertEquals("images/m3.jpg", vehicle.getImageUrl());
        assertEquals("A luxury sports sedan with high performance.", vehicle.getReview());
    }

    @Test
    public void testMercedesCClass() {
        Vehicle vehicle = new Vehicle("Mercedes", "C-Class", 2022, "Hybrid", "images/cclass.jpg", "A stylish luxury sedan with cutting-edge features.");
        assertEquals("Mercedes", vehicle.getMake());
        assertEquals("C-Class", vehicle.getModel());
        assertEquals(2022, vehicle.getYear());
        assertEquals("Hybrid", vehicle.getFuelType());
        assertEquals("images/cclass.jpg", vehicle.getImageUrl());
        assertEquals("A stylish luxury sedan with cutting-edge features.", vehicle.getReview());
    }

    @Test
    public void testChevroletCamaro() {
        Vehicle vehicle = new Vehicle("Chevrolet", "Camaro", 2021, "Gasoline", "images/camaro.jpg", "A bold design with aggressive performance.");
        assertEquals("Chevrolet", vehicle.getMake());
        assertEquals("Camaro", vehicle.getModel());
        assertEquals(2021, vehicle.getYear());
        assertEquals("Gasoline", vehicle.getFuelType());
        assertEquals("images/camaro.jpg", vehicle.getImageUrl());
        assertEquals("A bold design with aggressive performance.", vehicle.getReview());
    }

    @Test
    public void testNissanLeaf() {
        Vehicle vehicle = new Vehicle("Nissan", "Leaf", 2023, "Electric", "images/leaf.jpg", "An affordable and practical electric vehicle.");
        assertEquals("Nissan", vehicle.getMake());
        assertEquals("Leaf", vehicle.getModel());
        assertEquals(2023, vehicle.getYear());
        assertEquals("Electric", vehicle.getFuelType());
        assertEquals("images/leaf.jpg", vehicle.getImageUrl());
        assertEquals("An affordable and practical electric vehicle.", vehicle.getReview());
    }

    @Test
    public void testHyundaiElantra() {
        Vehicle vehicle = new Vehicle("Hyundai", "Elantra", 2022, "Hybrid", "images/elantra.jpg", "A compact sedan with modern features and hybrid efficiency.");
        assertEquals("Hyundai", vehicle.getMake());
        assertEquals("Elantra", vehicle.getModel());
        assertEquals(2022, vehicle.getYear());
        assertEquals("Hybrid", vehicle.getFuelType());
        assertEquals("images/elantra.jpg", vehicle.getImageUrl());
        assertEquals("A compact sedan with modern features and hybrid efficiency.", vehicle.getReview());
    }

    @Test
    public void testVolkswagenGolfGTI() {
        Vehicle vehicle = new Vehicle("Volkswagen", "Golf GTI", 2021, "Gasoline", "images/golfgti.jpg", "A sporty hatchback with a fun driving experience.");
        assertEquals("Volkswagen", vehicle.getMake());
        assertEquals("Golf GTI", vehicle.getModel());
        assertEquals(2021, vehicle.getYear());
        assertEquals("Gasoline", vehicle.getFuelType());
        assertEquals("images/golfgti.jpg", vehicle.getImageUrl());
        assertEquals("A sporty hatchback with a fun driving experience.", vehicle.getReview());
    }
}