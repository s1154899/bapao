import raspberry.RaspberryPi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class Songs extends JDialog{
    ArrayList<String> songNames = new ArrayList<>();
    test t;

    public Songs (JFrame frame, boolean modal){
        super(frame, modal);

        t = new test("Assest/","wwwooopps");
        setTitle("Songs");
        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(r.width, r.height);

        frame.setLayout(new GridLayout(1,2));

        JPanel songsPanel = new JPanel();
        songsPanel.setLayout(new GridLayout(songNames.size(), 1, 0, 20));

//        songNames.add("test 1");
//        songNames.add("test 2");
//        songNames.add("test 3");
//        songNames.add("test 4");
//        songNames.add("test 5");


        RaspberryPi pi = new RaspberryPi("192.168.2.7");
        String[] songs = pi.musicDirJava();





        setLayout(new BorderLayout());

        JPanel songsButtons = new JPanel();
        songsButtons.setPreferredSize(new Dimension( getWidth() / 3 - 20, songs.length * 100));
        JScrollPane scrollFrame = new JScrollPane(songsButtons);
        songsButtons.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( getWidth() / 3,300));
        add(scrollFrame, BorderLayout.LINE_START);


        GridLayout grid = new GridLayout(songs.length,1);

        songsButtons.setLayout(grid);



        for (String s : songs) {
            JButton songButton = new JButton();

//            songButton.setSize(frame.getWidth() / 2, 50 );
            songButton.setPreferredSize(new Dimension(20, 50));
            songButton.setText(s);
            songButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        pi.databaseCon.playmusic(s);
                        t.setTitle(s);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });

            songsButtons.add(songButton);

            add(t);

        }

//        for (String name: songNames){
//            JButton songName = new JButton(name);
//            songName.addActionListener(this);
//            songsPanel.add(songName);
//        }
//
//        add(songsPanel);

//        Playing playing = new Playing(frame, true);
//        add(playing,BorderLayout.EAST);

        setVisible(true);

    }
}

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        for (String name : songNames){
//            if (((JButton) e.getSource()).getText() == new JButton(name).getText()){
//                System.out.println(name);
//                ClickOnSong clickOnSong = new ClickOnSong(this, true, name);
//            }
//        }
//    }
//}
