package com.svalero.distrosound.servlet;

import com.svalero.distrosound.dao.ArtistDao;
import com.svalero.distrosound.dao.RegistrationDao;
import com.svalero.distrosound.database.Database;
import com.svalero.distrosound.exception.ArtistNotFoundException;
import com.svalero.distrosound.model.Artist;
import com.svalero.distrosound.model.Registration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/artist")

public class ArtistServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("nombre");
        String last_name = request.getParameter("apellidos");
        String email = request.getParameter("email");
        LocalDate dateBirth = LocalDate.parse(request.getParameter("cumpleaños"));
        String StringPremium = request.getParameter("premium");

        if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty() ||
                name == null || name.trim().isEmpty() ||
                last_name == null || last_name.trim().isEmpty() ||
                email == null || email.trim().isEmpty()) {
            response.getWriter().write("No puede haber campos vacíos.");
            return;

        } else {

        }


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

            boolean premium = false;

            if ("yes".equalsIgnoreCase(StringPremium)) {
                premium = true;
            } else if ("no".equalsIgnoreCase(StringPremium)) {
                premium = false;
            }

            artist.setUsername(username);
            artist.setPassword(password);
            artist.setName(name);
            artist.setLast_name(last_name);
            artist.setEmail(email);
            artist.setBirth_date(dateBirth);
            artist.setRoyalties(85.50f);
            artist.setPremium(premium);

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

        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        }
    }
}
