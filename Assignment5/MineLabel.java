import javax.swing.*;

/**
 * Holds all pertinent information regarding the individual
 * labels used within the minesweeper panel.
 */
public class MineLabel extends JLabel {

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
    }
}
