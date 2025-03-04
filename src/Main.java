public class Main {
    public static void main(String[] args) {
        VehicleDatabase.loadVehicles();
        new CarViewer(VehicleDatabase.getVehicles());
    }
}
