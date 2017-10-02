import javax.swing.*;
/**
 * @author Patrick Roeber
 */
public class MessageFrame extends JFrame {

    public static final int FRAME_HEIGHT = 250;
    public static final int FRAME_WIDTH = 100;

    public MessageFrame() {
        setTitle("Message in a Bottle");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MessagePanel panel = new MessagePanel();
        add(panel);
        setVisible(true);
    }
}
