

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import raspberry.RaspberryPi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Playlists extends JDialog implements ActionListener{
    ArrayList<String> listNames = new ArrayList<>();


    public static JSONArray Readplaylist()  {
        FileReader reader = null;
        try {
            reader = new FileReader("./playlist.json");
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);

            return jsonArray;

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return new JSONArray();
    }

    private JSONArray array;

    public Playlists() {
        super(Main.mainFrame,true);
        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(r.width, r.height);

        array = Readplaylist();

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(10, 1));


        JLabel playlists = new JLabel("Playlists");
        listPanel.add(playlists);


        for (int i = 0; i < array.size(); i++){

            JSONObject obj = (JSONObject) array.get(i);

            JButton listName = new JButton(obj.get("name").toString());
            listName.addActionListener(this);
            listPanel.add(listName);

        }

        for (String name : listNames) {
        }


        add(listPanel);

        MusicMain musicMain = new MusicMain();
        Playing playing = new Playing(Main.mainFrame, true, musicMain);
        add(playing, BorderLayout.EAST);


        setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton event = (JButton) e.getSource();
        System.out.println(event.getText());
        this.setTitle("now playing"+ event.getText());
        JSONArray songsArray = new JSONArray();
        for (int i =0; i < array.size();i++){
            JSONObject obj2 = (JSONObject) array.get(i);
            if (event.getText().equals(obj2.get("name"))){
                songsArray = (JSONArray) obj2.get("songs");
            }

        }
        for(RaspberryPi pi : RaspberryPi.connectedPis){
            for (int i = 0; i < songsArray.size();i++) {
                try {
                    pi.databaseCon.playmusic(songsArray.get(i).toString());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
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