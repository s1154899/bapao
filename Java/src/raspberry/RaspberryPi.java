package raspberry;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class RaspberryPi {

    private static final int BUFFER_SIZE = 4096;
    private String host = "192.168.2.4";
    private String user = "pi";
    private String pass = "raspberry";
    private String filePath = "C:/Users/edmar/Downloads/5758.jpg";
    private String uploadPath = "/files/pic.jpg";

    public static void main(String[] args) {
        RaspberryPi r = new RaspberryPi();
        r.UploadMusic("test.mp3");
    }


    public void UploadMusic(String name){
        upload("C:/Users/edmar/Downloads/test.mp3","/music/"+name);
    }
    //vraag me niet hoe dit werkt
    private void upload(String filePath, String uploadPath){
//
        String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
//        String host = "192.168.2.4";
//        String user = "pi";
//        String pass = "raspberry";

        ftpUrl = String.format(ftpUrl, user, pass, host, uploadPath);
        System.out.println("Upload URL: " + ftpUrl);

        try {
            URL url = new URL(ftpUrl);
            URLConnection conn = url.openConnection();
            OutputStream outputStream = conn.getOutputStream();
            FileInputStream inputStream = new FileInputStream(filePath);

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outputStream.close();

            System.out.println("File uploaded");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
