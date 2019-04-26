package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.DataComment;
import data.DataPost;
import models.Comment;
import models.Post;
import models.Response;
import models.User;
import utils.Mapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;

@WebServlet(name = "CommentServlet" , urlPatterns = "/comment")
public class CommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ObjectMapper ojm = new ObjectMapper();
        Response<Comment> res = new Response();
        User u = (User) request.getSession(false).getAttribute("user");
        DataPost dp = new DataPost();
        DataComment dc = new DataComment();
        Mapper reqMapper = new Mapper();
        HashMap jsonMapper = reqMapper.dataToMap(request);
        Post p = new Post();

        try{
            int userId = u.getId();
            int postId = Integer.parseInt(request.getParameter("postId"));
            String text = (String) jsonMapper.get("text");
            String url = (String) jsonMapper.get("url");

            if(dc.registerComment(userId ,text, url , postId)){
                res.setMessage("Comment created succesfully");
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
