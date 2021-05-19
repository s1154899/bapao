import javax.swing.*;
import java.awt.*;

public class MusicMain extends JPanel {

    Main frame;
    Main.colorEnum colorScheme;


    public MusicMain(Main frame, boolean modal){
        this.frame = frame;
        this.colorScheme = Main.getColorScheme();
        setLayout(new GridBagLayout());
        setBackground(colorScheme.getPrimaryColor());

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.weightx = 1;
        gc.weighty = 0.3f;
        gc.gridy = 0;
        gc.gridwidth = 3;
        gc.gridheight = 1;

        GridBagConstraints gcSecond = new GridBagConstraints();
        gcSecond.anchor = GridBagConstraints.CENTER;
        gcSecond.gridx = 1;
        gcSecond.gridy = 1;
        gcSecond.weightx = 1;
        gcSecond.weighty = 0.8f;
        gcSecond.gridwidth = 1;
        gcSecond.gridheight = 1;

        GridBagConstraints gcThird = new GridBagConstraints();
        gcThird.fill = GridBagConstraints.BOTH;
        //gcSecond.anchor = GridBagConstraints.FIRST_LINE_START;
        gcThird.gridx = 0;
        gcThird.weightx = 1;
        gcThird.weighty = 0.3f;
        gcThird.gridy = 2;
        gcThird.gridwidth = 3;
        gcThird.gridheight = 1;

        Header headPanel = new Header(frame, this);
        headPanel.setPreferredSize(new Dimension(1920,10));
        headPanel.setMaximumSize(new Dimension(1920,10));
        add(headPanel, gc);

        JLabel center = new JLabel();
        add(center, gcSecond);

        MusicFooter musicFooter = new MusicFooter(frame, modal);
        musicFooter.setPreferredSize(new Dimension(1920,10));
        musicFooter.setMaximumSize(new Dimension(1920,10));
        add(musicFooter, gcThird);
        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(r.width,r.height);

        //layout for the main panel
        BorderLayout border = new BorderLayout();
        setLayout(border);

        //layout for the menu
        GridLayout grid = new GridLayout(4,2,40,60);
        JPanel menu = new JPanel();
        menu.setLayout(grid);

        String[] buttons = {"Songs", "Results", "Playlists", "Actions", "Edit_Playlist", "Sensors", "Playing" };

        for(String button : buttons) {
            //creates button for creating the senors page
            JButton button1 = new JButton();
            button1.setText(button);
            button1.addActionListener(Reflect.actionListenerFromMethod(this, button+"Page"));

            //adds sensors button to the menu
            menu.add(button1);
        }


        //adds the menu to the main screen
        add(menu, BorderLayout.CENTER);


    }
    public void PlaylistsPage(){

        Playlists playlists = new Playlists();
        add(playlists);
    }
}
