

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MusicMain extends JPanel implements ActionListener{

    Header header;

    public MusicMain(){

    public MusicMain(Main frame, boolean modal) {
        this.frame = frame;
        this.colorScheme = Main.getColorScheme();
        setLayout(new GridBagLayout());
        setBackground(ColorScheme.getPrimaryColor());

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.weightx = 1;
        gc.weighty = 0.001f;
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
        gcThird.weighty = 0.001f;
        gcThird.gridy = 2;
        gcThird.gridwidth = 3;
        gcThird.gridheight = 1;


        // header related
        Header headPanel = new Header(frame, this);
        headPanel.setPreferredSize(new Dimension(1920, 10));
        headPanel.setMaximumSize(new Dimension(1920, 10));
        add(headPanel, gc);

        // menu
        JPanel menu = new JPanel();
        String[] buttons = {"Songs", "Playlists", "Edit_Playlist", "Playing"};
        for(String button : buttons) {
            //creates buttons for every item in arrayList
            JButton button1 = new JButton(button);
            button1.addActionListener(Reflect.actionListenerFromMethod(this, button+"Page"));


            //adds button to menu
            add(button1);
        }




        // footer
        MusicFooter musicFooter = new MusicFooter(frame, modal);
        musicFooter.setPreferredSize(new Dimension(1920, 10));
        musicFooter.setMaximumSize(new Dimension(1920, 10));
        add(musicFooter, gcThird);

    }


    //function called when playlists button is pressed
    public void PlaylistsPage(){
        Playlists playlists = new Playlists(new JFrame());
        add(playlists);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    public void removeThis(){
        Main.mainFrame.remove(this);
        Main.mainFrame.returnHome();
        Main.mainFrame.revalidate();
        Main.mainFrame.repaint();
    }


}


