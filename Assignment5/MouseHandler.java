import java.awt.event.*;

/**
 * @author Patrick
 */
public class MouseHandler extends MouseAdapter {

    /**
     *
     */
    private int x;
    private int y;

    /**
     *
     */
    private MSPanel panel;

    /**
     *
     * @param x
     * @param y
     * @param panel
     */
    public MouseHandler(int x, int y, MSPanel panel) {
        this.x = x;
        this.y = y;
        this.panel = panel;
    }

    /**
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3) {
            panel.getField().flagMine(x, y);
        }
        else {
            panel.getField().stepMine(x, y);
        }
        panel.setTheThings();
    }
}
