import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;

/**
 * Main is the entry point of the Automotive Catalog application.
 *
 * This class initializes the graphical user interface using a custom dark theme (FlatLaf),
 * then launches the {@link LandingPage}, which guides the user into login, registration,
 * or guest access workflows.
 *
 * This file connects directly to the final deliverable's core use case of launching the
 * app in a user-friendly, modern style consistent with the visual branding.
 */
public class Main {
    
    /**
     * Main method that sets the application's look-and-feel and initializes the GUI thread.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Attempt to apply the FlatLaf dark theme for a modern UI look
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            // Fallback if FlatLaf is unavailable on the system
            System.out.println("FlatLaf not found, falling back to default.");
            e.printStackTrace();
        }

        // Launch the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(LandingPage::new);
    }
}