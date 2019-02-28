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

    public JSONArray executeQuery(String query, Object... values) {
        try {
            this.pstmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            for (int i = 0; i < values.length; i++) {
                this.pstmt.setObject(i + 1, values[i]);
            }
            this.rs = this.pstmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.getTable(this.rs);
    }
    public void execute(String query, Object... values) {
        try {
            this.pstmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            for (int i = 0; i < values.length; i++) {
                this.pstmt.setObject(i + 1, values[i]);
            }

            this.pstmt.execute();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private JSONArray getTable(ResultSet rs) {
        try {
            this.rsmd = rs.getMetaData();
            rs.last();
            table = new JSONArray();
            rs.beforeFirst();
            while (rs.next()) {
                row = new JSONObject();
                for (int i = 1; i <= this.rsmd.getColumnCount(); i++) {
                    row.put(rsmd.getColumnLabel(i), rs.getObject(i));
                }
                table.put(row);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    public void closeCon() {
        try {
            this.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
