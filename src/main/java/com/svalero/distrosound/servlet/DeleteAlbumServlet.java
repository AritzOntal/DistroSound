package com.svalero.distrosound.servlet;

import com.svalero.distrosound.dao.AlbumDao;
import com.svalero.distrosound.dao.ArtistDao;
import com.svalero.distrosound.database.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteAlbum")
public class DeleteAlbumServlet extends HttpServlet {

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        String idAlbum = request.getParameter("id_album");
        int id = Integer.parseInt(idAlbum);


        try {
            Database database = new Database();
            database.connect();
            AlbumDao albumDao = new AlbumDao(database.getConnection());
            albumDao.deleteAlbumById(id);

            response.sendRedirect("client.zone.jsp");

        } catch (ClassNotFoundException | SQLException e) {

        }
    }
}
