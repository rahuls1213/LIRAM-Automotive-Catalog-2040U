import javax.swing.*;
import java.awt.*;

public class DarkGreenUI {
    public static void applyDarkGreenTheme(JFrame frame) {
        Color darkGreen = new Color(34, 45, 40); 
        Color lightGreen = new Color(50, 80, 60); 
        Color textColor = Color.WHITE;
        
        frame.getContentPane().setBackground(darkGreen);
        
        for (Component c : frame.getContentPane().getComponents()) {
            if (c instanceof JLabel || c instanceof JButton) {
                c.setForeground(textColor);
                c.setBackground(lightGreen);
            }
        }

        UIManager.put("Panel.background", darkGreen);
        UIManager.put("Button.background", lightGreen);
        UIManager.put("Button.foreground", textColor);
        UIManager.put("Label.foreground", textColor);
    }
}
