package raspberry;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Stream;


public class RaspberryPi {

    public static ArrayList<RaspberryPi> connectedPis = new ArrayList<>();
    private static final int BUFFER_SIZE = 4096;
    private String host = "192.168.2.4";
    private String user = "pi";
    private String pass = "raspberry";
    private String filePath = "C:/Users/edmar/Downloads/5758.jpg";
    private String uploadPath = "/files/pic.jpg";

    public DatabaseCon databaseCon;

    public static void main(String[] args) throws SQLException {

        RaspberryPi pi = new RaspberryPi("192.168.2.7");
//        pi.databaseCon.playmusic("mii channel but all the pauses are uncomfortably long.mp3");
//        pi.databaseCon.playmusic("Rick Astley - Never Gonna Give You Up (Video).mp3");
//        pi.databaseCon.playmusic("test.mp3");


    }

    public RaspberryPi(String Host){
        host = Host;
        try {
            databaseCon = new DatabaseCon(host);
            connectedPis.add(this);
            sync();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void sync(){

        String[] strings = musicDirJava();
        String[] strings1 = DirRaspberry("listMusic.py");
        for (String s : strings){
            boolean found = false;

            for(String s1 : strings1){

                if (s.equals(s1)){
                    found = true;
                }
            }

            if (!found){
                System.out.println("not found: " + s);
                UploadMusic(s);
            }


        }

        strings = scriptsDirJava();
        strings1 = DirRaspberry("listScripts.py");

        for (String s : strings){
            boolean found = false;

            for(String s1 : strings1){

                if (s.equals(s1)){
                    found = true;
                }
            }

            if (!found){
                System.out.println("not found: " + s);
                UploadScripts(s);
            }


        }

    }

    public void UploadMusic(String name){
        upload("./music/"+name,"/music/"+name);
    }
    public void UploadScripts(String name){ upload("./scripts/"+name, "/scripts/"+name);}
    //vraag me niet hoe dit werkt
    private void upload(String filePath, String uploadPath){

        String ftpUrl = "ftp://%s:%s@%s/%s;type=i";


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


    public String[] musicDirJava(){
        return DirJava("music");

    }

    public String[] scriptsDirJava(){
        return DirJava("scripts");
    }

    private String[] DirJava(String location){

        try (Stream<Path> paths = Files.walk(Paths.get("./"+location+"/"))) {

            Object[] p1 = paths.skip(1).toArray();
            String[] files = new String[p1.length];
            int count = 0;
            for(Object i :p1){
                files[count] = i.toString().replaceAll("\\\\","").replaceFirst("."+location,"").trim();
                count++;
            }
            return files;
            //          .filter(Files::isRegularFile)
            //         .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[1];
    }


    public String[] DirRaspberry(String page){

        try {
            var url = new URL("http://"+host+"/cgi-bin/" + page);
            var br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;

            var sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }


            String s = sb.toString().replaceAll("[\\[\\](){}]","").replaceAll("'","");
            String[] files = s.split(",");
            for (int i = 0; i < files.length ; i++){
               files[i] = files[i].trim();
            }
            return files;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[1];
    }

    public String getHost() {
        return host;
    }

    public static void copyFileUsingStream(File source) throws IOException {
        File dest = new File( "./scripts/"+source.getName());
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            ArrayList<RaspberryPi> pi = RaspberryPi.connectedPis;
            for (int i = 0; i < pi.size() ; i++){
                pi.get(i).sync();
            }
        } finally {
            is.close();
            os.close();
        }

    }

}
