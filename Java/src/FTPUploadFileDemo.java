
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * A program that demonstrates how to upload files from local computer
 * to a remote FTP server using Apache Commons Net API.
 *
 * @author www.codejava.net
 */
public class FTPUploadFileDemo {

    private static final int BUFFER_SIZE = 4096;

    public static void main(String[] args) {
        String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
        String host = "192.168.2.4";
        String user = "pi";
        String pass = "raspberry";
        String filePath = "C:/Users/edmar/Downloads/5758.jpg";
        String uploadPath = "/files/pic.jpg";

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
