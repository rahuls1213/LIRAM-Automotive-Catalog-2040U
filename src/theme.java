import javax.swing.*;
import java.awt.*;

public class DarkGreenUI {
    public static void applyDarkGreenTheme(JFrame frame) {

        Color darkGreen = new Color(34, 45, 40); 
        Color black = new Color(20, 25, 22); 
        Color lightGreen = new Color(50, 120, 70); 
        Color textColor = Color.WHITE;
        
        frame.getContentPane().setBackground(darkGreen);
        
        for (Component c : frame.getContentPane().getComponents()) {
            if (c instanceof JLabel || c instanceof JButton) {
                c.setForeground(textColor);
                c.setBackground(lightGreen);
            } else if (c instanceof JPanel) {
                c.setBackground(black);
            }
        }
        
        UIManager.put("Panel.background", black);
        UIManager.put("Button.background", lightGreen);
        UIManager.put("Button.foreground", textColor);
        UIManager.put("Label.foreground", textColor);
        UIManager.put("TextField.background", darkGreen);
        UIManager.put("TextField.foreground", textColor);
        UIManager.put("TextField.caretForeground", textColor);
    }
}
