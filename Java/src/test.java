import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class test {
    public static void main(String args[]){

<<<<<<< Updated upstream
        try {
            if (InetAddress.getByName("192.168.2.16").isReachable(50)) {
                System.out.println("192.168.2.16" + " is reachable");

            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
=======
        RaspberryPi pi = new RaspberryPi("192.168.2.7");
        String[] songs = pi.musicDirJava();

        JFrame frame = new JFrame();

        frame.setSize(500,500);

        frame.setLayout(new BorderLayout());

        JPanel test = new JPanel();
        test.setPreferredSize(new Dimension( frame.getWidth() / 2 - 20, songs.length * 100));
        JScrollPane scrollFrame = new JScrollPane(test);
        test.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( frame.getWidth() / 2,300));
        frame.add(scrollFrame, BorderLayout.LINE_START);


        GridLayout grid = new GridLayout(songs.length,1);

        test.setLayout(grid);



        for (String s : songs){
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
>>>>>>> Stashed changes
        }

    }
}
