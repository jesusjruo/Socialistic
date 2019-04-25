package data;

import models.Friend;
import utils.Database;
import utils.PropertiesReader;

import java.sql.*;


public class DataFriend {

    private final Connection con = Database.getInstance().getConnection();
    PropertiesReader props = PropertiesReader.getInstance();

    public boolean registerFriend(int userId, String friendUsername) {

        PreparedStatement stm = null;
        PreparedStatement stm2 = null;
        ResultSet rs;
        ResultSet rs2;
        boolean flag = false;
        Friend f = new Friend();

        try {
            stm = con.prepareStatement((props.getValue("checkFriendId")), ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stm.setString(1, friendUsername);
            rs = stm.executeQuery();
            if (rs.next()) {
                rs.beforeFirst();
                while (rs.next()) {
                    f.setId(rs.getInt("user_id"));
                }
                  stm2 = con.prepareStatement("INSERT INTO friends(user_id1 , user_id2 , date) VALUES (?, ?, NOW())");
                  stm2.setInt(1, userId);
                  stm2.setObject(2, f);

                  rs2 = stm2.executeQuery();

                if (rs2.next()) {
                    flag = true;

                } else {
                    flag = false;
                }

            } else {
                throw new Exception("No results");
            }
        }   catch (SQLException e) {
                e.printStackTrace();
                flag = false;

            }   catch (Exception e) {
                    e.printStackTrace();
                    flag = false;
                }
        return flag;
    }

}
