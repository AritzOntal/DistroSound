package com.svalero.distrosound.servlet;

import com.svalero.distrosound.dao.UserDao;
import com.svalero.distrosound.database.Database;
import com.svalero.distrosound.exception.UserNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

//aqui le digo cual es su URL
@WebServlet("/login")

public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Database database = new Database();
            database.connect();
            UserDao userDao = new UserDao(database.getConnection());
            String role = userDao.loginUser(username, password);
                //CREAR LA SESION
                HttpSession session = request.getSession();
                //GUARDAR SU USUARIO
                session.setAttribute("username", username);
                session.setAttribute("role", role);
                //llevar al pagina home
                response.getWriter().print("ok");

            database.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();

        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();

        } catch (IOException ioe) {

        } catch (UserNotFoundException unfe){
            response.getWriter().println ("Usuario/contraseña incorrectos");

        }
    }


}
