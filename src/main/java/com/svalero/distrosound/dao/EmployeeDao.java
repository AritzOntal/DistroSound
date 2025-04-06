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

    public Employee getEmployeeById(int id) throws SQLException, EmployeeNotFoundException {
        String sql = "SELECT * FROM employee WHERE id_employee = ?";
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


        statement.close();

        return (employee);
    }

    public ArrayList getAll() throws SQLException {

        String sql = "SELECT * FROM employee";
        PreparedStatement statement = null;
        ResultSet result = null;
        statement = connection.prepareStatement(sql);
        result = statement.executeQuery();

        //CREAMOS ARRAYLIST (COMO OBJETO) PARA GUARDAR TODOS LOS OBJETOS CREADOS EN EL BUCLE
        ArrayList<Employee> employeeList = new ArrayList<>();

        while (result.next()) {
            //creo objeto Album para añadirle los datos de la BD
            Employee employee = new Employee();
            employee.setId_employee(result.getInt("id_employee"));
            employee.setName(result.getString("name"));
            employee.setLast_name(result.getString("last_name"));
            employee.setUsername(result.getString("username"));
            employee.setPassword(result.getString("password"));
            employee.setEmail(result.getString("email"));

            //Lo AÑADIMOS al arraylist
            employeeList.add(employee);
        }
        result.close();
        statement.close();

        return employeeList;
    }


    //devuelve un único empleado
    public Employee get (int id){
        String sql = "SELECT * FROM employee WHERE id_employee = ?";
        PreparedStatement statement = null;

        return null;
    }


    public void modify(){

    }

    public boolean deleteEmployeeById(int id) throws SQLException {

        String sql = "DELETE FROM employee WHERE id_employee = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        int affectedRows = statement.executeUpdate();

        return affectedRows != 0;

    }

    public boolean modifyEmployee(Employee employee) throws SQLException {
        String sql = "UPDATE employee SET name = ?, last_name = ?, email = ?, " +
                "username = ?, speciality = ?, distributor = ? WHERE id_employee = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, employee.getName());
        statement.setString(2, employee.getLast_name());
        statement.setString(3, employee.getEmail());
        statement.setString(4, employee.getUsername());
        statement.setString(5, employee.getSpeciality());
        statement.setBoolean(6, employee.isDistributor());
        statement.setInt(7, employee.getId_employee());

        int affectedRows = statement.executeUpdate();

        return affectedRows != 0;
    }

}


