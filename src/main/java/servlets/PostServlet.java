package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.DataPost;
import javafx.geometry.Pos;
import models.Post;
import models.Response;
import models.User;
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

@WebServlet(name = "PostServlet" , urlPatterns = "/post")
public class PostServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DataPost dp = new DataPost();
        Post p = new Post();
        Mapper reqMapper = new Mapper();
        HashMap jsonMapper = reqMapper.dataToMap(request);
        User u = (User) request.getSession(false).getAttribute("user");
        Response<Post> res = new Response();

        try{
            String text = (String) jsonMapper.get("text");
            String url = (String) jsonMapper.get("url");
            int typeId = (int) jsonMapper.get("typeId");
            int postId = p.getId();

            if(dp.postExists(p.getId())) {

                if (dp.updatePost(text, url, typeId, postId)) {
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

    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        int postId = Integer.parseInt(request.getParameter("postId"));
        DataPost dp = new DataPost();
        Response res = new Response();

        if(dp.deletePost(postId)){
            res.setMessage("Post deleted succesfully");
            res.setStatus(200);

        }else{
            res.setMessage("Internal error");
            res.setStatus(500);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
