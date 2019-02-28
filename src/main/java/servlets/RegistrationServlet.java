package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegistrationServlet" , urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ObjectMapper objMapper = new ObjectMapper();

        try{

            Response<?> resp = new Response<>();
            resp.setMessage("Hola, probando respuesta con jackson");
            resp.setStatus(200);
            String res = objMapper.writeValueAsString(resp);
            System.out.println(objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(resp));
            response.getWriter().print(res);

        }
        catch(IOException e){
            e.printStackTrace();
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("Register servlet");
        out.println("<html><body>");
    }
}
