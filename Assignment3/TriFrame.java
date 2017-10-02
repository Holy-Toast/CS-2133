import javax.swing.*;
import java.awt.*;
/**
 * Blueprint for the triangle frame object.
 *
 * @author Patrick Roeber
 */
public class TriFrame extends JFrame {

    /**
     * Creates the triangle frame by:
     * getting the default toolkit and setting the size
     * of the frame to be half of the screen size,
     * setting the title, and close operation, and
     * then adding the triangle panel.
     */
    public TriFrame() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension size = toolkit.getScreenSize();

        setTitle("Sierpenski's Triangle");
        setSize(size.width / 2, size.height / 2);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        TriPanel panel = new TriPanel();
        add(panel);
        setVisible(true);
    }
}
