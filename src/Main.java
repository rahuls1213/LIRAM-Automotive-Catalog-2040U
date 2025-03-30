import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.out.println("FlatLaf not found, falling back to default.");
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(LandingPage::new); // Show landing GUI
    }
}