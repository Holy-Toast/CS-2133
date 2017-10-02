import javax.swing.*;
/**
 * Creates the object to be displayed to the user.
 *
 * @author Patrick Roeber
 */
public class MessageFrame extends JFrame {
    /**
     * The default height for the frame.
     */
    private static final int FRAME_HEIGHT = 250;
    /**
     * The default width of the frame.
     */
    private static final int FRAME_WIDTH = 100;

    /**
     * Creates the frame, sets the title, size, close operation,
     * and adds a message panel to the frame.
     */
    public MessageFrame() {
        setTitle("Message in a Bottle");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MessagePanel panel = new MessagePanel();
        add(panel);
        setVisible(true);
    }
}
