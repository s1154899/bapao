

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickOnSong extends JDialog implements ActionListener {
    private JButton addToPlaylist;
    private JButton play;
    private JButton cancel;
    private String songName;

    public ClickOnSong(Songs frame, boolean modal, String songName){
        super(frame, modal);
        this.songName = songName;
        setSize(300, 150);
        setLayout(new FlowLayout());

        addToPlaylist = new JButton("Add song to playlist");
        addToPlaylist.addActionListener(this);
        add(addToPlaylist);

        play = new JButton("Play song");
        play.addActionListener(this);
        add(play);

        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addToPlaylist){
            // moet nog kunnen worden toegevoegd aan playlists!!
            // variabele 'songName' kan hiervoor gebruikt worden
            dispose();
        } else if (e.getSource() == play){
            // liedje moet nog kunnen worden afgespeeld!!
            // variabele 'songName' kan hiervoor gebruikt worden
            dispose();
        } else if (e.getSource() == cancel){
            dispose();
        }
    }
}
