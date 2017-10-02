import javax.swing.*;
import java.awt.*;
/**
 * Blueprint for the triangle panel object.
 *
 * @author Patrick Roeber
 */
public class TriPanel extends JPanel {

    /**
     * When called, gets the current width and height of the
     * panel, corrects the dimension of the panel, then draws
     * the triangles.
     *
     * @param g the graphics object provided by java
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Dimension panelSize = new Dimension(getWidth(), getHeight());
        panelSize = correctDimension(panelSize);

        drawTriangle(0 ,0, panelSize, g);
    }

    /**
     * Takes in a dimension and finds the smaller of the two,
     * then returns a new dimension that is a square using the smaller
     * side of the given dimension.
     *
     * @param size the size to be corrected
     * @return the corrected size
     */
    private Dimension correctDimension(Dimension size) {
        int newSide;

        if(size.width > size.height) {
            newSide = size.height;
        }
        else {
            newSide = size.width;
        }
        return new Dimension(newSide, newSide);
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
