import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class test {
    public static void main(String args[]){

        try {
            if (InetAddress.getByName("192.168.2.16").isReachable(50)) {
                System.out.println("192.168.2.16" + " is reachable");

            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
