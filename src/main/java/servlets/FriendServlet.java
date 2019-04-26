package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.DataFriend;
import models.Friend;
import models.Response;
import models.User;
import data.DataUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(name = "FriendServlet", urlPatterns = "/friend")
public class FriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ObjectMapper objM = new ObjectMapper();
        Response<User> res = new Response();
        Friend addFriend = objM.readValue(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())), Friend.class);
        User u = (User) request.getSession(false).getAttribute("user");

        try{
            DataFriend df = new DataFriend();
            DataUser du = new DataUser();
            String friendUsername = addFriend.getUsername();

            if(du.usernameExists(friendUsername)){

                if(df.friendshipExists(u.getId() , friendUsername)){
                    res.setMessage("Friend already added");
                    res.setStatus(401);
                }else{
                    if(df.registerFriend(u.getId(), friendUsername)){
                        res.setMessage("Friend added succesfully!");
                        res.setStatus(200);
                    }else{
                        res.setMessage("Internal error");
                        res.setStatus(500);
                    }
                }
            }else{
                res.setMessage("Username not found");
                res.setStatus(404);
            }
        }catch(Exception e){
            res.setMessage("Internal error");
            res.setStatus(500);
        }

        String resp = objM.writeValueAsString(res);
        response.getWriter().print(resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
