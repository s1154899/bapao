import javax.swing.*;
import java.awt.*;

public class PlayingCenter extends JPanel {
    Main frame;
    Main.colorEnum colorScheme;

    public PlayingCenter(Main frame){
        this.frame = frame;
        this.colorScheme = Main.getColorScheme();

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setOpaque(false);
        setBackground(null);

        Box[] boxes = new Box[3];
        for (int i = 0; i < boxes.length; i++){
            boxes[i] = Box.createVerticalBox();
            boxes[i].setMinimumSize(new Dimension(640, 1080));
            add(boxes[i]);
        }

        JLabel left = new JLabel();
        left.setPreferredSize(new Dimension(640,50));
        left.setMaximumSize(new Dimension(640,50));
        boxes[0].add(left);

        JPanel test = new JPanel();
        test.setPreferredSize(new Dimension(640,1080));
        test.setMaximumSize(new Dimension(640,1080));
        test.setBackground(Color.BLACK);
        boxes[1].add(test);

        JLabel right = new JLabel();
        right.setPreferredSize(new Dimension(640,50));
        right.setMaximumSize(new Dimension(640,50));
        boxes[2].add(right);
    }
}
