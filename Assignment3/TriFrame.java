import javax.swing.*;
import java.awt.*;
/**
 * @author Patrick Roeber
 */
public class TriFrame extends JFrame {
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
