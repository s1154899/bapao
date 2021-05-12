import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickOnSong extends JDialog implements ActionListener {
    private JButton addToPlaylist;
    private JButton play;
    private JButton cancel;

    public ClickOnSong(Songs frame, boolean modal){
        super(frame, modal);
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
            dispose();
        } else if (e.getSource() == play){
            //liedje moet nog kunnen worden afgespeeld!!
            dispose();
        } else if (e.getSource() == cancel){
            dispose();
        }
    }
}
