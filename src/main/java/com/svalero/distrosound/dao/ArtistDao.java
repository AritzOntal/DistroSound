package com.svalero.distrosound.dao;

import com.svalero.distrosound.exception.ArtistNotFoundException;
import com.svalero.distrosound.exception.ArtistNotFoundException;
import com.svalero.distrosound.model.Album;
import com.svalero.distrosound.model.Artist;

import java.sql.*;
import java.util.ArrayList;

public class ArtistDao {

    private Connection connection;

    public ArtistDao(Connection connection) {
        this.connection = connection;
    }

    public boolean add(Artist artist) throws SQLException {
        String sql = "INSERT INTO artist (name, last_name, username, password, email, birth_date) VALUES (?,?,?,SHA1(?),?,?)";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, artist.getName());
        statement.setString(2, artist.getLast_name());
        statement.setString(3, artist.getUsername());
        statement.setString(4, artist.getPassword());
        statement.setString(5, artist.getEmail());
        statement.setDate(6, java.sql.Date.valueOf(artist.getBirth_date()));

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

        if (!result.next()) {
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

    public Artist getArtistById(int id) throws SQLException, ArtistNotFoundException {

        String sql = "SELECT * FROM artist WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet result = null;
        statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        result = statement.executeQuery();
        if (!result.next()) {
            throw new ArtistNotFoundException();
        }

        Artist artist = new Artist();
        artist.setId(id);
        artist.setName(result.getString("name"));
        artist.setLast_name(result.getString("last_name"));
        artist.setUsername(result.getString("username"));
        artist.setPassword(result.getString("password"));
        artist.setEmail(result.getString("email"));

        Date birthDate = result.getDate("birth_date");
        if (birthDate != null) {
            artist.setBirth_date(birthDate.toLocalDate());
        } else {
            artist.setBirth_date(null);
        }
        return artist;
    }

    public ArrayList getAll() throws SQLException {
        String sql = "SELECT * FROM artist";
        return launchQuery(sql);
    }

    public ArrayList getAllArtist(String search) throws SQLException {
        String sql = "SELECT * FROM artist WHERE name LIKE ? OR last_name LIKE ?";
        return launchQuery(sql, search);
    }

    private ArrayList<Artist> launchQuery(String query, String ...search) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);

        if(search.length > 0){
            statement.setString(1, "%" + search[0]  + "%");
            statement.setString(2, "%" + search[0]  + "%");

        }

        ResultSet result = statement.executeQuery();
        ArrayList<Artist> artistList = new ArrayList<>();

        while (result.next()) {
            //creo objeto Album para añadirle los datos de la BD
            Artist artist = new Artist();
            artist.setId(result.getInt("id"));
            artist.setName(result.getString("name"));
            artist.setLast_name(result.getString("last_name"));
            artist.setUsername(result.getString("username"));
            artist.setPassword(result.getString("password"));
            artist.setEmail(result.getString("email"));

            //Lo AÑADIMOS al arraylist
            artistList.add(artist);
        }
        result.close();
        statement.close();

        return artistList;
    }

    public boolean deleteArtistById(int id) throws SQLException {

        String sql = "DELETE FROM artist WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        int affectedRows = statement.executeUpdate();

        return affectedRows != 0;

    }

    public boolean modifyArtist(Artist artist) throws SQLException {
        String sql = "UPDATE artist SET name = ?, last_name = ?, username = ?, email = ?, " +
                "premium = ?, royalties = ?, birth_date = ?, password = SHA1(?) WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, artist.getName());
        statement.setString(2, artist.getLast_name());
        statement.setString(3, artist.getUsername());
        statement.setString(4, artist.getEmail());
        statement.setBoolean(5, artist.isPremium());
        statement.setFloat(6, artist.getRoyalties());
        statement.setDate(7, java.sql.Date.valueOf(artist.getBirth_date()));
        statement.setString(8, artist.getPassword());
        statement.setInt(9, artist.getId());

        int affectedRows = statement.executeUpdate();

        return affectedRows != 0;
    }
}
