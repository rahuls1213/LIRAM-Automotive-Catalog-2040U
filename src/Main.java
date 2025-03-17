import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        VehicleDatabase.loadVehicles();
        javax.swing.SwingUtilities.invokeLater(() -> new CarViewer(VehicleDatabase.getVehicles()));
    }
}
