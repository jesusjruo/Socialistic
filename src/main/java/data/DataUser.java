package data;

import models.User;
import utils.Database;
import utils.PropertiesReader;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import static utils.HashPassword.hashPassword;

@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB

public class DataUser {

    private final Connection con = Database.getInstance().getConnection();
    PropertiesReader props = PropertiesReader.getInstance();


    public User getUser(String username)  {
        PreparedStatement stm = null;
        ResultSet rs = null;
        User u = new User();

        try{
            stm = con.prepareStatement((props.getValue("checkUsername")), ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stm.setString(1, username);
            System.out.println(props.getValue("checkUsername"));
            rs = stm.executeQuery();
            System.out.println("query ejecutado");

            if(rs.next()){
                rs.beforeFirst();
                while (rs.next()){
                    u.setId(rs.getInt("user_id"));
                    u.setUsername(rs.getString("user_username"));
                    u.setPassword(rs.getString("user_password"));
                    u.setName(rs.getString("user_name"));
                    u.setLastname(rs.getString("user_lastname"));
                    u.setEmail(rs.getString("user_email"));
                    u.setBirthday(rs.getString("user_birthday"));
                    //u.setAvatar(rs.getString("user_avatar"));
                    u.setUser_sex(rs.getString("user_sex"));
                    u.setTimestamp(rs.getString("user_creation_time"));
                    System.out.println("parametros guardados en u");
                }

            } else {
                throw new Exception("No results");
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }

        System.out.println(u);
        return u;
    }

    public boolean authorization(String username , String password) {

        PreparedStatement stm = null;
        boolean flag = false;
        ResultSet rs;

        try {
            String q = this.props.getValue("authorization");
            System.out.println(props.getValue("authorization"));
            stm = con.prepareStatement(q);

            stm.setString(1, username);
            stm.setString(2, hashPassword(password));

            rs = stm.executeQuery();

            if (rs.next()) {
                flag = true;
            } else {
                flag = false;
            }
        } catch (Exception ex) {
            return false;
        }

        System.out.println(flag);
        return flag;
    }

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

    public boolean userExists(int id) {
        PreparedStatement stm = null;
        boolean flag = true;
        ResultSet rs = null;
        try {
            String q = this.props.getValue("checkUser");
            stm = con.prepareStatement(q);
            stm.setInt(1, id);
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

    public boolean registerUser(String name, String lastName, String email, String username, String password, String birthday, String gender) throws IOException {
        PreparedStatement stm = null;
        int rs;
        boolean flag = false;
        Date b = Date.valueOf(birthday);

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

    public boolean setAvatar(Part file) throws IOException{
        PreparedStatement stm = null;
        int rs;
        boolean flag = false;
        InputStream avatar = file.getInputStream();

        if (avatar != null){

            try{
                System.out.println(props.getValue("setAvatar"));
                stm = con.prepareStatement(props.getValue("setAvatar"));
                stm.setBlob(1,avatar);
                rs = stm.executeUpdate();
                if (rs > 0) {
                    flag = true;
                } else {
                    flag = false;
                }
            }catch (Exception ex){
                ex.printStackTrace();
                flag = false;
            }

        }else{
            System.out.println("No se registro archivo");
        }
        return flag;
    }
}