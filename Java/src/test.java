import raspberry.CheckHost;
import raspberry.SubnetCheck;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;


public class test extends JDialog {
    ArrayList<CheckHost> allThreads;

    public test(JFrame frame, boolean modal) {
        super(frame, modal);

        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(r.width, r.height);

        JPanel panel = new JPanel();

        JProgressBar checkingIp = new JProgressBar(0, 255 * 255);
        checkingIp.setPreferredSize(new Dimension(500,50));

        checkingIp.setValue(200);



        panel.add(checkingIp);
        add(panel);
        setVisible(true);
    }
    public static void main(String args[]){

        JFrame frame = new JFrame();
//        test t = new test(frame,true);

//        t.setSize(500,500);
//        t.setVisible(true);

        SubnetCheck subnetCheck = new SubnetCheck(frame,true);


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

