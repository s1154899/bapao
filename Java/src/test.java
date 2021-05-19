//import java.io.IOException;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
//public class test {
//    public static void main(String args[]){
//
//<<<<<<< Updated upstream
//        try {
//            if (InetAddress.getByName("192.168.2.16").isReachable(50)) {
//                System.out.println("192.168.2.16" + " is reachable");
//
//            }
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//=======
//        RaspberryPi pi = new RaspberryPi("192.168.2.7");
//        String[] songs = pi.musicDirJava();
//
//        JFrame frame = new JFrame();
//
//        frame.setSize(500,500);
//
//        frame.setLayout(new BorderLayout());
//
//        JPanel test = new JPanel();
//        test.setPreferredSize(new Dimension( frame.getWidth() / 2 - 20, songs.length * 100));
//        JScrollPane scrollFrame = new JScrollPane(test);
//        test.setAutoscrolls(true);
//        scrollFrame.setPreferredSize(new Dimension( frame.getWidth() / 2,300));
//        frame.add(scrollFrame, BorderLayout.LINE_START);
//
//
//        GridLayout grid = new GridLayout(songs.length,1);
//
//        test.setLayout(grid);
//
//
//
//        for (String s : songs){
//            JButton songButton = new JButton();
//
////            songButton.setSize(frame.getWidth() / 2, 50 );
//            songButton.setPreferredSize(new Dimension(20, 50 ));
//            songButton.setText(s);
//            songButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    try {
//                        pi.databaseCon.playmusic(s);
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    }
//                }
//            });
//
//            test.add(songButton);
//>>>>>>> Stashed changes
//        }
//
//    }
//}


import raspberry.RaspberryPi;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class test extends JPanel {

    public JLabel imageLabel;
    public JLabel Title;



    public test(String imgsrc, String title){

        setLayout(new GridLayout(3,1));

        try {
            BufferedImage image = null;
            image = ImageIO.read(new File(imgsrc));
            imageLabel = new JLabel(new ImageIcon(image));
            add(imageLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Title = new JLabel();

        Title.setHorizontalAlignment(SwingConstants.CENTER);
        Title.setText(title);

        add(Title);

        JPanel PlayButtonsPanel = new JPanel();
        PlayButtonsPanel.setLayout(new FlowLayout());

        RoundButton back = new RoundButton();
        back.setText("<");
        back.addActionListener(new ActionListener() {
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
        RoundButton playPause = new RoundButton();
        playPause.setText("=");
        playPause.addActionListener(new ActionListener() {
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
        RoundButton next = new RoundButton();
        next.setText(">");
        next.addActionListener(new ActionListener() {
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

        PlayButtonsPanel.add(back);
        PlayButtonsPanel.add(playPause);
        PlayButtonsPanel.add(next);

        add(PlayButtonsPanel);


    }


    public void setTitle(String text){
        Title.setText(text);
        revalidate();
        repaint();
    }
    public void setImage(String text){
        try {
            BufferedImage image = null;
            image = ImageIO.read(new File(text));
            imageLabel = new JLabel(new ImageIcon(image));
            add(imageLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        JFrame f = new JFrame();
        f.setSize(500, 500);

//
//        test t = new test("Assets/AlbumCover1.png","wooops");
//        f.add(t);
//
//        t.setTitle("tester");

        JButton b = new JButton();
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser file = new JFileChooser();




                File f = new File("");
                int returnVal = file.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this file: " +
                            file.getSelectedFile().getName());
                            f = file.getSelectedFile();
                }



                try {
                    RaspberryPi.copyFileUsingStream(f);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
        f.add(b);

        f.setVisible(true);



    }

}