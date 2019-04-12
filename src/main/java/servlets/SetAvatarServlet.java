package servlets;

import data.DataUser;
import models.Response;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet(name = "SetAvatarServlet" , urlPatterns = "/setAvatar")
@MultipartConfig

public class SetAvatarServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part file = request.getPart("file");
        Response<User> res = new Response();
        DataUser db = new DataUser();

        try{
            if(db.setAvatar(file)){
                res.setMessage("Avatar set succesfully");
                res.setStatus(200);
            }else{
                res.setMessage("Avatar image not found");
                res.setStatus(404);
            }
        }catch (Exception e){
            res.setMessage("Error occured");
            res.setStatus(500);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
