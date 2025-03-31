package com.svalero.distrosound.dao;

import com.svalero.distrosound.exception.ArtistNotFoundException;
import com.svalero.distrosound.exception.albumNotFoundException;
import com.svalero.distrosound.model.Album;
import com.svalero.distrosound.model.Artist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArtistDao {

    private Connection connection;

    public ArtistDao(Connection connection) {
        this.connection = connection;
    }

    public boolean add(Artist artist) throws SQLException {
        String sql = "INSERT INTO artist (name, last_name, username, password, email, role) VALUES (?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, artist.getName());
            statement.setString(2, artist.getLast_name());
            statement.setString(3, artist.getUsername());
            statement.setString(4, artist.getPassword());
            statement.setString(5, artist.getEmail());
            statement.setString(6, artist.getRole());

            int affectedRows = statement.executeUpdate();

            return affectedRows != 0;
    }

    public boolean exists(String username) throws SQLException, ArtistNotFoundException {
        String sql = "SELECT * FROM artist WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet result = statement.executeQuery();
        return result.next();
    }
}
