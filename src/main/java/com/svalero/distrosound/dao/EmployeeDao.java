package com.svalero.distrosound.dao;

import com.svalero.distrosound.exception.ArtistNotFoundException;
import com.svalero.distrosound.exception.EmployeeNotFoundException;
import com.svalero.distrosound.exception.UserNotFoundException;
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
        String sql = "INSERT INTO employee (name, last_name, username, password, email) VALUES (?,?,?,SHA1(?),?)";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, employee.getName());
        statement.setString(2, employee.getLast_name());
        statement.setString(3, employee.getUsername());
        statement.setString(4, employee.getPassword());
        statement.setString(5, employee.getEmail());

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
        String sql = "SELECT * FROM employee WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet result = statement.executeQuery();
        return result.next();
    }

    public String loginEmployee(String username, String password) throws SQLException, EmployeeNotFoundException {
        String sql = "SELECT role FROM employee WHERE username = ? AND password = SHA1(?)";
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
        String sql = "SELECT id_employee FROM employee WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("id_employee");
        } else {
            return null;
        }
    }

    public ArrayList getAll(){

        return null;
    }

    //devuelve un único empleado
    public Employee get (int id){

        return null;
    }


    public void modify(){

    }

    public void delete(Employee employee){

    }

}


