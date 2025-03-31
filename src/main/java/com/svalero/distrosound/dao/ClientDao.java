package com.svalero.distrosound.dao;

import com.svalero.distrosound.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientDao {

    private Connection connection;

    public ClientDao(Connection connection) {
        this.connection = connection;
    }

    public void add(){
    }

    public boolean loginClient(String username, String password) throws SQLException {
        String sql = "SELECT * FROM client WHERE username = ? AND password = SHA1(?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet result = statement.executeQuery();
        if (!result.next()){
            return false;
        }
        return true;
    }

    public ArrayList getAll(){

        return null;
    }

    //devuelve un único cliente
    public Client get (int id){

        return null;
    }


    public void modify(){

    }

    public void delete(Client client){

    }
}
