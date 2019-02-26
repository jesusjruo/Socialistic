package utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class Database {

    private ResultSet rs;
    private ResultSetMetaData rsmd;
    private JSONArray table;
    private JSONObject row;
    private PreparedStatement pstmt;
    private Connection con;
    private PropertiesReader prop = PropertiesReader.getInstance();
    public Database(){
        try {
            Class.forName(prop.getValue("dbDriver"));
            this.con= DriverManager.getConnection(prop.getValue("dbUrl"),prop.getValue("dbUser"),prop.getValue("dbPassword"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
