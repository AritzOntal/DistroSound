package com.svalero.distrosound.servlet;

import com.svalero.distrosound.dao.AlbumDao;
import com.svalero.distrosound.dao.EmployeeDao;
import com.svalero.distrosound.database.Database;
import com.svalero.distrosound.model.Album;
import com.svalero.distrosound.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/modifyEmployee")
@MultipartConfig

public class EditEmployeeServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String newName = request.getParameter("nombre");
        String newEmail = request.getParameter("email");
        String newLast_name = request.getParameter("apellidos");
        String newSpeciality = request.getParameter("especialidad");
        String newUsername = request.getParameter("username");
        String newPassword = request.getParameter("password");
        int idEmployee = Integer.parseInt(request.getParameter("id_employee"));
        String newStringActive =request.getParameter("active");


        try {
            Database database = new Database();
            database.connect();
            EmployeeDao employeeDao = new EmployeeDao(database.getConnection());

            Employee employee = new Employee();

            boolean active = false;

              if ("yes".equalsIgnoreCase(newStringActive)) {
                active = true;
            } else if ("No".equalsIgnoreCase(newStringActive)) {}
                active = false;

            employee.setId_employee(idEmployee);
            employee.setName(newName);
            employee.setEmail(newEmail);
            employee.setLast_name(newLast_name);
            employee.setSpeciality(newSpeciality);
            employee.setActive(active);
            employee.setUsername(newUsername);
            employee.setPassword(newPassword);

            employeeDao.modifyEmployee(employee);

            response.getWriter().print("ok");

            database.close();

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
