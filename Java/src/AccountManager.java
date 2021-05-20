import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class AccountManager {

    private static FileWriter file;

    public static void main(String[] args) {
        AccountManager accountManager = new AccountManager();
        accountManager.makeAccount("peter", "password");
        accountManager.makeAccount("jantje", "password1");
        System.out.println(accountManager.validateAccount("jantje", "password1"));
    }

    public boolean validateAccount(String name, String password){
        byte[] encodedhash = new byte[0];
        JSONObject currentAccountList = new JSONObject();
        JSONArray temp = new JSONArray();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            //System.out.println(bytesToHex(encodedhash));
        }
        catch (Exception e){
            System.out.println("Hashing not possible");
            e.printStackTrace();
        }

        try{
            JSONParser jsonParser = new JSONParser();
            FileReader accountListReader = new FileReader("Accounts.json");
            Object obj = jsonParser.parse(accountListReader);
            currentAccountList = (JSONObject) obj;
            //System.out.println(currentAccountList);
        }
        catch (Exception e){

        }

        temp.add("name: " + name);
        temp.add("password: " + bytesToHex(encodedhash));

        for (int i = 0; i < currentAccountList.size(); i++){
            Object object = currentAccountList.get(String.valueOf(i));
            if (object.equals(temp)){
                return true;
            }
        }

        return false;
    }

    public void makeAccount(String name, String password) {
        byte[] encodedhash = new byte[0];
        JSONObject currentAccountList = new JSONObject();
        JSONArray temp = new JSONArray();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String originalString;
            encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            //System.out.println(bytesToHex(encodedhash));
        } catch (Exception e) {
            System.out.println("Hashing not possible");
            e.printStackTrace();
        }

        try {
            JSONParser jsonParser = new JSONParser();
            FileReader accountListReader = new FileReader("Accounts.json");
            Object obj = jsonParser.parse(accountListReader);
            //System.out.println(obj);
            currentAccountList = (JSONObject) obj;
            //System.out.println(currentAccountList);
        } catch (Exception e) {

        }

        temp.add("name: " + name);
        temp.add("password: " + bytesToHex(encodedhash));
        boolean isDupe = false;

        for (int i = 0; i < currentAccountList.size(); i++){
            if (currentAccountList.get(String.valueOf(i)).equals(temp)) {
                isDupe = true;
            }
        }

        if (!isDupe){
            currentAccountList.put(currentAccountList.size(), temp);
            System.out.println("no dupe");
        }

        try {
            file = new FileWriter("Accounts.json");
            file.write(currentAccountList.toJSONString());
            //System.out.println(currentAccountList.toJSONString());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                file.flush();
                file.close();
            }
            catch (Exception e){

            }
        }


    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
