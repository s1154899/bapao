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

public class EditPlaylist extends JDialog {

    public static playlistJPanel playlist;
    public static editCreateJPanel edit;
    public static EditPlaylist editPlaylist;

    public static ArrayList<String> songsArray;



   public EditPlaylist(){
       super(Main.mainFrame,true);
       setLayout(new GridLayout(1,2));

       Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
       setSize(r.width,r.height);

        editPlaylist = this;


       songsArray = new ArrayList<>();



       playlist = new playlistJPanel(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               JButton button = (JButton) e.getSource();

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
               redrawEditCreate(button.getText());
           }
       }, new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               JButton button = (JButton) e.getSource();
               redrawEditCreate("");
               songsArray = new ArrayList<>();


           }
       });
       add(playlist);

       edit = new editCreateJPanel("");
       add(edit);

       setVisible(true);

   }

   public void redrawEditCreate(String oldName){
       this.remove(edit);
       edit = new editCreateJPanel(oldName);
       add(edit);
       repaint();
       revalidate();


   }

   public void redrawPlaylist(String OldName){
       this.remove(playlist);
       this.remove(edit);
       playlist = new playlistJPanel(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               JButton button = (JButton) e.getSource();

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
               redrawEditCreate(button.getText());
           }
       }, new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               JButton button = (JButton) e.getSource();
               redrawEditCreate("");
               songsArray = new ArrayList<>();


           }
       });
       add(playlist);

       edit = new editCreateJPanel(OldName);
       add(edit);
       repaint();
       revalidate();
   }



}

class playlistJPanel extends JPanel {
    public playlistJPanel(ActionListener actionListener, ActionListener newPlaylist) {
        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(r.width / 6,r.height);
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        JButton createPlaylist = new JButton("create new playlist");
        createPlaylist.setSize(this.getWidth(),100);
        createPlaylist.addActionListener(newPlaylist);
        add(createPlaylist);


        JSONArray jsonArray = SavedPlaylist.Readplaylist();

        for (int i =0; i < jsonArray.size();i++){
            JSONObject obj2 = (JSONObject) jsonArray.get(i);
            JButton but = new JButton((String) obj2.get("name"));
            but.setSize(this.getWidth(),100);
            but.addActionListener(actionListener);
            add(but);

        }



    }


}


class editCreateJPanel extends JPanel{

    String OldName;
    JPanel middel;
    JPanel SongsSelection;
    JPanel InPlaylist;

    public editCreateJPanel(String OldName){
        this.OldName = OldName;

        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(r.width / 6,r.height);
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        JPanel top = new JPanel();
        top.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JLabel label = new JLabel("edit name:");
        label.setPreferredSize(new Dimension(200,100));
        label.setSize(new Dimension(200,100));
        top.add(label);

        JTextField nameField = new JTextField(OldName);
//        nameField.setPreferredSize(new Dimension(300,100));
        nameField.setSize(300,50);
        nameField.setMaximumSize(new Dimension(300, 50));
        nameField.setPreferredSize(new Dimension(300, 50));
        top.add(nameField);



        add(top);

        middel = new JPanel();

        SongsSelection = SongList(RaspberryPi.musicDirJava(), "+", "add to playlist", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                EditPlaylist.songsArray.add(button.getText());
                redrawMiddel();
            }
        });
        middel.add(SongsSelection);

        InPlaylist = SongList(EditPlaylist.songsArray.toArray(new String[0]), "-", "in playlist", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                EditPlaylist.songsArray.remove(button.getText());

                redrawMiddel();
            }
        });
        middel.add(InPlaylist);

        add(middel);

        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout());

        JButton save = new JButton("save");
        save.setSize(200,100);
        save.setMaximumSize(new Dimension(200,100));
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (OldName.equals("")){
                    SavedPlaylist.addNewScript(nameField.getText(), EditPlaylist.songsArray.toArray(new String[0]));
                    EditPlaylist.editPlaylist.redrawPlaylist(nameField.getText());

                }else{
                    SavedPlaylist.changePlaylist(OldName,nameField.getText(),EditPlaylist.songsArray.toArray(new String[0]));
                    EditPlaylist.editPlaylist.redrawPlaylist(nameField.getText());
                }
            }
        });
        bottom.add(save);
        JButton remove = new JButton("remove");
        remove.setSize(200,100);
        remove.setMaximumSize(new Dimension(200,100));
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SavedPlaylist.removePlaylist(OldName);
                EditPlaylist.editPlaylist.redrawPlaylist("");
            }
        });
        bottom.add(remove);
        add(bottom);

    }

    public JPanel SongList(String[] songs, String atEnd , String title, ActionListener buttonsAction){
        JPanel list = new JPanel();
        list.setLayout(new BoxLayout(list,BoxLayout.Y_AXIS));
        list.add(new JLabel(title));


        JPanel songsButtons = new JPanel();
        songsButtons.setPreferredSize(new Dimension( getWidth() / 3 - 20, songs.length * 100));
        JScrollPane scrollFrame = new JScrollPane(songsButtons);
        songsButtons.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( getWidth() / 3,300));
        list.add(scrollFrame, BorderLayout.LINE_START);


        songsButtons.setLayout(new BoxLayout(songsButtons,BoxLayout.Y_AXIS));

        for (String s: songs) {
            JButton button = new JButton(s);
            button.addActionListener(buttonsAction);
            songsButtons.add(button);
        }



    return list;
    }

    public void redrawMiddel(){
        middel.remove(SongsSelection);
        middel.remove(InPlaylist);
        SongsSelection = SongList(RaspberryPi.musicDirJava(), "+", "add to playlist", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                EditPlaylist.songsArray.add(button.getText());
                redrawMiddel();
            }
        });
        middel.add(SongsSelection);

        InPlaylist = SongList(EditPlaylist.songsArray.toArray(new String[0]), "-", "in playlist", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                EditPlaylist.songsArray.remove(button.getText());
                redrawMiddel();
            }
        });
        middel.add(InPlaylist);
        repaint();
        revalidate();
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



}
