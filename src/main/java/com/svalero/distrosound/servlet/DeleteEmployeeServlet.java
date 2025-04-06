package com.svalero.distrosound.servlet;

import com.svalero.distrosound.dao.ArtistDao;
import com.svalero.distrosound.dao.EmployeeDao;
import com.svalero.distrosound.database.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteEmployee")
public class DeleteEmployeeServlet extends HttpServlet {

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        String idEmployee = request.getParameter("id_employee");
        int id = Integer.parseInt(idEmployee);


        try {
            Database database = new Database();
            database.connect();
            EmployeeDao employeeDao = new EmployeeDao(database.getConnection());
            employeeDao.deleteEmployeeById(id);

            response.sendRedirect("/DistroSound/logout");

        } catch (ClassNotFoundException | SQLException e) {

        }
    }
}
