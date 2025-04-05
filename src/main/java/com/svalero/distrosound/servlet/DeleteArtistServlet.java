package com.svalero.distrosound.servlet;

import com.svalero.distrosound.dao.ArtistDao;
import com.svalero.distrosound.database.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteArtist")
public class DeleteArtistServlet extends HttpServlet {

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        String idArtist = request.getParameter("id_artist");
        int id = Integer.parseInt(idArtist);


        try {
            Database database = new Database();
            database.connect();
            ArtistDao artistDao = new ArtistDao(database.getConnection());
            artistDao.deleteArtistById(id);

            response.sendRedirect("/DistroSound/logout");

        } catch (ClassNotFoundException | SQLException e) {

        }
    }
}
