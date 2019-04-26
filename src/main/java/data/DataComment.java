package data;

import utils.Database;
import utils.PropertiesReader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataComment {

    private final Connection con = Database.getInstance().getConnection();
    PropertiesReader props = PropertiesReader.getInstance();

    public boolean registerComment(int userId, String text, String url , int postId) {

        PreparedStatement stm = null;
        int rs;
        boolean flag = false;
        try{
            stm = con.prepareStatement(props.getValue("registerComment"));
            System.out.println(props.getValue("registerComment"));
            stm.setInt(1, userId);
            stm.setInt(2, postId);
            stm.setString(3, text);
            stm.setString(4, url);
            rs = stm.executeUpdate();
            if(rs > 0) {
                flag = true;
            } else {
                flag = false;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            flag = false;
        }
        return flag;
    }
}
