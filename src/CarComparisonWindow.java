import javax.swing.*;
import java.awt.*;

public class CarComparisonWindow extends JFrame {

    // Light mode theme colors
    private final Color BACKGROUND_COLOR = new Color(245, 245, 220); // Beige
    private final Color CARD_COLOR = Color.WHITE;
    private final Color TEXT_PRIMARY = new Color(26, 26, 64); // Navy
    private final Color TEXT_SECONDARY = new Color(51, 51, 51);
    private final Font HEADER_FONT = new Font("SansSerif", Font.BOLD, 20);
    private final Font BODY_FONT = new Font("SansSerif", Font.PLAIN, 14);

    public CarComparisonWindow(Vehicle car1, Vehicle car2) {
        setTitle("Car Comparison");
        setSize(1100, 650);
        setLayout(new GridLayout(1, 2, 10, 0)); // side-by-side layout with spacing
        getContentPane().setBackground(BACKGROUND_COLOR);

        add(createCarPanel(car1));
        add(createCarPanel(car2));

        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }

    private JPanel createCarPanel(Vehicle car) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(CARD_COLOR);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Image
        JLabel imageLabel = new JLabel();
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ImageIcon icon = new ImageIcon(new ImageIcon(car.getImageUrl()).getImage()
                .getScaledInstance(350, 230, Image.SCALE_SMOOTH));
        imageLabel.setIcon(icon);
        panel.add(imageLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Title (Make + Model)
        JLabel title = new JLabel(car.getMake() + " " + car.getModel());
        title.setFont(HEADER_FONT);
        title.setForeground(TEXT_PRIMARY);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 8)));

        // Year + Fuel
        JLabel yearFuel = new JLabel("Year: " + car.getYear() + " | Fuel: " + car.getFuelType());
        yearFuel.setFont(BODY_FONT);
        yearFuel.setForeground(TEXT_SECONDARY);
        yearFuel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(yearFuel);
        panel.add(Box.createRigidArea(new Dimension(0, 4)));

        // Price
        JLabel price = new JLabel("Price: " + car.getPrice());
        price.setFont(BODY_FONT);
        price.setForeground(TEXT_SECONDARY);
        price.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(price);
        panel.add(Box.createRigidArea(new Dimension(0, 12)));

        // Review
        JTextArea review = new JTextArea(car.getReview());
        review.setWrapStyleWord(true);
        review.setLineWrap(true);
        review.setEditable(false);
        review.setFocusable(false);
        review.setBackground(CARD_COLOR);
        review.setFont(BODY_FONT);
        review.setForeground(TEXT_SECONDARY);
        review.setAlignmentX(Component.CENTER_ALIGNMENT);
        review.setMaximumSize(new Dimension(400, 100));
        review.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(review);

        return panel;
    }
}
