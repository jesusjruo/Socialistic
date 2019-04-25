package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.DataPost;
import javafx.geometry.Pos;
import models.Post;
import models.Response;
import models.User;
import org.json.JSONArray;
import org.json.JSONException;
import utils.Mapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "PostServlet" , urlPatterns = "/post")
public class PostServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ObjectMapper ojm = new ObjectMapper();
        DataPost dp = new DataPost();
        Mapper reqMapper = new Mapper();
        HashMap jsonMapper = reqMapper.dataToMap(request);
        User u = (User) request.getSession(false).getAttribute("user");
        Response<Post> res = new Response();

        try{
            String text = (String) jsonMapper.get("text");
            String url = (String) jsonMapper.get("url");
            int typeId = (int) jsonMapper.get("typeId");

            if(dp.registerPost((u.getId()),text, url, typeId)){
                res.setMessage("Post created succesfully");
                res.setStatus(200);
            }else{
                res.setMessage("Data not found");
                res.setStatus(404);
            }

        }catch(Exception ex) {
            res.setMessage("Internal Error");
            res.setStatus(500);
            ex.printStackTrace();
        }

        String r = ojm.writeValueAsString(res);
        response.getWriter().print(r);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DataPost dp = new DataPost();
        ObjectMapper ojm = new ObjectMapper();
        Mapper reqMapper = new Mapper();
        HashMap jsonMapper = reqMapper.dataToMap(request);
        User u = (User) request.getSession(false).getAttribute("user");
        Response<Post> res = new Response();
        int postId = Integer.parseInt(request.getParameter("postId"));

        try{
            String text = (String) jsonMapper.get("text");
            String url = (String) jsonMapper.get("url");
            int typeId = (int) jsonMapper.get("typeId");

            if(dp.postExists(postId , u.getId())) {

                if (dp.updatePost(text, url, typeId , postId)) {
                    res.setMessage("Post updated succesfully");
                    res.setStatus(200);
                }else {
                    res.setMessage("Data not found");
                    res.setStatus(404);
                }
            }else{
                res.setMessage("Post does not exists");
                res.setStatus(404);
            }

        }catch(Exception ex) {
            res.setMessage("Internal Error");
            res.setStatus(500);
            ex.printStackTrace();
        }

        String r = ojm.writeValueAsString(res);
        response.getWriter().print(r);

    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int postId = Integer.parseInt(request.getParameter("postId"));
        User u = (User) request.getSession(false).getAttribute("user");
        DataPost dp = new DataPost();
        Response res = new Response();
        ObjectMapper ojm = new ObjectMapper();

        try {
            if (dp.deletePost((u.getId()), postId)){
                res.setMessage("Post deleted succesfully");
                res.setStatus(200);
            } else {
                res.setMessage("Data not found");
                res.setStatus(404);
            }
        }catch(Exception ex){
            res.setMessage("Internal Error");
            res.setStatus(500);
            ex.printStackTrace();
        }

        String r = ojm.writeValueAsString(res);
        response.getWriter().print(r);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        ObjectMapper ojm = new ObjectMapper();
//        DataPost dp = new DataPost();
//        User u = (User) request.getSession(false).getAttribute("user");
//        Response<Post> res = new Response();
//        JSONArray jarray = new JSONArray();
//        List<Post> posts = dp.getPosts(u.getId());
//
//        try{
//            if (posts != null){
//                for (Post p : posts) {
//                    jarray.put(BoardServices.boardToJSON(b));
//                }
//            } else {
//                r.put("status", 404);
//            }
//        }catch (Exception ex) {
//            try {
//                r.put("status", 500);
//            } catch (JSONException ex1) {
//                Logger.getLogger(PostServlet.class.getName()).log(Level.SEVERE, null, ex1);
//            }
//            Logger.getLogger(PostServlet.class.getName()).log(Level.SEVERE, null, ex);
//            ex.printStackTrace();
//        }
//
//        String r = ojm.writeValueAsString(res);
//        response.getWriter().print(r);
    }
}
