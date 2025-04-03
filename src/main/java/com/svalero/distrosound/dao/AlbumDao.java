package com.svalero.distrosound.dao;

import com.svalero.distrosound.exception.albumNotFoundException;
import com.svalero.distrosound.model.Album;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.concurrent.ExecutionException;

public class AlbumDao {

    //me creo un atributo connection para el constructor de abajo
    private Connection connection;

    //hago constructor para pasarme el connection de "Database"
    //para pilarle la conexion rollo alrevés y guardarla en el atributo
    public AlbumDao(Connection connection) {
        this.connection = connection;
    }

    public boolean add(Album album) throws SQLException {
        String sql = "INSERT INTO album (title, artist, genre, ISRC, description, explicit, price, url_cover, uploaded, id_artist) VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = statement = connection.prepareStatement(sql);

        statement.setString(1, album.getTitle());
        statement.setString(2, album.getArtist());
        statement.setString(3, album.getGenre());
        statement.setString(4, album.getISRC());
        statement.setString(5, album.getDescription());
        statement.setBoolean(6, album.getExplicit());
        statement.setFloat(7, album.getPrice());
        statement.setString(8, album.getUrl_cover());
        statement.setDate(9, java.sql.Date.valueOf(album.getUploaded()));
        statement.setInt(10, album.getId_artist());

        //UPDATE, INSERT, DELETE SE EJEVUTAN CON ESTE
        int affectedRows = statement.executeUpdate();
        //execute.query PARA LAS CONSULTAS SELECT

        //DEVUELVO BOOLEANO PARA SABER SI AFECTA A LAS ROWS O NO
        return affectedRows != 0;

    }

    public ArrayList getAll() throws SQLException {

        String sql = "SELECT * FROM album";
        PreparedStatement statement = null;
        ResultSet result = null;
        statement = connection.prepareStatement(sql);

        result = statement.executeQuery();

        //CREAMOS ARRAYLIST PARA GUARDAR TODOS LOS OBJETOS CREADOS EN EL BUCLE
        ArrayList<Album> albumList = new ArrayList<>();
        while (result.next()) {

            //creo objeto Album para añadirle los datos de la BD
            Album album = new Album();

            album.setId_album(result.getInt("id_album"));
            album.setTitle(result.getString("title"));
            album.setExplicit(result.getBoolean("explicit"));
            album.setDuration(result.getFloat("duration"));
            album.setArtist(result.getString("artist"));
            album.setGenre(result.getString("genre"));
            album.setUrl_cover(result.getString("url_cover"));
            album.setId_artist(result.getInt("id_artist"));
            album.setId_employee(result.getInt("id_employee"));
            album.setISRC(result.getString("ISRC"));
            album.setRelease_date(result.getDate("release_date"));

            //Lo AÑADIMOS al arraylist
            albumList.add(album);
        }

        statement.close();

        return albumList;
    }

    //devuelve TODOS los albunes que ha subido un cliente
    public ArrayList get(int id) throws SQLException {
        String sql = "SELECT * FROM album WHERE id_artist = ?";
        PreparedStatement statement = null;
        ResultSet result = null;
        statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        result = statement.executeQuery();

        ArrayList<Album> albumListById = new ArrayList<>();

        while (result.next()) {

            Album album = new Album();

            album.setId_album(result.getInt("id_album"));
            album.setTitle(result.getString("title"));
            album.setExplicit(result.getBoolean("explicit"));
            album.setDuration(result.getFloat("duration"));
            album.setArtist(result.getString("artist"));
            album.setGenre(result.getString("genre"));
            album.setUrl_cover(result.getString("url_cover"));
            album.setId_artist(result.getInt("id_artist"));
            album.setId_employee(result.getInt("id_employee"));
            album.setISRC(result.getString("ISRC"));
            album.setRelease_date(result.getDate("release_date"));

            albumListById.add(album);
        }
        statement.close();

        return albumListById;
    }

    //Devuelve un album por ID artista
    public Album getById(int id) throws SQLException, albumNotFoundException {

        String sql = "SELECT * FROM album WHERE id_artist = ?";
        PreparedStatement statement = null;
        ResultSet result = null;
        statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        result = statement.executeQuery();
        if (!result.next()) {
            throw new albumNotFoundException();
        }
            Album album = new Album();
            album.setId_album(result.getInt("id_album"));
            album.setTitle(result.getString("title"));
            album.setExplicit(result.getBoolean("explicit"));
            album.setDuration(result.getFloat("duration"));
            album.setArtist(result.getString("artist"));
            album.setGenre(result.getString("genre"));
            album.setUrl_cover(result.getString("url_cover"));
            album.setId_artist(result.getInt("id_artist"));
            album.setId_employee(result.getInt("id_employee"));
            album.setISRC(result.getString("ISRC"));
            album.setRelease_date(result.getDate("release_date"));
            album.setDescription(result.getString("description"));
            album.setDuration(result.getFloat("price"));

            statement.close();

            return (album);
    }

    public void modify() {

    }


    public boolean delete(String name) throws SQLException {
        //CON PASARLE EL NOMBRE COMO PARAMETRO NOS VALE (NO EL OBJETO ENTERO)
        String sql = "DELETE FROM games WHERE title = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);

        int affectedRows = statement.executeUpdate();

        //SI HAY INEAS AFECTADAS, DE VOLVERA TRUE PORQUE ES DISTINDO QUE 0.
        return affectedRows != 0;

    }
}
