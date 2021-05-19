import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Playlists extends JDialog {
    ArrayList<String> listNames = new ArrayList<>();


    public Playlists(JFrame frame, boolean modal) {
        super(frame, modal);
        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(r.width, r.height);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(listNames.size(), 1));

        listNames.add("test 1");
        listNames.add("test 2");
        listNames.add("test 3");
        listNames.add("test 4");
        listNames.add("test 5");
        listNames.add("test 5");


        for (String name : listNames) {
            JButton listName = new JButton(name);
            //listName.addActionListener((ActionListener) this);
            listPanel.add(listName);
        }

        add(listPanel);

//        Playing playing = new Playing();
//        add(playing, BorderLayout.EAST);

        setVisible(true);


    }
}
