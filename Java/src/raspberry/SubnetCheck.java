package raspberry;


import raspberry.RaspberryPi;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;


public class SubnetCheck extends JDialog {
    ArrayList<CheckHost> allThreads;

    private static JProgressBar checkingIp = new JProgressBar(0, 255 * 254);

    public SubnetCheck(JFrame frame, boolean modal){
        super(frame, modal);

        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(r.width, r.height);

        JPanel panel = new JPanel();

        JLabel label = new JLabel();



        checkingIp.setPreferredSize(new Dimension(500,50));


        checkingIp.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                StringBuilder builder = new StringBuilder();
                for(RaspberryPi pi : RaspberryPi.connectedPis){
                    builder.append(pi.getHost() + "\n");
                }
                if (checkingIp.getValue() >= checkingIp.getMaximum()){
                    dispose();
                    for(RaspberryPi pi : RaspberryPi.connectedPis){
                        try {
                            pi.databaseCon.viewTable();
                        } catch (SQLException throwables) {
//                            throwables.printStackTrace();
                        }
                    }
                }
                label.setText(builder.toString());

            }
        });



        panel.add(label);
        panel.add(checkingIp);
        add(panel);



        allThreads = new ArrayList<CheckHost>();

        for (int i = 0; 255 >= i;i++){
            CheckHost hosts = new CheckHost("192.168." + i);
            hosts.start();
            allThreads.add(hosts);

        }
        setVisible(true);
//        while(!checkThreads()){
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println("all done");
//
//        ArrayList<String> openIps = CheckHost.openIps;

//
//        for (int i = 0; openIps.size() > i; i++){
//
//            System.out.println(openIps.get(i));
//            new DatabaseCon(openIps.get(i));
//
//        }




    }


    public boolean checkThreads(){
        boolean bool = true;
        for (int i = 0; allThreads.size() > i; i++){
            if (!allThreads.get(i).done){
                bool = false;
                break;
            }

        }
        return bool;
    }

public static void addOneToProgress(){
        checkingIp.setValue(checkingIp.getValue() + 1);

}


}
