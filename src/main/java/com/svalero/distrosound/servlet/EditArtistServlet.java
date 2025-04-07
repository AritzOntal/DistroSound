package com.svalero.distrosound.servlet;

import com.svalero.distrosound.dao.AlbumDao;
import com.svalero.distrosound.dao.ArtistDao;
import com.svalero.distrosound.database.Database;
import com.svalero.distrosound.model.Album;
import com.svalero.distrosound.model.Artist;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/modifyArtist")
@MultipartConfig
public class EditArtistServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String newName = request.getParameter("nombre");
        String newLastName = request.getParameter("apellidos");
        String newUsername = request.getParameter("username");
        String newEmail = request.getParameter("email");
        String newStringPremium = request.getParameter("premium");
        String idArtist = request.getParameter("id_artist");
        String newPassword = request.getParameter("password");
        int idArtistInt = Integer.parseInt(idArtist);
        LocalDate dateBirth = LocalDate.parse(request.getParameter("cumpleaños"));

        try {
            Database database = new Database();
            database.connect();
            ArtistDao artistDao = new ArtistDao(database.getConnection());

            Artist artist = new Artist();

            boolean premium = false;

            if ("yes".equalsIgnoreCase(newStringPremium)) {
                premium = true;
            } else if ("no".equalsIgnoreCase(newStringPremium)) {
                premium = false;
            }

            artist.setName(newName);
            artist.setLast_name(newLastName);
            artist.setUsername(newUsername);
            artist.setEmail(newEmail);
            artist.setPremium(premium);
            artist.setId(idArtistInt);
            artist.setBirth_date(dateBirth);
            artist.setRoyalties(0.80f);
            artist.setPassword(newPassword);

            artistDao.modifyArtist(artist);

            response.getWriter().print("ok");

            database.close();

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().print("error");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
