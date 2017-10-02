import javax.swing.*;

/**
 * Holds all pertinent information regarding the individual
 * labels used within the minesweeper panel.
 */
public class MineLabel extends JLabel {

    /**
     * The x position within the array
     */
    private int xPos;
    /**
     * The y position within the array
     */
    private int yPos;

    /**
     * Constructs a MineLabel setting the text to
     * the given text, and setting the position to the
     * given x and y positions.
     *
     * @param text The text for the label to display
     * @param xPos The x position within the array
     * @param yPos The y position within the array
     */
    public MineLabel(String text ,int xPos, int yPos) {
        setText(text);
        setHorizontalAlignment(JLabel.CENTER);
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * @return The x position within the array
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * @return The y position within the array
     */
    public int getyPos() {
        return yPos;
    }
}
