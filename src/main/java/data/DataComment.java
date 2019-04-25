package data;

import utils.Database;
import utils.PropertiesReader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataComment {

    private final Connection con = Database.getInstance().getConnection();
    PropertiesReader props = PropertiesReader.getInstance();

    public boolean registerComment(int postId , int userId){

        PreparedStatement stm = null;
        boolean flag = false;
        ResultSet rs;




        return flag;
    }

}
