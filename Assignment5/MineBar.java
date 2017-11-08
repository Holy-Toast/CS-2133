import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class MineBar extends JMenuBar {
    private MSFrame frame;
    public MineBar(MSFrame frame) {
        super();
        this.frame = frame;
        MineMenu mm = new MineMenu("File");
        add(mm);
    }

    private class MineMenu extends JMenu {
        public MineMenu(String title) {
            super(title);
            MineMenuItem mmi1 = new MineMenuItem("New");
            mmi1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.getPanel().getField().newGame();
                }
            });
            MineMenuItem mmi2 = new MineMenuItem("Save");
            mmi2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fc = new JFileChooser();
                    int returnValue = fc.showSaveDialog(null);
                    if(returnValue == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        frame.getPanel().getField().saveGame(file);
                    }
                }
            });
            MineMenuItem mmi3 = new MineMenuItem("Load");
            mmi3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fc = new JFileChooser();
                    int returnValue = fc.showOpenDialog(null);
                    if(returnValue == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        frame.getPanel().getField().loadGame(file);
                    }
                }
            });
            MineMenuItem mmi4 = new MineMenuItem("Quit");
            mmi4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.getPanel().close();
                }
            });
            add(mmi1);
            add(mmi2);
            add(mmi3);
            add(mmi4);
        }
    }

    private class MineMenuItem extends JMenuItem {
        public MineMenuItem(String title) {
            super(title);
        }
    }
}
