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

        Header headPanel = new Header();
        headPanel.setMinimumSize(new Dimension(0, 128));

        headPanel.homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeThis();
            }
        });
        add(headPanel, gc);


        PlayingCenter playingCenter = new PlayingCenter(frame);
        add(playingCenter, gcSecond);

        MusicFooter musicFooter = new MusicFooter();
        musicFooter.setMinimumSize(new Dimension(0,48));
        add(musicFooter, gcThird);
    }

    public void removeThis(){
        Main.mainFrame.remove(this);
        Main.mainFrame.returnHome();
        Main.mainFrame.revalidate();
        Main.mainFrame.repaint();
    }
}
