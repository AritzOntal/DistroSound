package com.svalero.distrosound.dao;

import com.svalero.distrosound.exception.ArtistNotFoundException;
import com.svalero.distrosound.model.Artist;

import java.sql.*;

public class ArtistDao {

    private Connection connection;

    public ArtistDao(Connection connection) {
        this.connection = connection;
    }

    public boolean add(Artist artist) throws SQLException {
        String sql = "INSERT INTO artist (name, last_name, username, password, email) VALUES (?,?,?,SHA1(?),?)";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, artist.getName());
            statement.setString(2, artist.getLast_name());
            statement.setString(3, artist.getUsername());
            statement.setString(4, artist.getPassword());
            statement.setString(5, artist.getEmail());

            int affectedRows = statement.executeUpdate();
        if (affectedRows > 0) {
            // Recuperamos el ID generado automáticamente
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Asignamos el ID generado al artista como un Integer
                    int idArtist = generatedKeys.getInt(1);  // Obtenemos el ID como int
                    artist.setId(Integer.valueOf(idArtist)); // Convertimos el id a Integer y lo asignamos
                }
            }
        }
        return affectedRows > 0;
    }

    public boolean exists(String username) throws SQLException, ArtistNotFoundException {
        String sql = "SELECT * FROM artist WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet result = statement.executeQuery();
        return result.next();
    }

    public String loginArtist(String username, String password) throws SQLException, ArtistNotFoundException {
        String sql = "SELECT role FROM artist WHERE username = ? AND password = SHA1(?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet result = statement.executeQuery();

        if (!result.next()){
            throw new ArtistNotFoundException();
        }
        return result.getString("role");
    }

    public Integer getArtistIdByUsername(String username) throws SQLException {
        String sql = "SELECT id FROM artist WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("id");
        } else {
            return null;
        }
    }

    public boolean deleteArtistById(int id) throws SQLException {

        String sql = "DELETE FROM artist WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        int affectedRows = statement.executeUpdate();

        return affectedRows != 0;

        }
}
