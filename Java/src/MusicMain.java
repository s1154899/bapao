import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicMain extends JPanel implements ActionListener {

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

        JLabel center = new JLabel("test");
        add(center, gcSecond);


        MusicFooter musicFooter = new MusicFooter(frame, modal, this);
        musicFooter.setPreferredSize(new Dimension(1920,10));
        musicFooter.setMaximumSize(new Dimension(1920,10));
        add(musicFooter, gcThird);
    }

    public void removeThis(){
        frame.remove(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (((JButton) e.getSource()).getText() == "Playing"){
            frame.PlayingPage();
            removeThis();
        } else if (((JButton) e.getSource()).getText() == "Songs"){
            frame.SongsPage();
            removeThis();
        } else if (((JButton) e.getSource()).getText() == "Playists"){
            frame.PlaylistsPage();
            removeThis();
        } else if (((JButton) e.getSource()).getText() == "Edit playlist"){
            frame.Edit_PlaylistPage();
            removeThis();
        }
    }
}
