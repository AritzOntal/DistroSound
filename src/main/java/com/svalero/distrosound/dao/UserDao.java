package com.svalero.distrosound.dao;

import com.svalero.distrosound.exception.UserNotFoundException;
import com.svalero.distrosound.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {

    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public void add(){
    }

    public String loginUser(String username, String password) throws SQLException, UserNotFoundException {
        String sql = "SELECT role FROM user WHERE username = ? AND password = SHA1(?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet result = statement.executeQuery();
        if (!result.next()){
            throw new UserNotFoundException();
        }
        return result.getString("role");
    }

    public ArrayList getAll(){

        return null;
    }

    //devuelve un único cliente
    public User get (int id){
        return null;
    }


    public void modify(){

    }

    public void delete(User client){

    }
}
