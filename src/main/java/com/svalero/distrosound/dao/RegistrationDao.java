package com.svalero.distrosound.dao;
import java.sql.Types;
import java.time.LocalDate;

import com.svalero.distrosound.model.Artist;
import com.svalero.distrosound.model.Registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegistrationDao {
    Connection connection;

    public RegistrationDao(Connection connection) {
        this.connection = connection;
    }

    public boolean add(Registration registration) throws SQLException {
        String sql = "INSERT INTO REGISTRATION (id_employee, id_artist, regist_date, cost, premium, type) VALUES (?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setObject(1, registration.getId_employee(), Types.INTEGER);
        statement.setObject(2, registration.getId_artist(), Types.INTEGER);
        statement.setString(3, registration.getRegist_date().toString());
        statement.setFloat(4, registration.getCost());
        statement.setBoolean(5, registration.isPremium());
        statement.setString(6, registration.getTipe());

        int affectedRows = statement.executeUpdate();

        return affectedRows != 0;
    }

    public ArrayList getAll(){

        return null;
    }

    //devuelve un único regristro
    public Registration get (int id){

        return null;
    }


    public void modify(){

    }

    public void delete(Registration registration){

    }

}
