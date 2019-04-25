package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Response;
import org.json.JSONException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "LogoutServlet" , urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ObjectMapper ojm = new ObjectMapper();
        Response res = new Response();
        try{
            request.getSession(true).invalidate();
            res.setMessage("Session ended succesfully!");
            res.setStatus(200);
        }catch(Exception e){
            e.printStackTrace();
            try {
                res.setMessage("Something went wrong");
                res.setStatus(500);
            } catch (JSONException ex) {
                Logger.getLogger(LogoutServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        String r = ojm.writeValueAsString(res);
        response.getWriter().print(r);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
