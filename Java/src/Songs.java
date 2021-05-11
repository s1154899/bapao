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

        JPanel songsPanel = new JPanel();
        songsPanel.setLayout(new GridLayout(songNames.size(), 1));

        songNames.add("test 1");
        songNames.add("test 2");
        songNames.add("test 3");
        songNames.add("test 4");
        songNames.add("test 5");
        songNames.add("test 5");
        songNames.add("test 5");
        songNames.add("test 5");
        songNames.add("test 5");
        songNames.add("test 5");
        songNames.add("test 5");
        songNames.add("test 5");

        for (String name : songNames){
            JButton songName = new JButton(name);
            //songName.addActionListener((ActionListener) this);
            songsPanel.add(songName);
        }

        add(songsPanel);

        Playing playing = new Playing();
        add(playing,BorderLayout.EAST);

        setVisible(true);
    }
}