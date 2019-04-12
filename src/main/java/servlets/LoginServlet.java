package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.DataUser;
import models.Response;
import models.User;
import org.json.JSONException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ObjectMapper ojm = new ObjectMapper();
        User uLogin = ojm.readValue(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())), User.class);
        HttpSession ses = request.getSession(false);
        Response res = new Response();
        DataUser du = new DataUser();

        try {
            String username = uLogin.getUsername();
            String password = uLogin.getPassword();

            User u = du.getUser(username);
            System.out.println(username);

            if (du.authorization(username , password)) {
                res.setMessage("Signed in succesfully!");
                res.setStatus(200);
                ses.setAttribute("user", u);
                ses.setAttribute("check", true);
            } else {
                res.setMessage("Unable to sign you in, verify your credentials and try again");
                res.setStatus(403);
            }
        } catch (Exception ex) {
            try {
                res.setMessage("Internal error");
                res.setStatus(500);
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }catch (JSONException ex1){
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex1);
            }

            ex.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
