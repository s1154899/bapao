import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Playing extends JPanel {

    public Playing(){
        setPreferredSize(new Dimension(960, 1040));
        setBackground(Color.WHITE);
        //JButton previous = new JButton("<");
        //previous.setBounds(800, 670, 120, 120);
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(25, 25, 400, 400);
        g.fillOval(25, 500, 120, 120);
        g.fillOval(165, 500, 120, 120);
        g.fillOval(305, 500, 120, 120);

        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif Bold", Font.BOLD, 20));
        g.drawString("Song title", 180, 450);
        g.drawString("<", 70, 565);
        g.drawString(">", 360, 565);
    }
}
