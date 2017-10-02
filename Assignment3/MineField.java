import java.util.*;

/**
 * The minesweeper model class. This class keeps track of
 * the inner working of the minesweeper game.
 *
 * @author Patrick
 */
public class MineField {

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

    /**
     *
     */
    private boolean won = false;

    /**
     * Creates a minefield using the given number of rows and columns by
     * first setting the instance variables, then creating all of the mines
     * in the minefield and setting their position. Then it randomly selects
     * a number of locations in the minefield equal to the number of mines.
     * Those locations are then turned into mines.
     *
     * @param numRows the number of rows in the minefield
     * @param numCols the number of columns in the minefield
     * @param numMines the number of mines to be placed in the minefield
     */
    public MineField(int numRows, int numCols, int numMines) {

        field = new Mine[numCols][numRows];

        this.numCols = numCols;
        this.numRows = numRows;
        numberOfMines = numMines;

        for(int i=0; i<field.length; i++) {
            for(int j=0; j<field[0].length; j++) {
                field[i][j] = new Mine(i, j);
            }
        }
        List<Integer> currentMines = new ArrayList<>();
        for(int i=0; i<numMines; i++) {
            double randMineNumber = Math.random() * 100;
            while(currentMines.contains((int)randMineNumber)) {
                randMineNumber = Math.random() * 100;
            }
            currentMines.add((int)randMineNumber);
        }
        for(int i=0; i<currentMines.size(); i++) {
            int rowNum = currentMines.get(i) / 10;
            int colNum = currentMines.get(i) % 10;
            field[rowNum][colNum].setIsMine(true);
        }
    }

    /**
     * Takes in a particular mine label and checks to see if it was
     * already explored. If not, it checks to see if it has been flagged already.
     * If so, it decrements the number of flags placed, sets the mine to unflagged, and
     * changes the text to a ?. If it hadn't already been flagged it increments
     * the number of flags placed, sets the to mine to flagged, and sets the text to F.
     *
     * @param ml the mine label to be flagged
     */
    public void flagMine(MineLabel ml) {
        int x = ml.getxPos();
        int y = ml.getyPos();

        if(!field[x][y].getExplored()) {
            if (field[x][y].getFlagged()) {
                flagsPlaced--;
                field[x][y].setFlagged(false);
                ml.setText("?");
            } else {
                flagsPlaced++;
                field[x][y].setFlagged(true);
                ml.setText("F");
            }
        }
    }

    /**
     * Takes in a particular mine label, and the entire array of mine labels.
     * It first checks to make sure that the mine is not already flagged, if not,
     * it sets the mine to explored and then checks the surrounding mines. If none
     * of the surrounding mines are active, then it recursively steps on each of the
     * surrounding mines. If the initial mine was active, it sets lost to true.
     *
     * @param ml the mine label to be checked
     * @param labels the array which the mine labels are located in
     */
    public void stepMine(MineLabel ml, MineLabel[][] labels) {
        int x = ml.getxPos();
        int y = ml.getyPos();

        if(!field[x][y].getFlagged()) {
            field[x][y].setExplored(true);

            if(field[x][y].surroundingMines(field) == 0) {

                if (field[x][y].getX() > 0) {
                    if(!field[x-1][y].getExplored()) {
                        MineLabel nextMine = labels[x-1][y];
                        stepMine(nextMine, labels);
                    }
                    if (field[x][y].getY() > 0) {
                        if(!field[x-1][y-1].getExplored()) {
                            MineLabel nextMine = labels[x-1][y-1];
                            stepMine(nextMine, labels);
                        }
                    }
                }
                if (field[x][y].getY() > 0) {
                    if(!field[x][y-1].getExplored()) {
                        MineLabel nextMine = labels[x][y-1];
                        stepMine(nextMine, labels);
                    }
                    if (field[x][y].getX() < field.length - 1) {
                        if(!field[x+1][y-1].getExplored()) {
                            MineLabel nextMine = labels[x+1][y-1];
                            stepMine(nextMine, labels);
                        }
                    }
                }
                if (field[x][y].getX() < field.length - 1) {
                    if(!field[x+1][y].getExplored()) {
                        MineLabel nextMine = labels[x+1][y];
                        stepMine(nextMine, labels);
                    }
                    if (field[x][y].getY() < field[0].length - 1) {
                        if(!field[x+1][y+1].getExplored()) {
                            MineLabel nextMine = labels[x+1][y+1];
                            stepMine(nextMine, labels);
                        }
                    }
                }
                if (field[x][y].getY() < field[0].length - 1) {
                    if(!field[x][y+1].getExplored()) {
                        MineLabel nextMine = labels[x][y+1];
                        stepMine(nextMine, labels);
                    }
                    if (field[x][y].getX() > 0) {
                        if(!field[x-1][y+1].getExplored()) {
                            MineLabel nextMine = labels[x-1][y+1];
                            stepMine(nextMine, labels);
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
     * First, checks to see if the player has lost, if so, it sets all tiles to
     * explored. If the player has not lost, it cycles through each label and sets
     * each explored tile to it's respective string. X if it's a mine, " " if there
     * are no mines nearby, and 1-9 if there is at least one mine nearby. Lastly, it
     * checks for loss and win conditions and takes the appropriate action if one was
     * triggered.
     *
     * @param labels the array of mine labels to be modified
     */
    public void setMineText(MineLabel[][] labels) {
        if(lost) {
            for(int i=0; i<labels.length; i++) {
                for (int j = 0; j < labels[0].length; j++) {
                    field[i][j].setExplored(true);
                }
            }
        }
        for(int i=0; i<labels.length; i++) {
            for(int j=0; j<labels[0].length; j++) {
                if(field[i][j].getExplored()) {
                    if(field[i][j].getIsMine()) {
                        labels[i][j].setText("X");
                    }
                    else if(field[i][j].surroundingMines(field) == 0) {
                        labels[i][j].setText(" ");
                    }
                    else {
                        labels[i][j].setText(Integer.toString(field[i][j].surroundingMines(field)));
                    }
                }
            }
        }
        if(lost) {
            System.out.println("You lose!");
        }
        if(winCondition()) {
            System.out.println("You win!");
        }
    }

    /**
     * First counts the number of active mines which have been flagged.
     * Then, if the number of flagged active mines equal the total
     * number of mines, it returns true. Otherwise it returns false.
     *
     * @return whether or not the player has won
     */
    private boolean winCondition() {
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
                won = true;
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
     * @return Whether or not the game has been won.
     */
    public boolean getWon() {
        return won;
    }

    /**
     * @return Whether or not the game has been lost.
     */
    public boolean getLost() {
        return lost;
    }

    /**
     *
     */
    private class Mine {
        /**
         * The location of the mine within the mine array
         */
        private int x = 0;
        private int y = 0;
        /**
         * Whether or not the mine is active
         */
        private boolean isMine = false;
        /**
         * Whether or not the mine has been revealed
         */
        private boolean explored = false;
        /**
         * Whether or not the mine is flagged
         */
        private boolean flagged = false;

        /**
         * Creates a new mine with a location of (x, y).
         *
         * @param x The x location in the array
         * @param y The y location in the array
         */
        private Mine(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * @param isMine sets the mine to active or not
         */
        private void setIsMine(boolean isMine) {
            this.isMine = isMine;
        }

        /**
         * @param explored sets the mine to explored or unexplored
         */
        private void setExplored(boolean explored) {
            this.explored = explored;
        }

        /**
         * @param flagged Sets the mine to flagged or not
         */
        private void setFlagged(boolean flagged) {
            this.flagged = flagged;
        }

        /**
         * @return The x location in the array
         */
        private int getX() {
            return x;
        }

        /**
         * @return The y location in the array.
         */
        private int getY() {
            return y;
        }

        /**
         * @return Whether or not the mine is active
         */
        private boolean getIsMine() {
            return isMine;
        }

        /**
         * @return Whether or not the mine has been explored
         */
        private boolean getExplored() {
            return explored;
        }

        /**
         * @return Whether or not the mine has been flagged
         */
        private boolean getFlagged() {
            return flagged;
        }

        /**
         * Takes in a minefield and starts up a counter at 0. It then checks
         * the position: Left, Left-Below, Below, Right-Below, Right,
         * Right-Above, Above, and Left-Above. If it finds a mine in any of
         * these location it increments the counter. Then at the end, it returns
         * the final number in the counter.
         *
         * @param mf The minefield to check in
         * @return The number of active mines surrounding this particular
         *         mine.
         */
        private int surroundingMines(Mine[][] mf) {
            int counter = 0;
            if(x > 0) {
                if(mf[x-1][y].getIsMine()) {
                    counter++;
                }
                if(y < mf[0].length-1) {
                    if(mf[x-1][y+1].getIsMine()) {
                        counter++;
                    }
                }
            }
            if(y < mf[0].length-1) {
                if(mf[x][y+1].getIsMine()) {
                    counter++;
                }
                if(x < mf.length-1) {
                    if(mf[x+1][y+1].getIsMine()) {
                        counter++;
                    }
                }
            }
            if(x < mf.length-1) {
                if(mf[x+1][y].getIsMine()) {
                    counter++;
                }
                if(y > 0) {
                    if(mf[x+1][y-1].getIsMine()) {
                        counter++;
                    }
                }
            }
            if(y > 0) {
                if(mf[x][y-1].getIsMine()) {
                    counter++;
                }
                if(x > 0) {
                    if(mf[x-1][y-1].getIsMine()) {
                        counter++;
                    }
                }
            }
            return counter;
        }

    }
}
