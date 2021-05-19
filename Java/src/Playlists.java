import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Playlists extends JDialog {
    ArrayList<String> listNames = new ArrayList<>();


    public Playlists() {

        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(r.width, r.height);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(listNames.size(), 1));

        listNames.add("plyalist");
        listNames.add(": cyka");
        listNames.add("blyat");


        for (String name : listNames) {
            JButton listName = new JButton(name);

           // listName.addActionListener((ActionListener) this);
            listPanel.add(listName);
        }

        add(listPanel);

        Playing playing = new Playing();
        add(playing, BorderLayout.EAST);


        setVisible(true);


    }
}
