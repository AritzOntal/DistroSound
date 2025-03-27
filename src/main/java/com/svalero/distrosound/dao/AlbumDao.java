package com.svalero.distrosound.dao;

import com.svalero.distrosound.model.Album;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.concurrent.ExecutionException;

public class AlbumDao {

    //me creo un atributo connection para el constructor de abajo
    private Connection connection;

    //hago constructor para pasarme el connection de "Database"
    //para pilarle la conexion rollo alrevés y guardarla en el atributo

    public AlbumDao (Connection connection){
        this.connection = connection;
    }

    public boolean add(Album album) throws SQLException{
        String sql = "INSERT INTO album (title, id_client, ISRC) VALUES (?,?,?)";
        //ponemos null para no tener que darle ningu paramentro
        PreparedStatement statement = null;
        //ahora le damos el parametro con connection y nuestra variable de consulta para prepararlo y darle parametros.
        statement = connection.prepareStatement(sql);

        statement.setString(1, album.getTitle());
        statement.setInt(2, album.getId_client());
        statement.setString(3, album.getISRC());


        //UPDATE, INSERT, DELETE SE EJEVUTAN CON ESTE


        int affectedRows = statement.executeUpdate();
        //execute.query PARA LAS CONSULTAS SELECT

        //DEVUELVO BOOLEANO PARA SABER SI AFECTA A LAS ROWS O NO
        return affectedRows != 0;

    }

    public ArrayList getAll()throws SQLException {

        String sql = "SELECT * FROM album";
        PreparedStatement statement = null;
        ResultSet result = null;
        statement = connection.prepareStatement(sql);

        result = statement.executeQuery();

        System.out.println("conexion realizada con éxito");

        //CREAMOS ARRAYLIST PARA GUARDAR TODOS LOS OBJETOS CREADOS EN EL BUCLE
        ArrayList<Album> albumList = new ArrayList<>();
        while (result.next()){

            //creo objeto Album para añadirle los datos de la BD
            Album album = new Album();

            album.setId_album(result.getInt("id_album"));
            album.setTitle(result.getString("title"));
            album.setExplicit(result.getBoolean("explicit"));
            album.setDuration(result.getFloat("duration"));
            album.setArtist(result.getString("artist"));
            album.setGenre(result.getString("genre"));
            album.setUrl_cover(result.getString("url_cover"));
            album.setId_client(result.getInt("id_client"));
            album.setId_employee(result.getInt("id_employee"));
            album.setISRC(result.getString("ISRC"));
            album.setRelease_date(result.getDate("release_date"));

            //Lo AÑADIMOS al arraylist
            albumList.add(album);
        }

        statement.close();

        return albumList;
    }

    //devuelve un único juego
    public Album get (int id){

        return null;
    }


    public void modify(){

    }


    public boolean delete(String name) throws SQLException {
        //CON PASARLE EL NOMBRE COMO PARAMETRO NOS VALE (NO EL OBJETO ENTERO)
        String sql = "DELETE FROM games WHERE title = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);

        int affectedRows = statement.executeUpdate();

        //SI HAY INEAS AFECTADAS, DE VOLVERA TRUE PORQUE ES DISTINDO QUE 0.
        return  affectedRows != 0;

    }
}
