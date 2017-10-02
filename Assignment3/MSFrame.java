import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Patrick
 */
public class MSFrame extends JFrame {

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

    private class MSPanel extends JPanel {

        public static final int COLUMNS = 10; //X
        public static final int ROWS = 10;    //Y

        private MineField field;
        private MineLabel[][] labels;

        private MSPanel() {
            setLayout(new GridLayout(ROWS, COLUMNS));
            field = new MineField(ROWS, COLUMNS);
            labels = new MineLabel[ROWS][COLUMNS];

            for(int i=0; i<field.getNumCols(); i++) {
                for(int j=0; j<field.getNumRows(); j++) {
                    MineLabel newMine = new MineLabel("?", i, j);
                    newMine.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if(e.getButton() == MouseEvent.BUTTON3) {
                                field.flagMine(newMine);
                            }
                            else {
                                field.stepMine(newMine, labels);
                            }
                            field.setMineText(labels);
                        }
                    });
                    add(newMine);
                    labels[i][j] = newMine;
                }
            }
        }
    }
}
