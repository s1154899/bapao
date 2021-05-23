package raspberry;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DatabaseCon{

    String userName = "admin";
    String password = "admin";
    String dbms = "mysql";
    String serverName;
    String portNumber = "3306";

    private Connection con;


//    public static void main(String args[]) throws ClassNotFoundException {
//
////        Class.forName("com.mysql.cj.jdbc.Driver");
//        DatabaseCon dcon = new DatabaseCon("127.0.0.1");
//        try {
//            Connection connection = dcon.getConnection();
//            DatabaseCon.viewTable(connection);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }

    public DatabaseCon(String serverName) throws SQLException {
        this.serverName = serverName;
        con = getConnection();

    }


    public Connection getConnection() throws SQLException {

        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);

        if (this.dbms.equals("mysql")) {
            conn = DriverManager.getConnection(
                    "jdbc:" + this.dbms + "://" +
                            this.serverName +
                            ":" + this.portNumber + "/sensors",
                    connectionProps);
        }
//        else if (this.dbms.equals("derby")) {
//            conn = DriverManager.getConnection(
//                    "jdbc:" + this.dbms + ":" +
//                            this.dbName +
//                            ";create=true",
//                    connectionProps);
//        }
        System.out.println("Connected to database");
        return conn;
    }


    public void viewTable() throws SQLException {
        String query = "SELECT * FROM `meting` A LEFT JOIN meting_types B ON A.metingTypesID = B.TypeID;";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String meting = rs.getString("meting");
                String type = rs.getString("type");
                System.out.println(meting+ " " + type);
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }
    public int[] GetResults(int Limit , String type) throws SQLException {

        int[] results = new int[Limit];

        String query = "SELECT * FROM `meting` A LEFT JOIN meting_types B ON A.metingTypesID = B.TypeID WHERE type = '"+type+"' LIMIT "+ Limit;
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            int count = 0;
            while (rs.next()) {
                String meting = rs.getString("meting");

                results[count] = (Integer.parseInt(meting));
                System.out.println(meting+ " " + type);
                count++;
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        System.out.println(" " + type);
        return results;
    }

    public String[] GetTimestamps(int Limit , String type) throws SQLException {

        String[] results = new String[Limit];

        String query = "SELECT * FROM `meting` A LEFT JOIN meting_types B ON A.metingTypesID = B.TypeID WHERE type = '"+type+"' LIMIT "+ Limit;
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            int count = 0;
            while (rs.next()) {
                String meting = rs.getString("timestamps");

                results[count] = meting;
                System.out.println(meting+ " " + type);
                count++;
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }

        return results;
    }

    public ArrayList<String[]> GetNieuwResults() throws SQLException {
        ArrayList<String[]> results = new ArrayList<>();


        String query = "SELECT * FROM `meting` A LEFT JOIN meting_types B ON A.metingTypesID = B.TypeID WHERE `timestamp` in (SELECT max(`timestamp`) FROM `meting` GROUP BY `metingTypesID` );";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String meting = rs.getString("meting");
                String type = rs.getString("type");
                results.add(new String[]{type,meting});
                System.out.println(meting+ " " + type);
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }

        return results;
    }

    public int[] getTemp() throws SQLException {

        String query = "SELECT COUNT(*) A FROM `meting` where metingTypesID = 1;";
        int size =0;
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);


            while (rs.next())
            {
//                rs.last();    // moves cursor to the last row
                size = Integer.parseInt(rs.getString("A")); // get row id
            }

        } catch (SQLException e) {
            //e.printStackTrace();
        }

        int[] amount = new int[size];

        query = "SELECT * FROM `meting` where metingTypesID = 1;";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                amount[i] = Integer.parseInt(rs.getString("meting"));
                //String type = rs.getString("type");
//                System.out.println(meting);
                i++;
            }

        } catch (SQLException e) {
            //e.printStackTrace();
        }

        return amount;
    }

    public void playmusic(String song) throws SQLException {
        // the mysql insert statement
        //TODO add prepared
        String query = " INSERT INTO `sensors`.`player` (`location`,`set`) VALUES(\"/home/demo/ftp/music/"+song+"\",\"play\");";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = con.prepareStatement(query);
        //preparedStmt.setString (1, song);

        // execute the preparedstatement
        preparedStmt.execute();

    }


    public void backMusic() throws SQLException {
        // the mysql insert statement
        //TODO add prepared
        String query = "UPDATE `sensors`.`player` SET `set` = 'play' WHERE `idplayer` = (SELECT MAX(`idplayer`) FROM (select * from `sensors`.`player` WHERE `set` = 'played') as t);";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = con.prepareStatement(query);
        //preparedStmt.setString (1, song);

        // execute the preparedstatement
        preparedStmt.execute();

        query = "UPDATE `sensors`.`player` SET `set` = 'played' WHERE `set` = 'playing' OR `set` = 'paused' OR `set` = 'pause' ;";

        // create the mysql insert preparedstatement
        preparedStmt = con.prepareStatement(query);
        //preparedStmt.setString (1, song);

        // execute the preparedstatement
        preparedStmt.execute();
    }

    public void pauseMusic() throws SQLException {
        // the mysql insert statement
        //TODO add prepared
        String query = "UPDATE `sensors`.`player` SET `set` = 'pause' WHERE `set` = 'playing';";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = con.prepareStatement(query);
        //preparedStmt.setString (1, song);

        // execute the preparedstatement
        preparedStmt.execute();
    }

    public void nextMusic() throws SQLException {
        // the mysql insert statement
        //TODO add prepared
        String query = "UPDATE `sensors`.`player` SET `set` = 'played' WHERE `set` = 'playing' OR `set` = 'paused' OR `set` = 'pause' ;";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = con.prepareStatement(query);
        //preparedStmt.setString (1, song);

        // execute the preparedstatement
        preparedStmt.execute();
    }

    public void getScriptNames(){
        String query = "SELECT `ScriptName` FROM `sensors`";
        int size =0;
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);


            while (rs.next())
            {
//                rs.last();    // moves cursor to the last row
                System.out.println(rs.getString("ScriptName")); // get row id
            }

        } catch (SQLException e) {
            //e.printStackTrace();
        }

    }
    public void uploadScript(String name, String location, String unit , int time) {
        String query = "INSERT INTO `sensors`.`saved_script` (`ScriptName`,`ScriptLocation`,`ScriptUnit`,`ScriptTime`,`execAt`) VALUES ('"+name+"',"+"'/home/demo/ftp/scripts/"+location+"','"+unit+"',"+time+",0);";
        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = con.prepareStatement(query);
            preparedStmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}