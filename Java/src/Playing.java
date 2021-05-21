
import java.awt.*;
import javax.swing.*;

public class Playing extends JPanel {
    Main frame;
    Main.colorEnum colorScheme;
    MusicMain musicMain;

    public Playing(Main frame, boolean modal, MusicMain musicMain){
        this.frame = frame;
        this.colorScheme = Main.getColorScheme();
        this.musicMain = musicMain;

        setLayout(new GridBagLayout());
        setBackground(colorScheme.getPrimaryColor());

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

        Header headPanel = new Header(frame, this);
        headPanel.setPreferredSize(new Dimension(1920,128));
        headPanel.setMaximumSize(new Dimension(1920,128));
        add(headPanel, gc);

//        PlayingCenter playingCenter = new PlayingCenter(frame);
        test t = new test("Assets/AlbumCover1.png","wooops");
        add(t, gcSecond);

        MusicFooter musicFooter = new MusicFooter(frame, musicMain);
        musicFooter.setPreferredSize(new Dimension(1920,30));
        musicFooter.setMaximumSize(new Dimension(1920,30));
        add(musicFooter, gcThird);
    }

//    protected void paintComponent(Graphics g){
//        super.paintComponent(g);
//        g.setColor(Color.BLUE);
//        g.fillRect(280, 25, 400, 400);
//        g.fillOval(280, 500, 120, 120);
//        g.fillOval(420, 500, 120, 120);
//        g.fillOval(560, 500, 120, 120);
//
//        g.setColor(Color.BLACK);
//        g.setFont(new Font("SansSerif Bold", Font.BOLD, 20));
//        g.drawString("Song title", 435, 450);
//        g.drawString("<", 325, 565);
//        g.drawString(">", 615, 565);
//    }
}
