<%@ page import="com.svalero.distrosound.dao.AlbumDao" %>
<%@ page import="com.svalero.distrosound.database.Database" %>
<%@ page import="com.svalero.distrosound.model.Album" %>
<%@ page import="java.util.List" %>
<%@ page import="com.svalero.distrosound.exception.albumNotFoundException" %>
<!DOCTYPE html>
<html lang="es">
<jsp:include page="includes/head.jsp"/>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="includes/navbar.jsp"/>

<body>
<div class="col">
    <%
        int albumId = Integer.parseInt(request.getParameter("album_id"));
        Database database = new Database();
        database.connect();
        AlbumDao albumDao = new AlbumDao(database.getConnection());
        try {

            Album verAlbum = albumDao.getById(albumId);
    %>
    <div class="container-centered">
        <div class="card" style="width: 40rem;">
            <img src="<%=verAlbum.getUrl_cover()%>" class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title"><%=verAlbum.getTitle()%>
                </h5>
                <p class="card-text"><%=verAlbum.getDescription()%>
                </p>
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">Año lanzamiento: <%=verAlbum.getRelease_date()%>
                </li>
                <li class="list-group-item">Género: <%=verAlbum.getGenre()%>
                </li>
                <li class="list-group-item">Explícito: <%=verAlbum.isExplicit()%>
                </li>
                <li class="list-group-item">Precio: <%=verAlbum.getPrice()%> €</li>
            </ul>
            <div class="card-body">
                <a href="#" class="card-link">COMPRAR</a>
            </div>
        </div>
    </div>
</div>
</body>

    <% } catch (albumNotFoundException gnfe) {
        %>
<jsp:include page="includes/game_not_found.jsp"/>
    <%
}
%>

