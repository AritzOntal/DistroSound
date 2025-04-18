package com.svalero.distrosound.servlet;

import com.svalero.distrosound.dao.ArtistDao;
import com.svalero.distrosound.dao.EmployeeDao;
import com.svalero.distrosound.database.Database;
import com.svalero.distrosound.exception.ArtistNotFoundException;
import com.svalero.distrosound.exception.EmployeeNotFoundException;
import com.svalero.distrosound.model.Artist;
import com.svalero.distrosound.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/employee")

public class EmployeeServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("nombre");
        String last_name = request.getParameter("apellidos");
        String email = request.getParameter("email");

        try {
            Database database = new Database();
            database.connect();
            EmployeeDao employeeDao = new EmployeeDao(database.getConnection());

            if (employeeDao.exists(username)) {
                response.getWriter().print("error: Usuario ya registrado");
                return;
            }

            Employee employee = new Employee();

            employee.setUsername(username);
            employee.setPassword(password);
            employee.setName(name);
            employee.setLast_name(last_name);
            employee.setEmail(email);

            employeeDao.add(employee);

            response.getWriter().print("ok");

            database.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();

        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();

        } catch (IOException ioe) {
            ioe.printStackTrace();

        } catch (EmployeeNotFoundException enfe) {
            enfe.printStackTrace();
        }
    }
}
