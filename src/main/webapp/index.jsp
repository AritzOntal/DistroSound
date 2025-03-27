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
    <title>aplicación</title>
</head>
<body>
<h1>
    MI PRIMERA APLICACION WEB
</h1>
<ul>
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
    <li>Nombre----> <%= album.getTitle()%></li>
<%
}
        } catch (ClassNotFoundException cnfe) {

        } catch (SQLException sqle) {

        }
    %>
</ul>
</body>
</html>