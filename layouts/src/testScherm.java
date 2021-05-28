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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class testScherm extends JFrame{

    public static void main(String[] args){
        new testScherm();

    }

    public testScherm(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Domotica: home screen");
        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(r.width,r.height);
        add(new edit_playlist());
        setVisible(true);
    }


}

class edit_playlist extends JPanel{
    private JPanel playlist;
    private JPanel addSongsList;
    private JPanel removeSongsList;
    private ArrayList<String> songsArray;
    private JTextField playlistName;
    private String OldName;

    public edit_playlist(){
        OldName = "";
        //creates grid layout
        GridBagLayout grid = new GridBagLayout();

        setLayout(grid);
        //creates Constraint vairable for setting constraints on objects
        GridBagConstraints gbc = new GridBagConstraints();

        songsArray = new ArrayList<>();

        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;

        drawList();

        gbc.gridheight =1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(new JLabel("edit name: "),gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.ipady = 50;
        playlistName = new JTextField();
        playlistName.setPreferredSize(new Dimension(300,200));
        playlistName.setMaximumSize(new Dimension(300,200));
        add(playlistName,gbc);


        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.ipady = 0;


        add(new JLabel("add new songs to playlist: "),gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;

        add(new JLabel("remove songs from playlist: "),gbc);


        JButton save = new JButton("save");

        save.addActionListener(SavePlaylist());

        save.setBackground(Color.GREEN);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.ipady = 50;
        gbc.ipadx = 100;
        gbc.insets = new Insets(0,0,0,30);
        add(save,gbc);

        JButton remove = new JButton("remove");

        remove.addActionListener(removePlaylist());

        remove.setBackground(Color.RED);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.insets = new Insets(0,30,0,0);


        add(remove,gbc);










    }

    private void drawList(){

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 5;

        playlist = ListWithButtons(SavedPlaylist.returnPlaylistArray("name"), loadPlaylist());
        add( playlist, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridheight =1;
        gbc.ipady = 600;

        addSongsList = ListWithButtons(RaspberryPi.musicDirJava(), addToSongArray(),50);
        add(addSongsList,gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;

        removeSongsList = ListWithButtons(songsArray.toArray(new String[0]), RemoveSongfromSongArray(),50);
        add(removeSongsList,gbc);

    }


    private JPanel ListWithButtons(String[] buttonNames, ActionListener ButtonAction){
        return ListWithButtons(buttonNames,ButtonAction,75);
    }
    private JPanel ListWithButtons(String[] buttonNames, ActionListener ButtonAction, int ButtonHight){
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

        playlistPanel.setPreferredSize(new Dimension(100,buttonNames.length * 75));

        playlistPanel.setLayout(new BoxLayout(playlistPanel,BoxLayout.Y_AXIS));
        playlistPanel.setAutoscrolls(true);


        for (String name : buttonNames) {
            JButton but = new JButton(name);
            but.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));
            but.setSize(new Dimension(Integer.MAX_VALUE, 75));

            but.addActionListener(ButtonAction);
            playlistPanel.add(but);
        }

        container.add(scrollPane,gbc);
        return container;
    }


    private void removeAndRedraw(){

        remove(playlist);
        remove(removeSongsList);
        remove(addSongsList);

        drawList();
        repaint();
        revalidate();


    }

    private ActionListener addToSongArray(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button =(JButton) e.getSource();
                songsArray.add(button.getText());
                removeAndRedraw();

            }
        };
    }

    private ActionListener RemoveSongfromSongArray(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button =(JButton) e.getSource();
                songsArray.remove(button.getText());
                removeAndRedraw();

            }
        };
    }

    private ActionListener SavePlaylist(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (OldName.equals("")){
                    SavedPlaylist.addNewScript(playlistName.getText(), songsArray.toArray(new String[0]));
                    removeAndRedraw();

                }else{
                    SavedPlaylist.changePlaylist(OldName,playlistName.getText(),songsArray.toArray(new String[0]));
                    removeAndRedraw();
                }
            }
        };

    }


    private ActionListener removePlaylist(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SavedPlaylist.removePlaylist(OldName);
                removeAndRedraw();
            }
        };
    }

    private ActionListener loadPlaylist(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if(!button.getText().equals("new playlist")) {
                    OldName = button.getText();
                    playlistName.setText(button.getText());
                    songsArray = new ArrayList<>();

                    JSONArray jsonArray = SavedPlaylist.Readplaylist();

                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject obj2 = (JSONObject) jsonArray.get(i);
                        if (button.getText().equals(obj2.get("name"))) {
                            JSONArray array = (JSONArray) obj2.get("songs");
                            for (int j = 0; j < array.size(); j++) {
                                songsArray.add((String) array.get(j));

                            }

                        }

                    }
                }else{
                    OldName = "";
                    playlistName.setText("");
                    songsArray = new ArrayList<>();
                }
                removeAndRedraw();
            }
        };
    }

}


class SavedPlaylist {

    public static void main(String[] args){
        addNewScript("test1", new String[]{"w", "s", "a", "a"});
        addNewScript("test1", new String[]{"w", "s", "a", "a"});
        addNewScript("test1", new String[]{"w", "s", "a", "a"});
        addNewScript("test1", new String[]{"w", "s", "a", "a"});


    }



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

    public static void changePlaylist(String playlistName, String newplaylistName, String[] songs){
        JSONArray jsonArray = Readplaylist();

        JSONObject obj = new JSONObject();
        obj.put("name",newplaylistName);

        JSONArray array = new JSONArray();
        for (String s : songs){
            array.add(s);
        }
        obj.put("songs",array);

        for (int i =0; i < jsonArray.size();i++){
            JSONObject obj2 = (JSONObject) jsonArray.get(i);
            if (playlistName.equals(obj2.get("name"))){
                jsonArray.set(i,obj);
            }

        }
        try {
            Files.writeString(Path.of("./playlist.json"), jsonArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addNewScript(String newplaylistName, String[] songs){
        JSONArray jsonArray = Readplaylist();
        JSONObject obj = new JSONObject();
        obj.put("name",newplaylistName);

        JSONArray array = new JSONArray();
        for (String s : songs){
            array.add(s);
        }
        obj.put("songs",array);

        jsonArray.add(obj);

        try {
            Files.writeString(Path.of("./playlist.json"), jsonArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removePlaylist(String playlistName){
        JSONArray jsonArray = Readplaylist();


        for (int i =0; i < jsonArray.size();i++){
            JSONObject obj2 = (JSONObject) jsonArray.get(i);
            if (playlistName.equals(obj2.get("name"))){
                jsonArray.remove(i);
            }

        }
        try {
            Files.writeString(Path.of("./playlist.json"), jsonArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] returnPlaylistArray(String name){
        JSONArray jsonArray = SavedPlaylist.Readplaylist();

        String[] array = new String[jsonArray.size() + 1];
        array[0] = "new playlist";
        for (int i = 0; i < jsonArray.size();i++){
            JSONObject obj2 = (JSONObject) jsonArray.get(i);
            array[i+1] = (String) obj2.get(name);
        }
        return array;
    }



}