
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

class DatabaseCon{

    String userName = "app";
    String password = "admin";
    String dbms = "mysql";
    String serverName;
    String portNumber = "3306";


    public static void main(String args[]) throws ClassNotFoundException {

        //Class.forName("com.mysql.cj.jdbc.Driver");
        DatabaseCon dcon = new DatabaseCon("127.0.0.1");
        try {
            Connection connection = dcon.getConnection();
            DatabaseCon.viewTable(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public DatabaseCon(String serverName){
        this.serverName = serverName;
        try {
            Connection con = getConnection();
            DatabaseCon.viewTable(con);
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
        }
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


    public static void viewTable(Connection con) throws SQLException {
        String query = "SELECT * FROM `meting`";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String coffeeName = rs.getString("meting");

                System.out.println(coffeeName);
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    public String[] executeQuery(String query){
        ArrayList<String> resultList = new ArrayList<String>();

        try (Statement stmt = getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                resultList.add(rs.getString("meting"));
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        
        return (String[]) resultList.toArray();
    }
}