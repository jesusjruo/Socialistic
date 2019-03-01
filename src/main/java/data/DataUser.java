package data;


import utils.Database;
import utils.PropertiesReader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataUser {

    private final Connection con = Database.getInstance().getConnection();
    PropertiesReader props = PropertiesReader.getInstance();

    public boolean usernameExists(String username) {
        PreparedStatement stm = null;
        boolean flag = true;
        ResultSet rs = null;
        try {
            String q = this.props.getValue("checkUsername");
            stm = con.prepareStatement(q);
            stm.setString(1, username);
            rs = stm.executeQuery();
            if (rs.next()) {
                flag = true;
            } else {
                flag = false;
            }
        } catch (Exception ex) {
            return false;
        }

        return flag;
    }

    public boolean emailExists(String email) {
        PreparedStatement stm = null;
        boolean flag = true;
        ResultSet rs = null;
        try {
            String q = props.getValue("checkEmail");
            stm = con.prepareStatement(q);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                flag = true;
            } else {
                flag = false;
            }
        } catch (Exception ex) {
            return false;
        }

        return flag;
    }

    public boolean registerUser(String name, String lastName, String email, String username, String password, String birthday, String gender, String t) {
        PreparedStatement stm = null;
        int rs;
        boolean flag = false;
        try {
            System.out.println(props.getValue("registerUser"));
            stm = con.prepareStatement(props.getValue("registerUser"));
            System.out.println(password);
            stm.setString(1, name);
            stm.setString(2, lastName);
            stm.setString(3, email);
            stm.setString(4, username);
            stm.setString(5, password);
            stm.setString(6, birthday);
            stm.setString(7, gender);
            stm.setString(8, t);
            rs = stm.executeUpdate();
            if (rs > 0) {
                flag = true;
            } else {
                flag = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            flag = false;
        }
        return flag;
    }
}