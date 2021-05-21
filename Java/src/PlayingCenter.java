import raspberry.RaspberryPi;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlayingCenter extends JPanel {
    Main frame;
    //Main.colorEnum colorScheme;
    private Font usedFont;

    JButton previousButton;
    JButton playPauseButton;
    JButton nextButton;

    ImageIcon previousIcon;
    ImageIcon playPauseIcon;
    ImageIcon nextIcon;
    Image albumCover1;

    public PlayingCenter(Main frame) {

        try {
            usedFont = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Assets/Comfort.ttf"));
        } catch (IOException |FontFormatException e) {
            e.printStackTrace();
        }

        this.frame = frame;
        //this.colorScheme = Main.getColorScheme();

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setOpaque(false);
        setBackground(null);

        Box[] boxes = new Box[3];
        for (int i = 0; i < boxes.length; i++) {
            boxes[i] = Box.createVerticalBox();
        }
        boxes[0].setMinimumSize(new Dimension(750, 1080));
        boxes[0].setMaximumSize(new Dimension(750, 1080));
        add(boxes[0]);

        boxes[1].setMinimumSize(new Dimension(420,1080));
        boxes[1].setMaximumSize(new Dimension(420,1080));
        add(boxes[1]);

        boxes[2].setMinimumSize(new Dimension(750, 1080));
        boxes[2].setMaximumSize(new Dimension(750, 1080));
        add(boxes[2]);

        JLabel left = new JLabel();
        //JLabel left = new JLabel();
        boxes[0].add(left);

        JPanel playing = new JPanel();
        playing.setBackground(null);
        playing.setLayout(new GridBagLayout());
        playing.setPreferredSize(new Dimension(500,850));
        playing.setMaximumSize(new Dimension(500,850));

        GridBagConstraints gc = new GridBagConstraints();
        //gc.anchor = GridBagConstraints.CENTER;
        //gc.fill = GridBagConstraints.HORIZONTAL;

        gc.gridx = 0;
        gc.gridy = 3;
        gc.weightx = 1;
        gc.weighty = 0.4f;
        gc.gridwidth = 3;
        gc.gridheight = 1;
        gc.anchor = GridBagConstraints.CENTER;
        playing.setBackground(Color.cyan);
//        playing.setPreferredSize(new Dimension(640, 922));
//        playing.setMaximumSize(new Dimension(640, 922));
//        playing.setMinimumSize(new Dimension(640, 922));

        GridBagConstraints gc2 = new GridBagConstraints();
        //gc.anchor = GridBagConstraints.CENTER;
        //gc.fill = GridBagConstraints.HORIZONTAL;

        gc2.gridx = 1;
        gc2.gridy = 4;
        gc2.weightx = 1;
        gc2.weighty = 0.8f;
        gc2.gridwidth = 1;
        gc2.gridheight = 1;
        gc2.anchor = GridBagConstraints.CENTER;

        GridBagConstraints gc3 = new GridBagConstraints();

        gc3.gridx = 0;
        gc3.gridy = 1;
        gc3.weightx = 3;
        gc3.weighty = 0.4f;
        gc3.gridwidth = 3;
        gc3.gridheight = 3;
        gc3.anchor = GridBagConstraints.CENTER;

        playing.setOpaque(false);

//        JPanel albumCover = new JPanel();
//        //albumCover.setBackground(colorScheme.getSecondaryColor());
//        albumCover.setOpaque(false);
//
//        Image albumCoverImage = null;
//        try {
//            InputStream albumCoverImg = Login.class.getResourceAsStream("Assets/AlbumCover1.png");
//            albumCoverImage = ImageIO.read(albumCoverImg);
//        } catch (Exception e) {
//
//        }
////
////        JLabel label = new JLabel(new ImageIcon(albumCoverImage));
////        //label.setMaximumSize(new Dimension(640,));
////        albumCover.add(label);
//
//        playing.add(albumCover);

//        try{
//            InputStream imageSource = Login.class.getResourceAsStream("Assets/AlbumCover1.png");
//            Image albumCover1 = ImageIO.read(imageSource);
//        } catch (Exception e){
//
//        }

        try {
            InputStream albumCoverImg = Login.class.getResourceAsStream("AlbumCover1.png");
            albumCover1 = ImageIO.read(albumCoverImg);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageIcon albumCover1Icon = new ImageIcon(albumCover1);

        JLabel label = new JLabel(albumCover1Icon);
        playing.add(label, gc3);


        JLabel songTitle = new JLabel("Song title - Artist", SwingConstants.CENTER);
        songTitle.setFont(usedFont.deriveFont(20f));
        //songTitle.setForeground(colorScheme.getDetailColor());
        playing.add(songTitle, gc);

        previousButton = new JButton();
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<RaspberryPi> pis = RaspberryPi.connectedPis;
                for(int i = 0; i < pis.size() ;i++){
                    try {
                        pis.get(i).databaseCon.backMusic();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });

        try{
            InputStream imageSource = Login.class.getResourceAsStream("Assets/rewind.png");
            Image previousImage = ImageIO.read(imageSource);
            previousIcon = new ImageIcon(previousImage);
        } catch (Exception e){

        }

        previousButton.setIcon(previousIcon);
        previousButton.setPressedIcon(previousIcon);
        previousButton.setBorder(new EmptyBorder(0,10,0,10));
        previousButton.setBackground(Color.RED);
        previousButton.setFocusable(false);
        previousButton.setContentAreaFilled(false);
        previousButton.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                previousButton.setBackground(null);
            }

            @Override
            public void focusLost(FocusEvent e) {
                previousButton.setBackground(null);
            }
        });



        playPauseButton = new JButton();
        playPauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<RaspberryPi> pis = RaspberryPi.connectedPis;
                for(int i = 0; i < pis.size() ;i++){
                    try {
                        pis.get(i).databaseCon.pauseMusic();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });

        try{
            InputStream imageSource = Login.class.getResourceAsStream("Assets/playMusic.png");
            Image playPauseImage = ImageIO.read(imageSource);
            playPauseIcon = new ImageIcon(playPauseImage);
        } catch (Exception e){

        }

        playPauseButton.setIcon(playPauseIcon);
        playPauseButton.setPressedIcon(playPauseIcon);
        playPauseButton.setBorder(new EmptyBorder(0,0,0,0));
        playPauseButton.setBackground(Color.RED);
        playPauseButton.setFocusable(false);
        playPauseButton.setContentAreaFilled(false);
        playPauseButton.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                playPauseButton.setBackground(null);
            }

            @Override
            public void focusLost(FocusEvent e) {
                playPauseButton.setBackground(null);
            }
        });



        nextButton = new JButton();
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<RaspberryPi> pis = RaspberryPi.connectedPis;
                for(int i = 0; i < pis.size() ;i++){
                    try {
                        pis.get(i).databaseCon.nextMusic();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });

        try{
            InputStream imageSource = Login.class.getResourceAsStream("Assets/skip.png");
            Image nextImage = ImageIO.read(imageSource);
            nextIcon = new ImageIcon(nextImage);
        } catch (Exception e){

        }

        nextButton.setIcon(nextIcon);
        nextButton.setPressedIcon(nextIcon);
        nextButton.setBorder(new EmptyBorder(0,10,0,10));
        nextButton.setBackground(Color.RED);
        nextButton.setFocusable(false);
        nextButton.setContentAreaFilled(false);
        nextButton.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                nextButton.setBackground(null);
            }

            @Override
            public void focusLost(FocusEvent e) {
                nextButton.setBackground(null);
            }
        });

        gc2.gridx = 0;
        playing.add(previousButton, gc2);

        gc2.gridy = 4;
        gc2.gridx = 1;
        gc2.gridwidth = 1;
        playing.add(playPauseButton, gc2);
        gc2.gridy = 4;
        gc2.gridx = 2;
        gc2.gridwidth = 1;
        playing.add(nextButton, gc2);

        boxes[1].add(playing);

        JLabel right = new JLabel();
        //JLabel right = new JLabel();
        boxes[2].add(right);
    }

    protected void paintComponent(Graphics g){
//        super.paintComponent(g);
//
//        Image albumCoverImage = null;
//        try {
//            InputStream albumCoverImg = Login.class.getResourceAsStream("Assets/AlbumCover1.png");
//            albumCoverImage = ImageIO.read(albumCoverImg);
//
//            GradientPaint grad = new GradientPaint(0,0, Color.BLACK, 0,1080, Color.BLACK);
//            Graphics2D g2d1 = (Graphics2D) g;
//            g2d1.setPaint(grad);
//            g2d1.fill(new Rectangle2D.Double(0,0, 1920 , 1080));
//
//        } catch (Exception e) {
//
//        }
//
//        Graphics2D g2d = (Graphics2D)g;
//        g2d.drawImage(albumCoverImage, 750,20,420,420, this);
//
//        Graphics2D g2d1 = (Graphics2D) g;
//        g2d1.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2d1.setColor(colorScheme.getSecondaryColor());
//        g2d1.fillOval(Math.round(755f* (frame.getWidth() / 1920f)),Math.round(500f* (frame.getHeight() / 1080f)), 130, 130);
//        g2d1.fillOval(Math.round(895f* (frame.getWidth() / 1920f)),Math.round(500f* (frame.getHeight() / 1080f)), 130, 130);
//        g2d1.fillOval(Math.round(1035f* (frame.getWidth() / 1920f)),Math.round(500f* (frame.getHeight() / 1080f)), 130, 130);
//
//        //g2d1.setColor(colorScheme.getBorderColor());
//        g2d1.drawOval(Math.round(755f* (frame.getWidth() / 1920f)), Math.round(500f* (frame.getHeight() / 1080f)), 130, 130);
//        g2d1.drawOval(Math.round(895f* (frame.getWidth() / 1920f)), Math.round(500f* (frame.getHeight() / 1080f)), 130, 130);
//        g2d1.drawOval(Math.round(1035f* (frame.getWidth() / 1920f)), Math.round(500f* (frame.getHeight() / 1080f)), 130, 130);
//
//        g2d1.drawLine(960, 0, 960, 1080);
//        repaint();
    }
}
