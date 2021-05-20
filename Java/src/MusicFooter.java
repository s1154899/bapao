import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicFooter extends JPanel {
    Main frame;
    Main.colorEnum colorScheme;
    MusicMain musicMain;

    public MusicFooter(Main frame, MusicMain musicMain){
        this.frame = frame;
        this.musicMain = musicMain;
        this.colorScheme = Main.getColorScheme();

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setOpaque(false);
        setBackground(null);

        Box[] boxes = new Box[4];

        for (int i = 0; i < boxes.length; i++){
            boxes[i] = Box.createHorizontalBox();
            add(boxes[i]);
        }

        JButton songs = new JButton("Songs");
        songs.setFont(frame.getUsedFont().deriveFont(20f));
        songs.setBackground(colorScheme.getSecondBackgroundColor());
        songs.setPreferredSize(new Dimension(640, 50));
        songs.setMaximumSize(new Dimension(640, 50));
        songs.addActionListener(musicMain);
        boxes[0].add(songs);

        JButton playlists = new JButton("Playlists");
        playlists.setFont(frame.getUsedFont().deriveFont(20f));
        playlists.setBackground(colorScheme.getSecondBackgroundColor());
        playlists.setPreferredSize(new Dimension(640, 50));
        playlists.setMaximumSize(new Dimension(640, 50));
        playlists.addActionListener(musicMain);
        boxes[1].add(playlists);

        JButton editPlaylist = new JButton("Edit playlist");
        editPlaylist.setFont(frame.getUsedFont().deriveFont(20f));
        editPlaylist.setBackground(colorScheme.getSecondBackgroundColor());
        editPlaylist.setPreferredSize(new Dimension(640, 50));
        editPlaylist.setMaximumSize(new Dimension(640, 50));
        editPlaylist.addActionListener(musicMain);
        boxes[2].add(editPlaylist);

        JButton playing = new JButton("Playing");
        playing.setFont(frame.getUsedFont().deriveFont(20f));
        playing.setBackground(colorScheme.getSecondBackgroundColor());
        playing.setPreferredSize(new Dimension(640, 50));
        playing.setMaximumSize(new Dimension(640, 50));
        playing.addActionListener(musicMain);
        boxes[3].add(playing);
    }
}
