

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

        JSONArray jsonArray = Readplaylist();

        String[] array = new String[jsonArray.size()];
        for (int i = 0; i < jsonArray.size();i++){
            JSONObject obj2 = (JSONObject) jsonArray.get(i);
            array[i] = (String) obj2.get("name");
        }


        JPanel container = new JPanel();

        GridBagLayout grid = new GridBagLayout();

        container.setLayout(grid);
        //creates Constraint vairable for setting constraints on objects
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;

        JPanel playlistPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(playlistPanel);
//        scrollPane.setPreferredSize(new Dimension(400,600));


        playlistPanel.setAutoscrolls(true);
        playlistPanel.setBackground(Color.yellow);

        playlistPanel.setPreferredSize(new Dimension(100,array.length * 75));

        playlistPanel.setLayout(new BoxLayout(playlistPanel,BoxLayout.Y_AXIS));
        playlistPanel.setAutoscrolls(true);


        for (String name : array) {
            JButton but = new JButton(name);
            but.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));
            but.setSize(new Dimension(Integer.MAX_VALUE, 75));

            but.addActionListener(this);
            playlistPanel.add(but);
        }

        container.add(scrollPane,gbc);

        container.setPreferredSize(new Dimension(500,100));
        add(container, BorderLayout.LINE_START);



        //
//        JPanel listPanel = new JPanel();
//        listPanel.setLayout(new GridLayout(10, 1));
//
//
//        JLabel playlists = new JLabel("Playlists");
//        listPanel.add(playlists);
//
//
//        for (int i = 0; i < array.size(); i++){
//
//            JSONObject obj = (JSONObject) array.get(i);
//
//            JButton listName = new JButton(obj.get("name").toString());
//            listName.addActionListener(this);
//            listPanel.add(listName);
//
//        }

//        for (String name : listNames) {
//        }
//
//
//        add(listPanel);

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
        JSONArray jsonArray = Readplaylist();
        JSONArray songsArray = new JSONArray();
        for (int i =0; i < jsonArray.size();i++){
            JSONObject obj2 = (JSONObject) jsonArray.get(i);
            if (event.getText().equals(obj2.get("name"))){
                songsArray = (JSONArray) obj2.get("songs");
                System.out.println(obj2.toString());
            }

        }
        for(RaspberryPi pi : RaspberryPi.connectedPis){
            for (int i = 0; i < songsArray.size();i++) {
                try {
                    pi.databaseCon.playmusic(songsArray.get(i).toString());
                    System.out.println(songsArray.get(i).toString());
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