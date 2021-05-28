

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicMain extends JPanel {

    Header header;

    public MusicMain(){

        setLayout(new GridBagLayout());
        setBackground(Main.colorScheme.getPrimaryColor());

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

        header = new Header();
        header.setPreferredSize(new Dimension(1920,128));
        header.setMaximumSize(new Dimension(1920,128));

        header.setMinimumSize(new Dimension(0, 128));


        add(header, gc);

        header.homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeThis();
            }
        });




        JPanel center = new JPanel();
        center.setBackground(null);
        center.setBorder(new EmptyBorder(0,0,0,0));
        center.setOpaque(false);
        //center.setPreferredSize(new Dimension(500,300));
        center.setMinimumSize(new Dimension(1400,255));
        center.setLayout(new GridLayout(1,4));

        RoundButton songs = new RoundButton(250,250,Main.colorScheme.getSecondaryColor(), Main.colorScheme.getDetailColor(), "Assets/Icons/songs.png");
        songs.setBorder(new EmptyBorder(0,150,0,150));
        songs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Songs songs = new Songs(Main.mainFrame,true);
            }
        });

        RoundButton playlist = new RoundButton(250,250,Main.colorScheme.getSecondaryColor(), Main.colorScheme.getDetailColor(), "Assets/Icons/playlists.png");
        playlist.setBorder(new EmptyBorder(0,150,0,150));
        playlist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Playlists playlists = new Playlists();
            }
        });

        RoundButton editPlaylist = new RoundButton(250,250,Main.colorScheme.getSecondaryColor(), Main.colorScheme.getDetailColor(), "Assets/Icons/editPlaylist.png");
        editPlaylist.setBorder(new EmptyBorder(0,150,0,150));
        editPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditPlaylist play = new EditPlaylist();
                play.setVisible(true);
            }
        });

        RoundButton playing = new RoundButton(250,250,Main.colorScheme.getSecondaryColor(), Main.colorScheme.getDetailColor(), "Assets/Icons/playing.png");
        playing.setBorder(new EmptyBorder(0,150,0,150));
        playing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(Main.mainFrame,true);

                Playing playing = new Playing();
                dialog.add(playing);

                Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
                dialog.setSize(r.width,r.height);

                dialog.setVisible(true);
            }
        });

        center.add(songs);
        center.add(playlist);
        center.add(editPlaylist);
        center.add(playing);


        add(center, gcSecond);

        JPanel musicFooter = new JPanel();
        musicFooter.setPreferredSize(new Dimension(1920,30));
        musicFooter.setMaximumSize(new Dimension(1920,30));
        musicFooter.setMinimumSize(new Dimension(0, 30));
        add(musicFooter, gcThird);
    }

    public void removeThis(){
        Main.mainFrame.remove(this);
        Main.mainFrame.returnHome();
        Main.mainFrame.revalidate();
        Main.mainFrame.repaint();
    }

    public void changeColor(){
        setBackground(Main.colorScheme.getPrimaryColor());
        setForeground(Main.colorScheme.getDetailColor());

        for (Component component : getComponents()){
            component.setForeground(Main.colorScheme.getDetailColor());
            if (component instanceof MusicFooter){
                for (Component component1 : ((MusicFooter) component).getComponents()){
                    Box box = (Box) component1;
                    JButton button = (JButton) box.getComponent(0);
                    button.setForeground(Main.colorScheme.getDetailColor());
                    button.setBorder(new LineBorder(Main.colorScheme.getBorderColor()));
                    button.setBackground(Main.colorScheme.getSecondaryColor());
                }
            }
        }
    }


}
