import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The input and output class for the minesweeper game.
 *
 * @author Patrick
 */
public class MSFrame extends JFrame {

    /**
     * Sets up the frame by: setting the title, setting the size to
     * a square, half the height of the monitor, setting the location
     * to the middle of the screen, and adding a new panel to hold the
     * game.
     */
    public MSFrame() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension size = toolkit.getScreenSize();

        setTitle("Minesweeper");
        setSize(size.height / 2, size.height / 2);
        setLocation((size.width / 2)-(size.height / 4), (size.height / 2) - (size.height / 4));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MSPanel sweeper = new MSPanel();
        add(sweeper);
        setVisible(true);
    }

    /**
     *
     */
    public void closeFrame() {
        setVisible(false);
        dispose();
    }

    /**
     * The panel in which the entire game takes place
     */
    private class MSPanel extends JPanel {

        /**
         * The number of columns in the minesweeper game
         */
        public static final int COLUMNS = 10; //X
        /**
         * The number of rows in the minesweeper game
         */
        public static final int ROWS = 10;    //Y

        /**
         * The number of mines to be created.
         */
        private final int NUM_MINES = 10;

        /**
         * The minefield object to be used within the program
         */
        private MineField field;
        /**
         * The array of labels which will display all of the mines.
         */
        private MineLabel[][] labels;

        /**
         * Creates the panel which holds everything needed for the game to run.
         * First creates a new layout, a new minefield, and a new label array.
         * Then it creates each label, giving each a default text of ? and an
         * appropriate location within the array. It adds a mouse listener to
         * each label which calls flagMine when the right mouse button is pressed
         * and calls stepMine when any other button is pressed. Then calls
         * setMineText to change the label's text.
         */
        private MSPanel() {
            setLayout(new GridLayout(ROWS, COLUMNS));
            field = new MineField(ROWS, COLUMNS, NUM_MINES);
            labels = new MineLabel[ROWS][COLUMNS];

            for(int i=0; i<field.getNumCols(); i++) {
                for(int j=0; j<field.getNumRows(); j++) {
                    final MineLabel newMine = new MineLabel("?", i, j);
                    newMine.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if(e.getButton() == MouseEvent.BUTTON3) {
                                field.flagMine(newMine);
                                if(field.getWon()) {
                                    winGame();
                                }
                            }
                            else {
                                field.stepMine(newMine, labels);
                                if(field.getLost()) {
                                    loseGame();
                                }
                            }
                            field.setMineText(labels);
                        }
                    });
                    add(newMine);
                    labels[i][j] = newMine;
                }
            }
        }

        /**
         * Displays a winning message and asks the user if
         * they would like to play again.
         */
        public void winGame() {
            int choice = JOptionPane.showConfirmDialog(null, "You Win!", "Play again?", JOptionPane.YES_NO_OPTION);
            if(choice == 0) { // Yes
                setVisible(false);
                closeFrame();
                new MSFrame();
            }
            else { // No
                setVisible(false);
                dispose();
            }
        }

        /**
         * Displays a losing message and asks the user if
         * they would like to play again.
         */
        public void loseGame() {
            int choice = JOptionPane.showConfirmDialog(null, "You Lose! Play again?", "Play again?", JOptionPane.YES_NO_OPTION);
            if(choice == 0) { // Yes
                setVisible(false);
                closeFrame();
                new MSFrame();
            }
            else { // No
                setVisible(false);
                dispose();
            }
        }
    }
}
