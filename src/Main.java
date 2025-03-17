import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        try {
            // Apply FlatLaf Theme
            UIManager.setLookAndFeel(new FlatDarkLaf());  // Dark theme
        } catch (Exception e) {
            System.out.println("FlatLaf not found, falling back to default.");
            e.printStackTrace();
        }
        
        // Load vehicles and start the GUI
        VehicleDatabase.loadVehicles();
        javax.swing.SwingUtilities.invokeLater(() -> new CarViewer(VehicleDatabase.getVehicles()));
    }
}
