package com.svalero.distrosound.servlet;

import com.svalero.distrosound.dao.AlbumDao;
import com.svalero.distrosound.database.Database;
import com.svalero.distrosound.model.Album;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/modifyArtist")
@MultipartConfig

public class EditArtistServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String newTitle = request.getParameter("titulo");
        String newArtist = request.getParameter("artista");
        String newGenre = request.getParameter("genero");
        String newDescription = request.getParameter("descripcion");
        String newISRC = request.getParameter("ISRC");
        String newExplicitString = request.getParameter("explicito");
        float newPrice = Float.parseFloat(request.getParameter("precio"));
        int newAlbumId = Integer.parseInt(request.getParameter("id_album"));

        try {
            Database database = new Database();
            database.connect();
            AlbumDao albumDao = new AlbumDao(database.getConnection());

            Album album = new Album();

            boolean explicit = false;

            if ("yes".equalsIgnoreCase(newExplicitString)) {
                explicit = true;
            } else if ("no".equalsIgnoreCase(newExplicitString)) {
                explicit = false;
            }

            album.setTitle(newTitle);
            album.setArtist(newArtist);
            album.setGenre(newGenre);
            album.setDescription(newDescription);
            album.setISRC(newISRC);
            album.setId_album(newAlbumId);
            album.setPrice(newPrice);
            album.setExplicit(explicit);

            albumDao.modifyAlbum(album);

            response.getWriter().print("ok");

            database.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
