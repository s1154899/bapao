package raspberry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;

public class UploadedScripts {
    public String name;
    public String fileName;
    public String interval;
    public int intervalTime;

    public static void main(String[] args){

//        UploadedScripts.addNewScript( new UploadedScripts("t","tt","ttt",2));

        UploadedScripts.changeScript("t","ttdasdasd","2",20);

        for (UploadedScripts up : UploadedScripts.ReadScripts()){
            System.out.println(up.fileName);
        }


    }

    public UploadedScripts(String name,String fileName, String interval, int intervalTime){
        this.name = name;
        this.fileName = fileName;
        this.interval = interval;
        this.intervalTime = intervalTime;

    }


    public static UploadedScripts[] ReadScripts()  {
        FileReader reader = null;
        try {
        reader = new FileReader("./Scripts.json");
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray =   (JSONArray) jsonParser.parse(reader);

        UploadedScripts[] scripts = new UploadedScripts[jsonArray.size()];

        for (int i = 0; i < jsonArray.size(); i++){
            JSONObject obj = (JSONObject) jsonArray.get(i);
            scripts[i] = new UploadedScripts(obj.get("name").toString(),obj.get("fileName").toString(),obj.get("interval").toString(),Integer.parseInt(obj.get("intervalTime").toString()));
            System.out.println(obj.toJSONString());
        }

        return scripts;
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return new UploadedScripts[0];
    }

    public static void changeScript(String scriptName, String fileNameChanged, String intervalChanged, int intervalTimeChanged){
        JSONArray jsonArray = new JSONArray();

        for (UploadedScripts script : ReadScripts()){
            JSONObject obj = new JSONObject();
            if (scriptName.equals(script.name)){
                obj.put("name",script.name );
                obj.put("fileName",fileNameChanged );
                obj.put("interval",intervalChanged );
                obj.put("intervalTime",intervalTimeChanged );
                jsonArray.add(obj);
            }else{
                obj.put("name",script.name );
                obj.put("fileName",script.fileName );
                obj.put("interval",script.interval );
                obj.put("intervalTime",script.intervalTime );
                jsonArray.add(obj);
            }

        }
        try {
            Files.writeString(Path.of("./Scripts.json"), jsonArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void removePlaylist(String scriptName){
        JSONArray jsonArray = new JSONArray();

        for (UploadedScripts script : ReadScripts()){
            JSONObject obj = new JSONObject();
            if (!scriptName.equals(script.name)){
                obj.put("name",script.name );
                obj.put("fileName",script.fileName );
                obj.put("interval",script.interval );
                obj.put("intervalTime",script.intervalTime );
                jsonArray.add(obj);
            }else{

                for(RaspberryPi rasp :  RaspberryPi.connectedPis){
                    try {
                        rasp.databaseCon.removeScript(scriptName);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        }
        try {
            Files.writeString(Path.of("./Scripts.json"), jsonArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void addNewScript(UploadedScripts scripts){
        JSONArray jsonArray = new JSONArray();


        for (UploadedScripts script : ReadScripts()){
            JSONObject obj = new JSONObject();
            obj.put("name",script.name );
            obj.put("fileName",script.fileName );
            obj.put("interval",script.interval );
            obj.put("intervalTime",script.intervalTime );
            jsonArray.add(obj);
        }


            JSONObject obj = new JSONObject();
            obj.put("name",scripts.name );
            obj.put("fileName",scripts.fileName );
            obj.put("interval",scripts.interval );
            obj.put("intervalTime",scripts.intervalTime );
            jsonArray.add(obj);

            for(RaspberryPi rasp :  RaspberryPi.connectedPis){
                rasp.databaseCon.uploadScript(scripts.name, scripts.fileName, scripts.interval, scripts.intervalTime);
            }
        System.out.println(jsonArray.toJSONString());
        try {
            Files.writeString(Path.of("./Scripts.json"), jsonArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
