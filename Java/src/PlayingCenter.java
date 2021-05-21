import raspberry.RaspberryPi;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.Rectangle2D;
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

        JLabel left = new JLabel("left", SwingConstants.CENTER);
        //JLabel left = new JLabel();
        boxes[0].add(left);

        JPanel playing = new JPanel();
        playing.setBackground(null);
//        playing.setLayout(new GridLayout(3, 1));
//        playing.setPreferredSize(new Dimension(640, 922));
//        playing.setMaximumSize(new Dimension(640, 922));
//        playing.setMinimumSize(new Dimension(640, 922));
        playing.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        playing.setOpaque(false);

        Box[] boxesCenter = new Box[3];

        playing.add(boxesCenter[0]);
        playing.add(boxesCenter[1]);
        playing.add(boxesCenter[2]);

        Image albumCoverImage = null;
        try {
            InputStream albumCoverImg = Login.class.getResourceAsStream("Assets/AlbumCover1.png");
            albumCoverImage = ImageIO.read(albumCoverImg);
        } catch (Exception e) {

        }

        JLabel label = new JLabel(new ImageIcon(albumCoverImage));
        boxesCenter[0].add(label);


//        JPanel albumCover = new JPanel();
//        //albumCover.setBackground(colorScheme.getSecondaryColor());
//        albumCover.setOpaque(false);
//
////
////        JLabel label = new JLabel(new ImageIcon(albumCoverImage));
////        //label.setMaximumSize(new Dimension(640,));
////        albumCover.add(label);
//
//        playing.add(albumCover);

//        JLabel test = new JLabel("test1");
//        playing.add(test);

        JLabel songTitle = new JLabel("Song title - Artist", SwingConstants.CENTER);
        songTitle.setFont(usedFont.deriveFont(20f));
        //songTitle.setForeground(colorScheme.getDetailColor());
        boxesCenter[1].add(songTitle);

//        previousButton = new JButton();
//        previousButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                ArrayList<RaspberryPi> pis = RaspberryPi.connectedPis;
//                for(int i = 0; i < pis.size() ;i++){
//                    try {
//                        pis.get(i).databaseCon.backMusic();
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    }
//                }
//            }
//        });
//
//        try{
//            InputStream imageSource = Login.class.getResourceAsStream("Assets/left-arrow.png");
//            Image previousImage = ImageIO.read(imageSource);
//            previousIcon = new ImageIcon(previousImage);
//        } catch (Exception e){
//
//        }
//
//        previousButton.setIcon(previousIcon);
//        previousButton.setPressedIcon(previousIcon);
//        previousButton.setBorder(new EmptyBorder(30,0,0,Math.round(200f*(frame.getWidth()/1920f))));
//        previousButton.setBackground(Color.RED);
//        previousButton.setFocusable(false);
//        previousButton.setContentAreaFilled(false);
//        previousButton.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                previousButton.setBackground(null);
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                previousButton.setBackground(null);
//            }
//        });
//
//
//
//        playPauseButton = new JButton();
//        playPauseButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                ArrayList<RaspberryPi> pis = RaspberryPi.connectedPis;
//                for(int i = 0; i < pis.size() ;i++){
//                    try {
//                        pis.get(i).databaseCon.pauseMusic();
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    }
//                }
//            }
//        });
//
//        try{
//            InputStream imageSource = Login.class.getResourceAsStream("Assets/playMusic.png");
//            Image playPauseImage = ImageIO.read(imageSource);
//            playPauseIcon = new ImageIcon(playPauseImage);
//        } catch (Exception e){
//
//        }
//
//        playPauseButton.setIcon(playPauseIcon);
//        playPauseButton.setPressedIcon(playPauseIcon);
//        playPauseButton.setBorder(new EmptyBorder(30,0,0,Math.round(200f*(frame.getWidth()/1920f))));
//        playPauseButton.setBackground(Color.RED);
//        playPauseButton.setFocusable(false);
//        playPauseButton.setContentAreaFilled(false);
//        playPauseButton.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                playPauseButton.setBackground(null);
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                playPauseButton.setBackground(null);
//            }
//        });
//
//
//
//        nextButton = new JButton();
//        nextButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                ArrayList<RaspberryPi> pis = RaspberryPi.connectedPis;
//                for(int i = 0; i < pis.size() ;i++){
//                    try {
//                        pis.get(i).databaseCon.nextMusic();
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    }
//                }
//            }
//        });
//
//        try{
//            InputStream imageSource = Login.class.getResourceAsStream("Assets/Next.png");
//            Image nextImage = ImageIO.read(imageSource);
//            nextIcon = new ImageIcon(nextImage);
//        } catch (Exception e){
//
//        }
//
//        nextButton.setIcon(nextIcon);
//        nextButton.setPressedIcon(nextIcon);
//        nextButton.setBorder(new EmptyBorder(30,0,0,Math.round(200f*(frame.getWidth()/1920f))));
//        nextButton.setBackground(Color.RED);
//        nextButton.setFocusable(false);
//        nextButton.setContentAreaFilled(false);
//        nextButton.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                nextButton.setBackground(null);
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                nextButton.setBackground(null);
//            }
//        });
//
//
//
//
//
//
//        playing.add(previousButton);
//        playing.add(playPauseButton);
//        playing.add(nextButton);
//
//        boxes[1].add(playing);

        boxesCenter[2].add(new JLabel("test boxesCenter[2]"));

        JLabel right = new JLabel("right", SwingConstants.CENTER);
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
//            //GradientPaint grad = new GradientPaint(0,0,colorScheme.getFirstBackgroundColor(), 0,1080,colorScheme.getSecondBackgroundColor());
//            Graphics2D g2d1 = (Graphics2D) g;
//            //g2d1.setPaint(grad);
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
//        //g2d1.setColor(colorScheme.getSecondaryColor());
//        g2d1.fillOval(Math.round(750f* (frame.getWidth() / 1920f)),Math.round(500f* (frame.getHeight() / 1080f)), 130, 130);
//        g2d1.fillOval(Math.round(890f* (frame.getWidth() / 1920f)),Math.round(500f* (frame.getHeight() / 1080f)), 130, 130);
//        g2d1.fillOval(Math.round(1030f* (frame.getWidth() / 1920f)),Math.round(500f* (frame.getHeight() / 1080f)), 130, 130);
//
//        //g2d1.setColor(colorScheme.getBorderColor());
//        g2d1.drawOval(Math.round(750f* (frame.getWidth() / 1920f)), Math.round(500f* (frame.getHeight() / 1080f)), 130, 130);
//        g2d1.drawOval(Math.round(890f* (frame.getWidth() / 1920f)), Math.round(500f* (frame.getHeight() / 1080f)), 130, 130);
//        g2d1.drawOval(Math.round(1030f* (frame.getWidth() / 1920f)), Math.round(500f* (frame.getHeight() / 1080f)), 130, 130);
//        repaint();
    }
}
