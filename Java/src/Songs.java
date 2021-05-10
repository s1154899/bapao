import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Songs extends JDialog {
    ArrayList<String> songNames = new ArrayList<>();


    public Songs (JFrame frame, boolean modal){
        super(frame, modal);
        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(r.width, r.height);
        setLayout(new BorderLayout());

        songNames.add("test 1");
        songNames.add("test 2");
        songNames.add("test 3");
        songNames.add("test 4");
        songNames.add("test 5");

        for (String name : songNames){
            JButton songName = new JButton(name);
            //songName.addActionListener((ActionListener) this);
            add(songName);

        }

//        JScrollPane scrollPane = new JScrollPane(new JTextArea(10, 20), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//        add(scrollPane);

        Playing playing = new Playing();
        add(playing,BorderLayout.EAST);

        setVisible(true);
    }
}
