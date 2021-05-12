import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Songs extends JDialog implements ActionListener{
    ArrayList<String> songNames = new ArrayList<>();

    public Songs (JFrame frame, boolean modal){
        super(frame, modal);
        setTitle("Songs");
        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(r.width, r.height);

        JPanel songsPanel = new JPanel();
        songsPanel.setLayout(new GridLayout(songNames.size(), 1, 0, 20));

        songNames.add("test 1");
        songNames.add("test 2");
        songNames.add("test 3");
        songNames.add("test 4");
        songNames.add("test 5");

        for (String name: songNames){
            JButton songName = new JButton(name);
            songName.addActionListener(this);
            songsPanel.add(songName);
        }

        add(songsPanel);

//        JPanel dotsPanel = new JPanel();
//        dotsPanel.setLayout(new GridLayout(songNames.size(), 1));
//
//        for (String name: songNames){
//            JButton dots = new JButton("***");
//            dots.addActionListener(this);
//            dotsPanel.add(dots);
//        }
//
//        add(dotsPanel);

        Playing playing = new Playing();
        add(playing,BorderLayout.EAST);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (String name : songNames){
            if (((JButton) e.getSource()).getText() == new JButton(name).getText()){
                System.out.println(name);
                ClickOnSong clickOnSong = new ClickOnSong(this, true);

            }
        }
    }
}
