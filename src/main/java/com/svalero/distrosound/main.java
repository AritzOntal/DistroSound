package com.svalero.distrosound;

import com.svalero.distrosound.dao.AlbumDao;
import com.svalero.distrosound.database.Database;
import com.svalero.distrosound.model.Album;

import java.sql.*;
import java.util.ArrayList;

public class main {
    public static void main(String[] args){

        //creo un objeto de la clase Database

        Database database = new Database();

        try {
            database.connect();

            //con esto consigo darle acceso al dao de la conexion (como un puente)
            AlbumDao albumDao = new AlbumDao(database.getConnection());

            //CREO UN ARRAYLIST PARA GUARDAR RESPUESAT DEL GET ALL QUE ES UN ARRAY
            ArrayList<Album> albumList = albumDao.getAll();

            //objeto donde meteremos nuestro juego nuevo con el formulario
            Album newAlbum = new Album();

            String title = "OK Computer";
            String ISRC = "349204859624";
            int idCLient = 1;

            //seteamos nuestro modelo de album para llenar valores del objeto
            newAlbum.setTitle(title);
            newAlbum.setISRC(ISRC);
            newAlbum.setId_client(1);

            //se lo pasamos al dao para utilizarlo en INSERT
            albumDao.add(newAlbum);


            //RECORREMOS EL ARRAYLIST Y COGEMOS CON GET. LO QUE NOS INTERESE DE EL
            for (Album album : albumList) {
                System.out.println(album.getArtist());
            }

            database.close();

        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            System.out.println("El driver no está funcionando bien");
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("No se ha podido conectar con la base de datos");
        }

    }

}
