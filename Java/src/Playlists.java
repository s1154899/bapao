import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Playlists extends JDialog implements ActionListener {
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
            listName.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton event = (JButton) e.getSource();
                    System.out.println(event.getText());
                    for (String element : listNames) {
                        if (element == event.getText()) {
                            Playlist(event.getText());
                        }
                    }
                }
            });
            listPanel.add(listName);
        }



        Playing playing = new Playing();
        add(playing, BorderLayout.EAST);
        add(listPanel);

        setVisible(true);


    }
    public void Playlist(String playlist){

        // moet geselecteerde playlist afspelen


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}


