import raspberry.CheckHost;
import raspberry.SubnetCheck;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import raspberry.RaspberryPi;


public class test extends JDialog {
    ArrayList<CheckHost> allThreads;

    public test(JFrame frame, boolean modal) {
        super(frame, modal);

        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(r.width, r.height);

        JPanel panel = new JPanel();

        JProgressBar checkingIp = new JProgressBar(0, 255 * 255);
        checkingIp.setPreferredSize(new Dimension(500,50));

//        checkingIp.setValue(200);



        panel.add(checkingIp);
        add(panel);
        setVisible(true);
    }
    public static void main(String args[]){

        JFrame frame = new JFrame();

        frame.setSize(500,500);

        frame.setLayout(new BorderLayout());

        JPanel test = new JPanel();
        test.setPreferredSize(new Dimension( frame.getWidth() / 2,2000));
        JScrollPane scrollFrame = new JScrollPane(test);
        test.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( frame.getWidth() / 2,300));
        frame.add(scrollFrame, BorderLayout.LINE_START);


        GridLayout grid = new GridLayout(10,1);

        test.setLayout(grid);

        RaspberryPi pi = new RaspberryPi("192.168.2.7");


        for (String s : pi.musicDirJava()){
            JButton songButton = new JButton();

//            songButton.setSize(frame.getWidth() / 2, 50 );
            songButton.setPreferredSize(new Dimension(20, 50 ));
            songButton.setText(s);
            songButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        pi.databaseCon.playmusic(s);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });

            test.add(songButton);
        }

        frame.setVisible(true);

//        test t = new test(frame,true);

//        t.setSize(500,500);
//        t.setVisible(true);

        //SubnetCheck subnetCheck = new SubnetCheck(frame,true);


//        try {
//            if (InetAddress.getByName("192.168.2.16").isReachable(50)) {
//                System.out.println("192.168.2.16" + " is reachable");
//
//            }
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}

