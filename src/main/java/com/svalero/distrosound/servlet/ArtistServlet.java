package com.svalero.distrosound.servlet;

import com.svalero.distrosound.dao.ArtistDao;
import com.svalero.distrosound.dao.RegistrationDao;
import com.svalero.distrosound.dao.UserDao;
import com.svalero.distrosound.database.Database;
import com.svalero.distrosound.exception.ArtistNotFoundException;
import com.svalero.distrosound.exception.RegistrationNotFoundException;
import com.svalero.distrosound.exception.UserNotFoundException;
import com.svalero.distrosound.model.Artist;
import com.svalero.distrosound.model.Registration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@WebServlet("/artist")

public class ArtistServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("nombre");
        String last_name = request.getParameter("apellidos");
        String email = request.getParameter("email");

        try {
            Database database = new Database();
            database.connect();
            ArtistDao artistDao = new ArtistDao(database.getConnection());
            RegistrationDao registrationDao = new RegistrationDao(database.getConnection());


            if (artistDao.exists(username)) {
                response.getWriter().print("error: Usuario ya registrado");
                return;
            }

            Artist artist = new Artist();

            artist.setUsername(username);
            artist.setPassword(password);
            artist.setName(name);
            artist.setLast_name(last_name);
            artist.setEmail(email);

            artistDao.add(artist);

            Registration registration = new Registration();

            registration.setTipe("artist");
            registration.setCost(9.99f);
            registration.setRegist_date(LocalDate.now());
            registration.setPremium(false);
            registration.setId_artist(artist.getId());
            registration.setId_employee(null);

            registrationDao.add(registration);

            response.getWriter().print("ok");

            database.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();

        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();

        } catch (IOException ioe) {
            ioe.printStackTrace();

        } catch (ArtistNotFoundException arfe) {
            arfe.printStackTrace();
        }
    }
}
