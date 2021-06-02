import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Playing extends JPanel {
    Main frame;
    //Main.colorEnum colorScheme;
    MusicMain musicMain;

    public Playing(Main frame, boolean modal, MusicMain musicMain){
        this.frame = frame;
        //this.colorScheme = Main.getColorScheme();
        this.musicMain = musicMain;

        setLayout(new GridBagLayout());
        //etBackground(colorScheme.getPrimaryColor());

        GridBagConstraints gcSecond = new GridBagConstraints();
        gcSecond.anchor = GridBagConstraints.CENTER;
        gcSecond.gridx = 1;
        gcSecond.gridy = 1;
        gcSecond.weightx = 1;
        gcSecond.weighty = 0.8f;
        gcSecond.gridwidth = 1;
        gcSecond.gridheight = 1;


        Header headPanel = new Header();
        headPanel.setMinimumSize(new Dimension(0, 128));

        headPanel.homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeThis();
            }
        });

        PlayingCenter playingCenter = new PlayingCenter(frame);
        add(playingCenter, gcSecond);
    }

    public void removeThis(){
        Main.mainFrame.remove(this);
        Main.mainFrame.returnHome();
        Main.mainFrame.revalidate();
        Main.mainFrame.repaint();
    }
}
