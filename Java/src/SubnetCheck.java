
import java.util.ArrayList;


public class SubnetCheck extends Thread {
    ArrayList<CheckHost> allThreads;

    public SubnetCheck(){

        allThreads = new ArrayList<CheckHost>();

        for (int i = 0; 255 > i;i++){
            CheckHost hosts = new CheckHost("192.168." + i);
            hosts.start();
            allThreads.add(hosts);

        }

        while(!checkThreads()){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("all done");

        ArrayList<String> openIps = CheckHost.openIps;


        for (int i = 0; openIps.size() > i; i++){

            System.out.println(openIps.get(i));
            new DatabaseCon(openIps.get(i));

        }




    }

    public static void main(String args[]){
        new SubnetCheck();




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




}
