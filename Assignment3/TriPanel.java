import javax.swing.*;
import java.awt.*;
/**
 * @author Patrick Roeber
 */
public class TriPanel extends JPanel {

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Dimension panelSize = new Dimension(getWidth(), getHeight());
        panelSize = correctDimension(panelSize);

        drawTriangle(0 ,0, panelSize, g);
    }

    private Dimension correctDimension(Dimension size) {
        int newSide;

        if(size.width > size.height) {
            newSide = size.height;
        }
        else {
            newSide = size.width;
        }
        Dimension newSize = new Dimension(newSide, newSide);
        return newSize;
    }

    /**
     * Recursively draws the triangles
     *
     * @param x    Top left coord for x
     * @param y    Top left coord for y
     * @param size Size of the panel
     * @param g    Graphics
     */
    private void drawTriangle(int x, int y, Dimension size, Graphics g) {
        if(size.width <= 2) {
            g.drawRect(x, y, 1, 1);
        }
        else {
            Dimension newSize = new Dimension(size.width / 2, size.height / 2);

            int newX = (size.width / 4) + x;
            int newY = y;
            drawTriangle(newX, newY, newSize, g); //Top Middle

            newX = x;
            newY = (size.height / 2) + y;
            drawTriangle(newX, newY, newSize, g); //Bottom Left

            newX = (size.width / 2) + x;
            newY = (size.height / 2) + y;
            drawTriangle(newX, newY, newSize, g); //Bottom Right
        }
    }
}
