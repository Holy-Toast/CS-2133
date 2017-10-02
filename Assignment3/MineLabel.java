import javax.swing.*;

public class MineLabel extends JLabel {
    private int xPos;
    private int yPos;

    public MineLabel(String text ,int xPos, int yPos) {
        setText(text);
        setHorizontalAlignment(JLabel.CENTER);
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }
    public int getyPos() {
        return yPos;
    }
}
