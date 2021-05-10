package music;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Song {

public File file;
public int duration;
public String name;
public String album;
public String Artist;

    public static void main(String[] args) {

        File file = new File("C:/Users/edmar/Downloads/test.mp3");

        System.out.println(file);
        System.out.println(file.getAbsolutePath());
//        System.out.println(file.getCanonicalFile());
        System.out.println(file.getName());
        try {
            FileInputStream fin = new FileInputStream(file);
            //fin.getChannel().position(fin.getChannel().size() - 128);
            byte[] a = new  byte[128];
            while(fin.read(a) != -1){
                String s = new String(a , "ISO-8859-1");
                System.out.println(s);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


public Song(String location, int duration, String name, String album , String artist ){

 File file = new File(location);


}

public static void AddSong(String location){}
public static void RemoveSong(Song song){}


}
