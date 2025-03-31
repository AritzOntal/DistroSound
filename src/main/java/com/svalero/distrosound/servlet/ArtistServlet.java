package com.svalero.distrosound.servlet;

import com.svalero.distrosound.dao.ArtistDao;
import com.svalero.distrosound.dao.UserDao;
import com.svalero.distrosound.database.Database;
import com.svalero.distrosound.exception.ArtistNotFoundException;
import com.svalero.distrosound.exception.UserNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class ArtistServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Database database = new Database();
            database.connect();
            ArtistDao aristDao = new ArtistDao(database.getConnection());



            database.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();

        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();

        } catch (IOException ioe) {

        } catch (ArtistNotFoundException unfe){
            response.getWriter().println ("No se ha podido añadir artista");

        }
    }
}
