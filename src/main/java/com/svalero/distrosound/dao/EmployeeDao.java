package com.svalero.distrosound.dao;

import com.svalero.distrosound.exception.ArtistNotFoundException;
import com.svalero.distrosound.exception.EmployeeNotFoundException;
import com.svalero.distrosound.exception.UserNotFoundException;
import com.svalero.distrosound.exception.albumNotFoundException;
import com.svalero.distrosound.model.Album;
import com.svalero.distrosound.model.Artist;
import com.svalero.distrosound.model.Employee;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDao {

    private Connection connection;

    public EmployeeDao(Connection connection) {
        this.connection = connection;

    }

    public boolean add(Employee employee) throws SQLException {
        String sql = "INSERT INTO EMPLOYEE (name, last_name, username, password, email, speciality, comision, hiring_date) VALUES (?,?,?,SHA1(?),?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, employee.getName());
        statement.setString(2, employee.getLast_name());
        statement.setString(3, employee.getUsername());
        statement.setString(4, employee.getPassword());
        statement.setString(5, employee.getEmail());
        statement.setString(6, employee.getSpeciality());
        statement.setFloat(7, employee.getComision());
        statement.setDate(8, java.sql.Date.valueOf(employee.getHiring_date()));


        int affectedRows = statement.executeUpdate();
        if (affectedRows > 0) {
            // Recuperamos el ID generado automáticamente
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idEmployee = generatedKeys.getInt(1);
                    employee.setId_employee(Integer.valueOf(idEmployee));
                }
            }
        }
        return affectedRows > 0;
    }

    public boolean exists(String username) throws SQLException, EmployeeNotFoundException {
        String sql = "SELECT * FROM EMPLOYEE WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet result = statement.executeQuery();
        return result.next();
    }

    public String loginEmployee(String username, String password) throws SQLException, EmployeeNotFoundException {
        String sql = "SELECT role FROM EMPLOYEE WHERE username = ? AND password = SHA1(?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet result = statement.executeQuery();
        if (!result.next()){
            throw new EmployeeNotFoundException();
        }
        return result.getString("role");
    }

    public Integer getEmployeeIdByUsername(String username) throws SQLException {
        String sql = "SELECT id_employee FROM EMPLOYEE WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("id_employee");
        } else {
            return null;
        }
    }

    public Employee getEmployeeById(int id) throws SQLException, EmployeeNotFoundException {
        String sql = "SELECT * FROM EMPLOYEE WHERE id_employee = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        if (!result.next()) {
            throw new EmployeeNotFoundException();
        }

        Employee employee = new Employee();

        employee.setId_employee(id);
        employee.setName(result.getString("name"));
        employee.setLast_name(result.getString("last_name"));
        employee.setUsername(result.getString("username"));
        employee.setPassword(result.getString("password"));
        employee.setEmail(result.getString("email"));
        employee.setActive(result.getBoolean("active"));
        employee.setSpeciality(result.getString("speciality"));
        employee.setComision(result.getFloat("comision"));
        employee.setHiring_date(result.getDate("hiring_date").toLocalDate());


        statement.close();

        return (employee);
    }

    public ArrayList getAllEmployee(String search) throws SQLException {
        String sql = "SELECT * FROM EMPLOYEE WHERE name LIKE ? OR last_name LIKE ?";
        return launchQuery(sql, search);
    }

    public ArrayList getAll() throws SQLException {
        String sql = "SELECT * FROM EMPLOYEE";
         return launchQuery(sql);
    }

    private ArrayList<Employee> launchQuery(String query, String ...search) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);

        if(search.length > 0){
            statement.setString(1, "%" + search[0]  + "%");
            statement.setString(2, "%" + search[0]  + "%");

        }

        ResultSet result = statement.executeQuery();
        ArrayList<Employee> employeeList = new ArrayList<>();
        while (result.next()) {
        Employee employee = new Employee();
        employee.setId_employee(result.getInt("id_employee"));
        employee.setName(result.getString("name"));
        employee.setLast_name(result.getString("last_name"));
        employee.setUsername(result.getString("username"));
        employee.setPassword(result.getString("password"));
        employee.setEmail(result.getString("email"));
        employeeList.add(employee);
        }
        result.close();
        statement.close();
        return employeeList;
    }


    //devuelve un único empleado
    public Employee get (int id){
        String sql = "SELECT * FROM EMPLOYEE WHERE id_employee = ?";
        PreparedStatement statement = null;

        return null;
    }


    public void modify(){

    }

    public boolean deleteEmployeeById(int id) throws SQLException {

        String sql = "DELETE FROM EMPLOYEE WHERE id_employee = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        int affectedRows = statement.executeUpdate();

        return affectedRows != 0;

    }

    public boolean modifyEmployee(Employee employee) throws SQLException {
        String sql = "UPDATE EMPLOYEE SET name = ?, last_name = ?, email = ?, " +
                "username = ?, speciality = ?, active = ?, password = SHA1(?) WHERE id_employee = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, employee.getName());
        statement.setString(2, employee.getLast_name());
        statement.setString(3, employee.getEmail());
        statement.setString(4, employee.getUsername());
        statement.setString(5, employee.getSpeciality());
        statement.setBoolean(6, employee.isActive());
        statement.setString(7, employee.getPassword());
        statement.setInt(8, employee.getId_employee());

        int affectedRows = statement.executeUpdate();

        return affectedRows != 0;
    }

}


