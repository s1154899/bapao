import javax.swing.*;

public class MusicFooter extends JPanel {

  //  Main frame;

    public MusicFooter(Main frame, boolean modal){
     //   this.frame = frame;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(null);

        Box[] boxes = new Box[4];

        for (int i = 0; i < boxes.length; i++){
            boxes[i] = Box.createHorizontalBox();
            add(boxes[i]);
        }


    }
}
