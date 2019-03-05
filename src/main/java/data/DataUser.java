package data;

import utils.Database;
import utils.PropertiesReader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static utils.HashPassword.hashPassword;

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

    public boolean registerUser(String name, String lastName, String email, String username, String password, String birthday, String gender) {
        PreparedStatement stm = null;
        int rs;
        boolean flag = false;
        java.sql.Date b = java.sql.Date.valueOf(birthday);
        try {
            System.out.println(props.getValue("registerUser"));
            System.out.println(hashPassword(password));
            stm = con.prepareStatement(props.getValue("registerUser"));
            stm.setString(1, name);
            stm.setString(2, lastName);
            stm.setString(3, email);
            stm.setString(4, username);
            stm.setString(5, hashPassword(password));
            stm.setDate(6, b);
            stm.setString(7, gender);
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