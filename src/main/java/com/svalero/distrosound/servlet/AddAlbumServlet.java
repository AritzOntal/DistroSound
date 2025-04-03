package com.svalero.distrosound.servlet;

import com.svalero.distrosound.dao.AlbumDao;
import com.svalero.distrosound.database.Database;
import com.svalero.distrosound.model.Album;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/newAlbum")

public class AddAlbumServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException {
        String title = request.getParameter("titulo");
        String artist = request.getParameter("artista");
        String genre = request.getParameter("genero");
        String url_cover = request.getParameter("url_cover");
        String description = request.getParameter("descripcion");
        String ISRC = request.getParameter("ISRC");
        int idArtist = Integer.parseInt(request.getParameter("id_artist"));
        boolean explicit = Boolean.parseBoolean(request.getParameter("explicito"));
        float price = Float.parseFloat(request.getParameter("precio"));


        try {
            Database database = new Database();
            database.connect();
            AlbumDao albumDao = new AlbumDao(database.getConnection());

            Album album = new Album();

            album.setTitle(title);
            album.setArtist(artist);
            album.setGenre(genre);
            album.setDescription(description);
            album.setISRC(ISRC);
            album.setExplicit(explicit);
            album.setPrice(price);
            album.setUrl_cover(url_cover);
            album.setUploaded(LocalDate.now());
            album.setId_artist(idArtist);

            albumDao.add(album);


        }catch (SQLException sqle) {
            sqle.printStackTrace();

        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }

        }
    }
