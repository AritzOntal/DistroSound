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
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

@WebServlet("/newAlbum")
@MultipartConfig

public class AddAlbumServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException {
        String title = request.getParameter("titulo");
        String artist = request.getParameter("artista");
        String genre = request.getParameter("genero");
        Part image = request.getPart("imagen");
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

            String filename = "default.jpg";
            if (image.getSize() != 0) {
                filename = UUID.randomUUID() + ".jpg";
                String imagePath = "/Users/aritzontalvillasanchez/Desktop/apache-tomcat-9.0.100/webapps/distrosound_images";
                InputStream inputStream = image.getInputStream();
                Files.copy(inputStream, Path.of(imagePath + File.separator + filename));
            }

            album.setTitle(title);
            album.setArtist(artist);
            album.setGenre(genre);
            album.setDescription(description);
            album.setISRC(ISRC);
            album.setExplicit(explicit);
            album.setPrice(price);
            album.setImage(filename);
            album.setUploaded(LocalDate.now());
            album.setId_artist(idArtist);

            albumDao.add(album);

            response.getWriter().print("ok");


        }catch (SQLException sqle) {
            sqle.printStackTrace();

        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }

        }
    }
