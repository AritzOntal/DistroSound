package com.svalero.distrosound.servlet;

import com.svalero.distrosound.dao.ClientDao;
import com.svalero.distrosound.database.Database;

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
            ClientDao clientDao = new ClientDao(database.getConnection());
            boolean clientTrue = clientDao.loginClient(username, password);
            if (!clientTrue) {
                response.getWriter().println("Usuario/contraseña incorrectos");

                return;

            } else {
                //CREAR LA SESION
                HttpSession session = request.getSession();
                //GUARDAR SU USUARIO
                session.setAttribute("username", username);
                //TODO AÑADIR EL ROL
                //llevar al pagina home
                response.getWriter().print("ok");
            }

            database.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();

        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();

        } catch (IOException ioe) {

        }
    }


}
