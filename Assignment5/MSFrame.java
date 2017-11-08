import javax.swing.*;
import java.awt.*;

/**
 * The input and output class for the minesweeper game.
 *
 * @author Patrick
 */
public class MSFrame extends JFrame {

    private MSPanel panel;

    /**
     * Sets up the frame by: setting the title, setting the size to
     * a square, half the height of the monitor, setting the location
     * to the middle of the screen, and adding a new panel to hold the
     * game.
     */
    public MSFrame() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension size = toolkit.getScreenSize();

        int difficulty = newGame();

        setTitle("Minesweeper");
        setSize(size.height / 2, size.height / 2);
        setLocation((size.width / 2)-(size.height / 4), (size.height / 2) - (size.height / 4));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        panel = new MSPanel(this, difficulty);
        MineBar mBar = new MineBar(this);
        setJMenuBar(mBar);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public MSFrame(MineField field) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension size = toolkit.getScreenSize();

        setTitle("Minesweeper");
        setSize(size.height / 2, size.height / 2);
        setLocation((size.width / 2)-(size.height / 4), (size.height / 2) - (size.height / 4));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        panel = new MSPanel(this, field);
        MineBar mBar = new MineBar(this);
        setJMenuBar(mBar);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static int newGame() {
        String[] options = new String[] {"Easy", "Medium", "Hard"};
        int choice = JOptionPane.showOptionDialog(null, "Select the difficulty", "New Game", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        return choice;
    }

    public MSPanel getPanel() {
        return panel;
    }
}
