import java.util.*;

public class MineField {

    private int numCols = 10;
    private int numRows = 10;

    private final int NUM_MINES = 10;

    private Mine[][] field;
    private int flagsPlaced = 0;

    private boolean lost = false;

    public MineField(int numRows, int numCols) {

        field = new Mine[numCols][numRows];

        this.numCols = numCols;
        this.numRows = numRows;

        for(int i=0; i<field.length; i++) {
            for(int j=0; j<field[0].length; j++) {
                field[i][j] = new Mine();
                field[i][j].setX(i);
                field[i][j].setY(j);
            }
        }
        List<Integer> currentMines = new ArrayList<>();
        for(int i=0; i<NUM_MINES; i++) {
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

    private boolean winCondition() {
        int count = 0;
        if(flagsPlaced == NUM_MINES) {
            for(int i=0; i<field.length; i++) {
                for(int j=0; j<field[0].length; j++) {
                    if(field[i][j].getIsMine()) {
                        if(field[i][j].getFlagged()) {
                            count++;
                        }
                    }
                }
            }
            if(count == NUM_MINES) {
                return true;
            }
        }
        return false;
    }


    public int getNumCols() {
        return numCols;
    }
    public int getNumRows() {
        return numRows;
    }


    private class Mine {
        private int x = 0;
        private int y = 0;
        private boolean isMine = false;
        private boolean explored = false;
        private boolean flagged = false;

        private void setX(int x) {
            this.x = x;
        }
        private void setY(int y) {
            this.y = y;
        }
        private void setIsMine(boolean isMine) {
            this.isMine = isMine;
        }
        private void setExplored(boolean explored) {
            this.explored = explored;
        }
        private void setFlagged(boolean flagged) {
            this.flagged = flagged;
        }

        private int getX() {
            return x;
        }
        private int getY() {
            return y;
        }
        private boolean getIsMine() {
            return isMine;
        }
        private boolean getExplored() {
            return explored;
        }
        private boolean getFlagged() {
            return flagged;
        }

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
