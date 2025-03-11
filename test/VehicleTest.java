public class VehicleTest 
{
    @Test
    public void testVehicleCreation() 
    {
        Vehicle vehicle = new Vehicle("Toyota", "Corolla", 2022, "Gasoline", "images/corolla.jpg", "Reliable.");

        assertEquals("Toyota", vehicle.getMake());
        assertEquals("Corolla", vehicle.getModel());
        assertEquals(2022, vehicle.getYear());
        assertEquals("Gasoline", vehicle.getFuelType());
        assertEquals("images/corolla.jpg", vehicle.getImageUrl());
        assertEquals("Reliable.", vehicle.getReview());
    }
}
