package data;

import models.Post;
import utils.Database;
import utils.PropertiesReader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataPost {

    private final Connection con = Database.getInstance().getConnection();
    PropertiesReader props = PropertiesReader.getInstance();

    public Post getPost(int userId) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        Post p = new Post();
        try{
            stm = con.prepareStatement("SELECT * FROM post WHERE " + userId + " = ? LIMIT 1");
            stm.setString(1, String.valueOf(userId));
            rs = stm.executeQuery();
            if(rs.next()){
                rs.beforeFirst();
                while (rs.next()){
                    p.setId(rs.getInt("post_id"));
                    p.setText(rs.getString("post_text"));
                    p.setUrl(rs.getString("post_url"));
                    p.setTypeId(Integer.parseInt(rs.getString("type_post_id")));
                    p.setTimestamp(rs.getString("post_creation_time"));
                }
            } else {
                throw new Exception("No results");
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return p;
    }

    public List<Post> getPosts(int userId){
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Post> posts = new ArrayList<>();
        try{
            stm = con.prepareStatement(props.getValue("getOwnPosts"));
            stm.setInt(1,userId);
            if(rs.next()){
                rs.beforeFirst();
                while(rs.next()){
                    Post p = new Post();
                    p.setId(Integer.parseInt(rs.getString("post_id")));
                    p.setText(rs.getString("post_text"));
                    p.setUrl(rs.getString("post_url"));
                    p.setTypeId(Integer.parseInt(rs.getString("type_post_id")));
                    p.setTimestamp(rs.getString("post_creation_time"));
                    p.setUserId(Integer.parseInt(rs.getString("user_id")));
                    posts.add(p);
                }
            }else{
                return null;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return posts;
    }

    public boolean registerPost(int userId, String text, String url, int typeId){

        PreparedStatement stm = null;
        int rs;
        boolean flag = false;
        try{
            stm = con.prepareStatement(props.getValue("registerPost"));
            System.out.println(props.getValue("registerPost"));
            stm.setInt(1, userId );
            stm.setString(2, text);
            stm.setString(3, url);
            stm.setInt(4, typeId );
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

    public boolean postExists(int postId , int userId) {
        PreparedStatement stm = null;
        boolean flag = true;
        ResultSet rs = null;
        try {
            String q = this.props.getValue("checkPost");
            stm = con.prepareStatement(q);
            stm.setInt(1, postId);
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

    public boolean updatePost(String text, String url, int typeId, int postId) {
        PreparedStatement stm = null;
        DataPost dp = new DataPost();
        int rs;
        boolean flag = false;
        try {
            stm = con.prepareStatement(props.getValue("updatePost"));
            stm.setString(1,text );
            stm.setString(2, url);
            stm.setInt(3, typeId);
            stm.setInt(4, postId);
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

    public boolean deletePost(int userId , int postId) {
        PreparedStatement stm = null;
        int rs;
        boolean flag = false;
        try {
            stm = con.prepareStatement(props.getValue("deletePost"));
            stm.setInt(1, postId);
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
