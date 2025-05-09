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
        String sql = "INSERT INTO ALBUM (title, artist, genre, ISRC, description, explicit, price, url_cover, uploaded, id_artist) VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = statement = connection.prepareStatement(sql);

        statement.setString(1, album.getTitle());
        statement.setString(2, album.getArtist());
        statement.setString(3, album.getGenre());
        statement.setString(4, album.getISRC());
        statement.setString(5, album.getDescription());
        statement.setBoolean(6, album.getExplicit());
        statement.setFloat(7, album.getPrice());
        statement.setString(8, album.getImage());
        statement.setString(9, album.getUploaded());
        statement.setInt(10, album.getId_artist());

        //UPDATE, INSERT, DELETE SE EJEVUTAN CON ESTE
        int affectedRows = statement.executeUpdate();
        //execute.query PARA LAS CONSULTAS SELECT

        //DEVUELVO BOOLEANO PARA SABER SI AFECTA A LAS ROWS O NO
        return affectedRows != 0;

    }
    public ArrayList getAll() throws SQLException {
        String sql = "SELECT * FROM ALBUM";
        return launchQuery(sql);
    }

    public ArrayList getAllAlbums(String search) throws SQLException {
        String sql = "SELECT * FROM ALBUM WHERE title LIKE ? OR artist LIKE ?";
        return launchQuery(sql, search);
    }

    private ArrayList<Album> launchQuery(String query, String ...search) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);

        if(search.length > 0){
            statement.setString(1, "%" + search[0]  + "%");
            statement.setString(2, "%" + search[0]  + "%");

        }
        ResultSet result = statement.executeQuery();
        ArrayList<Album> albumList = new ArrayList<>();
        while (result.next()) {
            Album album = new Album();
            album.setId_album(result.getInt("id_album"));
            album.setTitle(result.getString("title"));
            album.setExplicit(result.getBoolean("explicit"));
            album.setArtist(result.getString("artist"));
            album.setGenre(result.getString("genre"));
            album.setImage(result.getString("url_cover"));
            album.setId_artist(result.getInt("id_artist"));
            album.setRelease_date(result.getDate("release_date"));
            albumList.add(album);
        }
        result.close();
        statement.close();
        return albumList;
    }

    //devuelve TODOS los albunes que ha subido un cliente
    public ArrayList get(int id) throws SQLException {
        String sql = "SELECT * FROM ALBUM WHERE id_artist = ?";
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
            album.setArtist(result.getString("artist"));
            album.setGenre(result.getString("genre"));
            album.setImage(result.getString("url_cover"));
            album.setId_artist(result.getInt("id_artist"));
            album.setISRC(result.getString("ISRC"));
            album.setRelease_date(result.getDate("release_date"));

            albumListById.add(album);
        }
        statement.close();

        return albumListById;
    }

    //Devuelve un album por ID artista
    public Album getById(int id) throws SQLException, albumNotFoundException {

        String sql = "SELECT * FROM ALBUM WHERE id_album = ?";
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
            album.setArtist(result.getString("artist"));
            album.setGenre(result.getString("genre"));
            album.setImage(result.getString("url_cover"));
            album.setId_artist(result.getInt("id_artist"));
            album.setISRC(result.getString("ISRC"));
            album.setRelease_date(result.getDate("release_date"));
            album.setDescription(result.getString("description"));
            album.setPrice(result.getFloat("price"));
            album.setUploaded(result.getString("uploaded"));

            statement.close();

            return (album);
    }

    public void modify() {

    }


    public boolean deleteAlbumById(int id) throws SQLException {

        String sql = "DELETE FROM ALBUM WHERE id_album = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        int affectedRows = statement.executeUpdate();
        //SI HAY INEAS AFECTADAS, DE VOLVERA TRUE PORQUE ES DISTINDO QUE 0.
        return affectedRows != 0;
    }

    public boolean modifyAlbum(Album album) throws SQLException {
        String sql = "UPDATE ALBUM SET title = ?, artist = ?, genre = ?, ISRC = ?, " +
                "description = ?, explicit = ?, price = ? WHERE id_album = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, album.getTitle());
        statement.setString(2, album.getArtist());
        statement.setString(3, album.getGenre());
        statement.setString(4, album.getISRC());
        statement.setString(5, album.getDescription());
        statement.setBoolean(6, album.getExplicit());
        statement.setFloat(7, album.getPrice());
        statement.setInt(8, album.getId_album());
        int affectedRows = statement.executeUpdate();

        return affectedRows != 0;
    }
}
