import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Playing extends JPanel {

    public Playing(){
        setPreferredSize(new Dimension(960, 1040));
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(280, 25, 400, 400);
        g.fillOval(280, 500, 120, 120);
        g.fillOval(420, 500, 120, 120);
        g.fillOval(560, 500, 120, 120);

        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif Bold", Font.BOLD, 20));
        g.drawString("Song title", 435, 450);
        g.drawString("<", 325, 565);
        g.drawString(">", 615, 565);
    }
}
