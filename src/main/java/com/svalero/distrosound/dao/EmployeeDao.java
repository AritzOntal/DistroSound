package com.svalero.distrosound.dao;

import com.svalero.distrosound.exception.ArtistNotFoundException;
import com.svalero.distrosound.exception.EmployeeNotFoundException;
import com.svalero.distrosound.exception.UserNotFoundException;
import com.svalero.distrosound.model.Artist;
import com.svalero.distrosound.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDao {

    private Connection connection;

    public EmployeeDao(Connection connection) {
        this.connection = connection;

    }

    public boolean add(Employee employee) throws SQLException {
        String sql = "INSERT INTO employee (name, last_name, username, password, email) VALUES (?,?,?,SHA1(?),?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, employee.getName());
        statement.setString(2, employee.getLast_name());
        statement.setString(3, employee.getUsername());
        statement.setString(4, employee.getPassword());
        statement.setString(5, employee.getEmail());

        int affectedRows = statement.executeUpdate();

        return affectedRows != 0;
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


