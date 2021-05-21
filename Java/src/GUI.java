import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    private JButton songs;
    private JButton results;
    private JButton playlists;
    private JButton actions;
    private JButton editPlaylists;
    private JButton sensors;
    private JButton playing;

    public GUI(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5,2));
        setSize(1920, 1020);
        setTitle("Bapao domotica-systeem");

        add(new JLabel("Muziek"));

        add(new JLabel("Sensoren"));

        songs = new JButton("Songs");
        songs.addActionListener(this);
        add(songs);

        results = new JButton("Results");
        results.addActionListener(this);
        add(results);

        playlists = new JButton("Playlists");
        playlists.addActionListener(this);
        add(playlists);

        actions = new JButton("Actions");
        actions.addActionListener(this);
        add(actions);

        editPlaylists = new JButton("Edit playlists");
        editPlaylists.addActionListener(this);
        add(editPlaylists);

        sensors = new JButton("Sensors");
        sensors.addActionListener(this);
        add(sensors);

        playing = new JButton("Playing");
        playing.addActionListener(this);
        add(playing);


        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
