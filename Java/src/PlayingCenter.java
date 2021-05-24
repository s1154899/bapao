import raspberry.RaspberryPi;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.File;
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

    Image currentAlbumCover;

    ArrayList<String> albumCovers = new ArrayList<>();
    ArrayList<String> songTitles = new ArrayList<>();
    int currentSongNumber = 1;
    String[] songTitle;

    public PlayingCenter(Main frame) {
        albumCovers.add("Assets/AlbumCovers/AlbumCover1.png");
        albumCovers.add("Assets/AlbumCovers/AlbumCover2.png");
        albumCovers.add("Assets/AlbumCovers/AlbumCover3.png");
        albumCovers.add("Assets/AlbumCovers/AlbumCover4.png");

//        songTitles.add("Song title 1");
//        songTitles.add("Song title 2");
//        songTitles.add("Song title 3");
//        songTitles.add("Song title 4");

        songTitle = RaspberryPi.musicDirJava();
        System.out.println(songTitle.length);
        for(String song : songTitle){
            System.out.println(song);
            songTitles.add(song);
        }

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

        JPanel playing = new JPanel();
        playing.setBackground(null);
        playing.setLayout(new GridBagLayout());
        playing.setPreferredSize(new Dimension(500,850));
        playing.setMaximumSize(new Dimension(500,850));

        GridBagConstraints gc = new GridBagConstraints();

        gc.gridx = 0;
        gc.gridy = 4;
        gc.weightx = 1;
        gc.weighty = 0.4f;
        gc.gridwidth = 3;
        gc.gridheight = 1;
        gc.anchor = GridBagConstraints.CENTER;

        GridBagConstraints gc2 = new GridBagConstraints();

        gc2.gridx = 1;
        gc2.gridy = 5;
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

        try {
            InputStream albumCoverImg = Login.class.getResourceAsStream(albumCovers.get(currentSongNumber - 1));
            currentAlbumCover = ImageIO.read(albumCoverImg);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel albumCoverLabel = new JLabel();
        Image test = currentAlbumCover.getScaledInstance(420, 420, Image.SCALE_SMOOTH);
        ImageIcon albumCoverIcon = new ImageIcon(test);
        albumCoverLabel.setIcon(albumCoverIcon);
        playing.add(albumCoverLabel, gc3);


        JLabel songTitle = new JLabel(songTitles.get(currentSongNumber - 1), SwingConstants.CENTER);
        songTitle.setFont(usedFont.deriveFont(20f));
        //songTitle.setForeground(colorScheme.getDetailColor());
        playing.add(songTitle, gc);

        previousButton = new JButton();
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentSongNumber == 1){
                    currentSongNumber = songTitles.size();
                } else {
                    currentSongNumber--;
                }
                ArrayList<RaspberryPi> pis = RaspberryPi.connectedPis;
                for(int i = 0; i < pis.size() ;i++){
                    try {
                        pis.get(i).databaseCon.backMusic();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                songTitle.setText(songTitles.get(currentSongNumber - 1));

                try {
                    InputStream albumCoverImg = Login.class.getResourceAsStream(albumCovers.get(currentSongNumber - 1));
                    currentAlbumCover = ImageIO.read(albumCoverImg);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }

                Image test = currentAlbumCover.getScaledInstance(420, 420, Image.SCALE_SMOOTH);
                ImageIcon albumCoverIcon = new ImageIcon(test);
                albumCoverLabel.setIcon(albumCoverIcon);

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
                if(currentSongNumber == songTitles.size()){
                    currentSongNumber = 1;
                } else {
                    currentSongNumber++;
                }

                ArrayList<RaspberryPi> pis = RaspberryPi.connectedPis;
                for(int i = 0; i < pis.size() ;i++){
                    try {
                        pis.get(i).databaseCon.nextMusic();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }

                songTitle.setText(songTitles.get(currentSongNumber - 1));

                try {
                    InputStream albumCoverImg = Login.class.getResourceAsStream(albumCovers.get(currentSongNumber - 1));
                    currentAlbumCover = ImageIO.read(albumCoverImg);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }

                Image test = currentAlbumCover.getScaledInstance(420, 420, Image.SCALE_SMOOTH);
                ImageIcon albumCoverIcon = new ImageIcon(test);
                albumCoverLabel.setIcon(albumCoverIcon);
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

        gc2.gridx = 1;
        gc2.gridwidth = 1;
        playing.add(playPauseButton, gc2);

        gc2.gridx = 2;
        gc2.gridwidth = 1;
        playing.add(nextButton, gc2);

        boxes[1].add(playing);
    }
}
