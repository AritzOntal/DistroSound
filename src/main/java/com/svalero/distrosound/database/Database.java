package com.svalero.distrosound.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private Connection connection;

    //LANZO LAS EXCEPECIONES FUERA CON THROWS PARA HACER ALGO CON ELLAS
    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/DISTROSOUND", "ARITZONTAL", "19920912");
    }

    public void close() throws SQLException{
        connection.close();
    }
    //dar acceso a la conexion realizada aqui para los demás packages
    public Connection getConnection() {
        return connection;
    }
}


