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
        int rs2;
        boolean flag = false;
        int friendId = 0;
        try {
            stm = con.prepareStatement((props.getValue("checkFriendId")), ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stm.setString(1, friendUsername);
            rs = stm.executeQuery();
            if (rs.next()) {
                rs.beforeFirst();
                while (rs.next()) {
                    friendId = rs.getInt("user_id");
                }
                  stm2 = con.prepareStatement((props.getValue("registerFriend")));
                  stm2.setInt(1,7); //Consultar con el profesor.
                  stm2.setInt(2, userId);
                  stm2.setInt(3, friendId);

                  rs2 = stm2.executeUpdate();

                if (rs2 > 0) {
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

    public boolean friendshipExists(int userId , String friendUsername) {

        PreparedStatement stm3 = null;
        PreparedStatement stm4 = null;
        ResultSet rs3;
        ResultSet rs4;
        boolean flag = false;
        int friendId = 0;

        try {
            stm3 = con.prepareStatement((props.getValue("checkFriendId")), ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stm3.setString(1, friendUsername);
            rs3 = stm3.executeQuery();
            if (rs3.next()) {
                rs3.beforeFirst();
                while (rs3.next()) {
                    friendId = rs3.getInt("user_id");
                }
                stm4 = con.prepareStatement((props.getValue("checkFriendship")),ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                stm4.setInt(1, userId);
                stm4.setInt(2, friendId);

                rs4 = stm4.executeQuery();

                if (rs4.next()) {
                    rs4.beforeFirst();
                    while (rs3.next()) {
                        flag = true;
                    }
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
