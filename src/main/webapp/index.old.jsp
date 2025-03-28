<%@ page import="com.svalero.distrosound.database.Database" %>
<%@ page import="com.svalero.distrosound.dao.AlbumDao" %>
<%@ page import="com.svalero.distrosound.model.Album" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="./css/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>aplicación</title>
</head>
<body>

<div class="container">
    <ul class="list-group">
        <%
            try {
                Database database = new Database();
                database.connect();

//Le doy la conexión que acabo de pillar con el contructor a AlbumDao
                AlbumDao albumDao = new AlbumDao(database.getConnection());

//creo un arralisty donde cogere la consulta de todos los albunes
                List<Album> albumList = albumDao.getAll();

//recorro el arraylist y de ahi pillo lo que quiero de cada album
                for (Album album : albumList) {
        %>
        <li class="list-group-item">Nombre----> <%= album.getTitle()%>
        </li>
        <%
            }
        } catch (ClassNotFoundException cnfe) {

        %>
        <div class="alert alert-danger" role="alert">
            Ha habido un problema en la base de datos. Intentalo de nuevo.
                <%

        } catch (SQLException sqle) {
        %>

            <div class="alert alert-danger" role="alert">
                No se ha podido conectar a la base de datos.
            </div>

                <%

        }

        %>
    </ul>
</div>
</body>
</html>