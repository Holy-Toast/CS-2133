import java.util.*;
import java.io.*;
import java.util.concurrent.*;

/**
 * The minesweeper model class. This class keeps track of
 * the inner working of the minesweeper game.
 *
 * @author Patrick
 */
public class MineField implements Serializable {

    private static final long serialVersionUID = 347103844958234L;

    /**
     * The 2D array which holds all of the mine objects.
     */
    private Mine[][] field;

    /**
     * The number of rows and columns in the minefield.
     */
    private int numRows;
    private int numCols;

    /**
     * The number of mines in the minefield.
     */
    private int numberOfMines = 0;

    /**
     * Keeps track of how many flags the user has placed.
     */
    private int flagsPlaced = 0;

    /**
     * Keeps track of whether or not the player has lost.
     */
    private boolean lost = false;

    private MSPanel panel;

    /**
     * Creates a minefield using the given number of rows and columns by
     * first setting the instance variables, then creating all of the mines
     * in the minefield and setting their position. Then it randomly selects
     * a number of locations in the minefield equal to the number of mines.
     * Those locations are then turned into mines.
     */
    public MineField(MSPanel panel, int difficulty) {

        this.panel = panel;

        if(difficulty == 0) {
            numCols = 10;
            numRows = 10;
            numberOfMines = 10;
        }
        else if(difficulty == 1) {
            numCols = 15;
            numRows = 15;
            numberOfMines = 33;
        }
        else if(difficulty == 2) {
            numCols = 20;
            numRows = 20;
            numberOfMines = 80;
        }
        else {
            System.out.println("Difficulty Error");
            panel.close();
        }

        field = new Mine[numCols][numRows];

        for(int i=0; i<field.length; i++) {
            for(int j=0; j<field[0].length; j++) {
                field[i][j] = new Mine(i, j);
            }
        }

        List<int[]> currentMines = new ArrayList<>();
        for(int i=0; i<numberOfMines; i++) {
            int randCol = ThreadLocalRandom.current().nextInt(0, numCols);
            int randRow = ThreadLocalRandom.current().nextInt(0, numRows);
            int[] randMine = new int[] {randRow, randCol};
            while(currentMines.contains(randMine)) {
                randCol = ThreadLocalRandom.current().nextInt(0, numCols);
                randRow = ThreadLocalRandom.current().nextInt(0, numRows);
                randMine = new int[] {randRow, randCol};
            }
            currentMines.add(randMine);
        }
        for(int i=0; i<currentMines.size(); i++) {
            int[] mineCoord = currentMines.get(i);
            int rowNum = mineCoord[0];
            int colNum = mineCoord[1];
            field[rowNum][colNum].setIsMine(true);
        }
    }

    /**
     *
     *
     * @param x The x location
     * @param y The y location
     */
    public void flagMine(int x, int y) {

        if(!field[x][y].getExplored()) {
            if (field[x][y].getFlagged()) {
                flagsPlaced--;
                field[x][y].setFlagged(false);
            } else {
                flagsPlaced++;
                field[x][y].setFlagged(true);
            }
        }
    }

    /**
     *
     *
     * @param x The x location
     * @param y The y location
     */
    public void stepMine(int x, int y) {

        if(!field[x][y].getFlagged()) {
            field[x][y].setExplored(true);

            if(field[x][y].surroundingMines(field) == 0) {

                if (field[x][y].getX() > 0) {
                    if(!field[x-1][y].getExplored()) {
                        stepMine(x-1, y);
                    }
                    if (field[x][y].getY() > 0) {
                        if(!field[x-1][y-1].getExplored()) {
                            stepMine(x-1, y-1);
                        }
                    }
                }
                if (field[x][y].getY() > 0) {
                    if(!field[x][y-1].getExplored()) {
                        stepMine(x, y-1);
                    }
                    if (field[x][y].getX() < field.length - 1) {
                        if(!field[x+1][y-1].getExplored()) {
                            stepMine(x+1, y-1);
                        }
                    }
                }
                if (field[x][y].getX() < field.length - 1) {
                    if(!field[x+1][y].getExplored()) {
                        stepMine(x+1, y);
                    }
                    if (field[x][y].getY() < field[0].length - 1) {
                        if(!field[x+1][y+1].getExplored()) {
                            stepMine(x+1, y+1);
                        }
                    }
                }
                if (field[x][y].getY() < field[0].length - 1) {
                    if(!field[x][y+1].getExplored()) {
                        stepMine(x, y+1);
                    }
                    if (field[x][y].getX() > 0) {
                        if(!field[x-1][y+1].getExplored()) {
                            stepMine(x-1, y+1);
                        }
                    }
                }
            }
            if(field[x][y].getIsMine()) {
                lost = true;
            }
        }
    }

    /**
     * First counts the number of active mines which have been flagged.
     * Then, if the number of flagged active mines equal the total
     * number of mines, it returns true. Otherwise it returns false.
     *
     * @return whether or not the player has won
     */
    public boolean winCondition() {
        int count = 0;
        if(flagsPlaced == numberOfMines) {
            for(int i=0; i<field.length; i++) {
                for(int j=0; j<field[0].length; j++) {
                    if(field[i][j].getIsMine()) {
                        if(field[i][j].getFlagged()) {
                            count++;
                        }
                    }
                }
            }
            if(count == numberOfMines) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return the number of columns
     */
    public int getNumCols() {
        return numCols;
    }

    /**
     * @return the number of rows
     */
    public int getNumRows() {
        return numRows;
    }

    /**
     * @return Whether or not the game has been lost.
     */
    public boolean getLost() {
        return lost;
    }

    /**
     * @return The mine array.
     */
    public Mine[][] getField() {
        return field;
    }

    public void newGame() {
        panel.close();
        new MSFrame();
    }

    public void newGame(MineField field) {
        panel.close();
        new MSFrame(field);
    }

    public void saveGame(File file) {
        FileOutputStream out = null;
        ObjectOutputStream oos = null;

        try {
            out = new FileOutputStream(file);
            oos = new ObjectOutputStream(out);
            oos.writeObject(this);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if(out != null) {
                try {
                    out.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
            if(oos != null) {
                try {
                    oos.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadGame(File file) {
        FileInputStream in = null;
        ObjectInputStream ois = null;

        try {
            in = new FileInputStream(file);
            ois = new ObjectInputStream(in);
            MineField loadedField = (MineField) ois.readObject();
            newGame(loadedField);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
            if(ois != null) {
                try {
                    ois.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
