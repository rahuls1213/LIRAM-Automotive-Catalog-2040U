import javax.swing.*;
import java.awt.*;

public class CarComparisonWindow extends JFrame {

    public CarComparisonWindow(Vehicle car1, Vehicle car2) {
        setTitle("Car Comparison");
        setSize(1000, 600);
        setLayout(new GridLayout(1, 2));

        add(createCarPanel(car1));
        add(createCarPanel(car2));

        setLocationRelativeTo(null); // Center screen
        setVisible(true);
    }

    private JPanel createCarPanel(Vehicle car) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        ImageIcon icon = new ImageIcon(new ImageIcon(car.getImageUrl()).getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH));
        imageLabel.setIcon(icon);

        JTextArea info = new JTextArea();
        info.setEditable(false);
        info.setText("Make: " + car.getMake() +
                     "\nModel: " + car.getModel() +
                     "\nYear: " + car.getYear() +
                     "\nFuel Type: " + car.getFuelType() +
                     "\nReview: " + car.getReview());

        panel.add(imageLabel, BorderLayout.NORTH);
        panel.add(info, BorderLayout.CENTER);

        return panel;
    }
}
