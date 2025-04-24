package com.svalero.distrosound.servlet;

import com.svalero.distrosound.dao.ArtistDao;
import com.svalero.distrosound.dao.EmployeeDao;
import com.svalero.distrosound.database.Database;
import com.svalero.distrosound.exception.ArtistNotFoundException;
import com.svalero.distrosound.exception.EmployeeNotFoundException;

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
            EmployeeDao employeeDao = new EmployeeDao(database.getConnection());
            ArtistDao artistDao = new ArtistDao(database.getConnection());
            String role = null;

                Integer idArtist = artistDao.getArtistIdByUsername(username);
                Integer idEmployee = employeeDao.getEmployeeIdByUsername(username);

            try {

                role = employeeDao.loginEmployee(username, password);

            } catch (EmployeeNotFoundException e) {

            }

            if (role == null) {
                try {
                    role = artistDao.loginArtist(username, password);

                } catch (ArtistNotFoundException e) {
                    response.getWriter().print("El usuario no existe");

                    return;
                }
            }

            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", role);
            session.setAttribute("id_artist", idArtist);
            session.setAttribute("id_employee", idEmployee);

            response.getWriter().print("ok");

            database.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();

        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }
}
