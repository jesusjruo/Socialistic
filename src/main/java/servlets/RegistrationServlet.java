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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@WebServlet(name = "RegistrationServlet" , urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ObjectMapper objM = new ObjectMapper();
        Response<User> res = new Response();
        User userRegister = objM.readValue(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())), User.class);

        try {
            DataUser db = new DataUser();
            String name     = userRegister.getName();
            String lastName = userRegister.getLastname();
            String email    = userRegister.getEmail();
            String username = userRegister.getUsername();
            String password = userRegister.getPassword();
            String birthday = userRegister.getBirthday();
            String gender = userRegister.getUser_sex();


            if(db.usernameExists(username) || db.emailExists(email)){
                res.setMessage("Username o Correo ya exisentes");
                res.setStatus(404);
            }else{
                if(db.registerUser(name, lastName, email, username, password, birthday, gender)){
                    res.setMessage("Registrado exitosamente");
                    res.setStatus(200);
                }else{
                    res.setMessage("Ocurrio un error");
                    res.setStatus(500);

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                res.setStatus(501);
            } catch (JSONException ex1) {
                Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex1);
            }
            ex.printStackTrace();
        }

        //Se muestra el json de respuesta
        System.out.println(objM.writerWithDefaultPrettyPrinter().writeValueAsString(res));
        String resp = objM.writeValueAsString(res);
        response.getWriter().print(resp);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
