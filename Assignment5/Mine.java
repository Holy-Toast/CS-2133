import java.io.*;
/**
 *
 */
public class Mine implements Serializable {

    private static final long serialVersionUID = 347103844934234L;

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
    public Mine(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param isMine sets the mine to active or not
     */
    public void setIsMine(boolean isMine) {
        this.isMine = isMine;
    }

    /**
     * @param explored sets the mine to explored or unexplored
     */
    public void setExplored(boolean explored) {
        this.explored = explored;
    }

    /**
     * @param flagged Sets the mine to flagged or not
     */
    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    /**
     * @return The x location in the array
     */
    public int getX() {
        return x;
    }

    /**
     * @return The y location in the array.
     */
    public int getY() {
        return y;
    }

    /**
     * @return Whether or not the mine is active
     */
    public boolean getIsMine() {
        return isMine;
    }

    /**
     * @return Whether or not the mine has been explored
     */
    public boolean getExplored() {
        return explored;
    }

    /**
     * @return Whether or not the mine has been flagged
     */
    public boolean getFlagged() {
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
    public int surroundingMines(Mine[][] mf) {
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
