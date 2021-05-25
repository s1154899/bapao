

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicFooter extends JPanel {

    public MusicFooter(){


        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setOpaque(false);
        setBackground(null);

        Box[] boxes = new Box[4];

        for (int i = 0; i < boxes.length; i++){
            boxes[i] = Box.createHorizontalBox();
            add(boxes[i]);
        }

        JButton songs = new JButton("Songs");
//        songs.setFont(getUsedFont().deriveFont(20f));
        songs.setBackground(Main.colorScheme.getSecondBackgroundColor());
        songs.setPreferredSize(new Dimension(640, 50));
        songs.setMaximumSize(new Dimension(640, 50));
        songs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Songs songs = new Songs(Main.mainFrame,true);
            }
        });
        boxes[0].add(songs);

        JButton playlists = new JButton("Playlists");
//        playlists.setFont(getUsedFont().deriveFont(20f));
        playlists.setBackground(Main.colorScheme.getSecondBackgroundColor());
        playlists.setPreferredSize(new Dimension(640, 50));
        playlists.setMaximumSize(new Dimension(640, 50));
        playlists.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Playlists playlists = new Playlists();
            }
        });
        boxes[1].add(playlists);

        JButton editPlaylist = new JButton("Edit playlist");
//        editPlaylist.setFont(getUsedFont().deriveFont(20f));
        editPlaylist.setBackground(Main.colorScheme.getSecondBackgroundColor());
        editPlaylist.setPreferredSize(new Dimension(640, 50));
        editPlaylist.setMaximumSize(new Dimension(640, 50));
        editPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditPlaylist play = new EditPlaylist();
                play.setVisible(true);
            }
        });
        boxes[2].add(editPlaylist);

        JButton playing = new JButton("Playing");
//        playing.setFont(getUsedFont().deriveFont(20f));
        playing.setBackground(Main.colorScheme.getSecondBackgroundColor());
        playing.setPreferredSize(new Dimension(640, 50));
        playing.setMaximumSize(new Dimension(640, 50));
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
        boxes[3].add(playing);
    }



}
