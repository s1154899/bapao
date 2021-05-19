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


        RaspberryPi pi = new RaspberryPi("192.168.2.7");
        String[] songs = pi.musicDirJava();

        JFrame frame = new JFrame();

        frame.setSize(500,500);

        frame.setLayout(new BorderLayout());

        JPanel test = new JPanel();
        test.setPreferredSize(new Dimension( frame.getWidth() / 2 - 20, songs.length * 100));
        JScrollPane scrollFrame = new JScrollPane(test);
        test.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( frame.getWidth() / 2,300));
        frame.add(scrollFrame, BorderLayout.LINE_START);


        GridLayout grid = new GridLayout(songs.length,1);

        test.setLayout(grid);



        for (String s : songs){
            JButton songButton = new JButton();

//            songButton.setSize(frame.getWidth() / 2, 50 );
            songButton.setPreferredSize(new Dimension(20, 50 ));
            songButton.setText(s);
            songButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        pi.databaseCon.playmusic(s);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });




        for (String name: songNames){
            JButton songName = new JButton(name);
            songName.addActionListener(this);
            songsPanel.add(songName);
        }

        add(songsPanel);

//        Playing playing = new Playing(frame, true);
//        add(playing,BorderLayout.EAST);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (String name : songNames){
            if (((JButton) e.getSource()).getText() == new JButton(name).getText()){
                System.out.println(name);
                ClickOnSong clickOnSong = new ClickOnSong(this, true, name);
            }
        }
    }
}
