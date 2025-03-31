package com.svalero.distrosound.servlet;

import com.svalero.distrosound.dao.UserDao;
import com.svalero.distrosound.database.Database;
import com.svalero.distrosound.exception.UserNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

//aqui le digo cual es su URL
@WebServlet("/logout")

public class LogoutServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession();
        currentSession.invalidate();
        response.sendRedirect("/DistroSound");
    }
}
