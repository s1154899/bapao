package raspberry;

import raspberry.RaspberryPi;
import raspberry.SubnetCheck;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class CheckHost extends Thread {
    String subnet;

    public static ArrayList<String> openIps = new ArrayList<String>();

    public boolean done = false;

    public CheckHost(String subnet){
        this.subnet = subnet;
    }
    @Override
    public void run() {
        super.run();

        int timeout = 50;
        for (int i = 1; i <= 255; i++) {
            String host = subnet + "." + i;
            try {
                if (InetAddress.getByName(host).isReachable(timeout)) {
                    System.out.println(host + " is reachable");
//                    openIps.add(host);
                    new RaspberryPi(host);
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            SubnetCheck.addOneToProgress();
        }
        done = true;
    }
}
