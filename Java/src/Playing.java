
import java.awt.*;
import javax.swing.*;

public class Playing extends JPanel {
  //  public static String songTitle;

    public Playing(){
        setPreferredSize(new Dimension(960, 1040));
        JButton forward = new JButton(">");
        JButton back = new JButton("<");
        super.add(forward);
        super.add(back);
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
        g.drawString("songTitle", 435, 450);
        g.drawString("<", 325, 565);
        g.drawString(">", 615, 565);
    }
}
