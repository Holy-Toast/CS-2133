import javax.swing.*;
import java.awt.*;
/**
 * Creates the message panel which will be the panel
 * that the program draws on.
 *
 * @author Patrick Roeber.
 */
public class MessagePanel extends JPanel {

    /**
     * Draws several lines, arcs, and ovals which all
     * come together and form a bottle shape. Then it
     * draws 4 lines of text inside the bottle.
     *
     * @param g the graphics object provided by java
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawOval(30, 10, 20, 10);
        g.drawLine(30, 15, 30, 35);
        g.drawLine(50, 15, 50, 35);
        g.drawArc(50, 25, 20, 20, 180, 90);
        g.drawArc(10, 25, 20, 20, 0, -90);
        g.drawArc(50, 45, 20, 20, 90, -90);
        g.drawArc(10, 45, 20, 20, 90, 90);
        g.drawLine(10, 55, 10, 150);
        g.drawLine(70, 55, 70, 150);
        g.drawOval(10, 140, 60, 20);
        g.drawString(" Test", 24, 65);
        g.drawString("Bottle", 25, 85);
        g.drawString("Please", 22, 105);
        g.drawString("Ignore", 24, 125);
    }
}
