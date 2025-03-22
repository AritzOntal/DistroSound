package com.svalero.distrosound;

import java.sql.*;

public class main {
    public static void main(String[] args){

        //creo un objeto de la clase Connection
        Connection conexion = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conexion = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/DISTROSOUND",
                    "ARITZONTAL", "19920912");


            //guardo la consulta que quiero
            String sql = "SELECT * FROM album";

            //objeto para laznar las consultas (dandole el nombre statement)
            PreparedStatement statement = null;

            //objeto para guardar la respuesta (dandole el nombre result)
            ResultSet result = null;

            //poniendo "null" solo los estoy cogiendo como objeto

            //utilizando "conexion" invoco "prepareStatemente(con paramentro de consulta)
            statement = conexion.prepareStatement(sql);

            result = statement.executeQuery();

            System.out.println("conexion realizada con éxito");

            while (result.next()){
                String genre = result.getString("genre");
                String title = result.getString("title");
                boolean explicit = result.getBoolean("explicit");
                String mayorEdad = explicit ? "Yes" : "No";

                System.out.println("title: " + " " + title);
                System.out.println("genre: " + " " + genre);
                System.out.println("explicit: " + " " + mayorEdad);

            }
            statement.close();

            conexion.close();
            System.out.println("conexion cerrada con éxito");

        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            System.out.println("El driver no está funcionando bien");
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("No se ha podido conectar con la base de datos");
        }

    }

}
