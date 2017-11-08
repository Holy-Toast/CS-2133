import javax.swing.*;
import java.awt.*;

/**
 * The panel in which the entire game takes place
 *
 * @author Patrick
 */
public class MSPanel extends JPanel {

    private MSFrame frame;

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
    public MSPanel(MSFrame frame, int difficulty) {
        this.frame = frame;
        field = new MineField(this, difficulty);

        setLayout(new GridLayout(field.getNumRows(), field.getNumCols()));
        labels = new MineLabel[field.getNumRows()][field.getNumCols()];

        for(int i=0; i<field.getNumCols(); i++) {
            for(int j=0; j<field.getNumRows(); j++) {
                MineLabel newMine = new MineLabel("?", i, j);
                newMine.addMouseListener(new MouseHandler(i, j, this));
                add(newMine);
                labels[i][j] = newMine;
            }
        }
    }

    public MSPanel(MSFrame frame, MineField field) {
        this.frame = frame;
        this.field = field;

        setLayout(new GridLayout(field.getNumRows(), field.getNumCols()));
        labels = new MineLabel[field.getNumRows()][field.getNumCols()];

        for(int i=0; i<field.getNumCols(); i++) {
            for(int j=0; j<field.getNumRows(); j++) {
                MineLabel newMine = new MineLabel("?", i, j);
                newMine.addMouseListener(new MouseHandler(i, j, this));
                add(newMine);
                labels[i][j] = newMine;
            }
        }
        setTheThings();
    }

    /**
     * Displays a winning message and asks the user if
     * they would like to play again.
     */
    private void winGame() {
        int choice = JOptionPane.showConfirmDialog(null, "You Win!", "Play again?", JOptionPane.YES_NO_OPTION);
        if(choice == 0) { // Yes
            close();
            new MSFrame();
        }
        else { // No
            close();
        }
    }

    /**
     * Displays a losing message and asks the user if
     * they would like to play again.
     */
    private void loseGame() {
        int choice = JOptionPane.showConfirmDialog(null, "You Lose! Play again?", "Play again?", JOptionPane.YES_NO_OPTION);
        if(choice == 0) { // Yes
            close();
            new MSFrame();
        }
        else { // No
            close();
        }
    }

    /**
     * Sets the frame visibility to false and disposes the frame.
     */
    public void close() {
        frame.setVisible(false);
        frame.dispose();
    }

    public MSFrame getFrame() {
        return frame;
    }

    /**
     * @return The Mine Field.
     */
    public MineField getField() {
        return field;
    }

    /**
     * First, checks to see if the player has lost, if so, it sets all tiles to
     * explored. If the player has not lost, it cycles through each label and sets
     * each explored tile to it's respective string. X if it's a mine, " " if there
     * are no mines nearby, and 1-9 if there is at least one mine nearby. Lastly, it
     * checks for loss and win conditions and takes the appropriate action if one was
     * triggered.
     */
    public void setTheThings() {
        if(field.getLost()) {
            for(int i=0; i<labels.length; i++) {
                for (int j = 0; j < labels[0].length; j++) {
                    field.getField()[i][j].setExplored(true);
                }
            }
        }
        for(int i=0; i<labels.length; i++) {
            for(int j=0; j<labels[0].length; j++) {
                if(field.getField()[i][j].getExplored()) {
                    if(field.getField()[i][j].getIsMine()) {
                        labels[i][j].setText("X");
                    }
                    else if(field.getField()[i][j].surroundingMines(field.getField()) == 0) {
                        labels[i][j].setText(" ");
                    }
                    else {
                        labels[i][j].setText(Integer.toString(field.getField()[i][j].surroundingMines(field.getField())));
                    }
                }
                else {
                    if(field.getField()[i][j].getFlagged()) {
                        labels[i][j].setText("F");
                    }
                    else {
                        labels[i][j].setText("?");
                    }
                }
            }
        }
        if(field.getLost()) {
            loseGame();
        }
        if(field.winCondition()) {
            winGame();
        }
    }
}
