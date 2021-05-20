import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Playlists extends JDialog implements ActionListener{
    ArrayList<String> listNames = new ArrayList<>();



    public Playlists(JFrame frame) {

        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(r.width, r.height);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(10, 1));

        listNames.add("plyalist");
        listNames.add(": cyka");
        listNames.add("blyat");


        JLabel playlists = new JLabel("Playlists");
        listPanel.add(playlists);

        for (String name : listNames) {
            JButton listName = new JButton(name);
            listName.addActionListener(this);
            listPanel.add(listName);
        }


        add(listPanel);

//        Playing playing = new Playing();
//        add(playing, BorderLayout.EAST);


        setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton event = (JButton) e.getSource();
        System.out.println(event.getText());

        // function also gets called when pressing on song-buttons
        for (String element : listNames) {
            if (element == event.getText()) {
                Playlist(event.getText());
            }
        }
        if("Terug" == event.getText()){
            // ga terug

        } else if ("Edit playlist" == event.getText()){
            EditPlaylist editPlaylist = new EditPlaylist();

        }

    }

    // needs parameters stringArray to pull songs out from, for now using strings
    public void Playlist(String playlist){
        System.out.println(playlist);

        getContentPane().removeAll(); //or remove(JComponent)
        revalidate();
        repaint();
        setTitle("Selected playlist: " + playlist);

        ArrayList<String> songNames = new ArrayList<>();

        JPanel songsPanel = new JPanel();
        songsPanel.setLayout(new GridLayout(songNames.size(), 1, 0, 20));


        songNames.add("test 1");
        songNames.add("test 2");
        songNames.add("test 3");
        songNames.add("test 4");
        songNames.add("test 5");


        for (String name: songNames){
            JLabel songName = new JLabel(name);
            songsPanel.add(songName);
        }
        JButton terug = new JButton("Terug");
        terug.addActionListener(this);
        songsPanel.add(terug);
        JButton edit = new JButton("Edit playlist");
        edit.addActionListener(this);
        songsPanel.add(edit);

        add(songsPanel);

//        Playing playing = new Playing();
//        add(playing,BorderLayout.EAST);
        setVisible(true);

    }
}