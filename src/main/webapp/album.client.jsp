<%@ page import="com.svalero.distrosound.dao.AlbumDao" %>
<%@ page import="com.svalero.distrosound.database.Database" %>
<%@ page import="com.svalero.distrosound.model.Album" %>
<%@ page import="java.util.List" %>
<%@ page import="com.svalero.distrosound.exception.albumNotFoundException" %>
<jsp:include page="includes/header.jsp"/>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="includes/navbar.jsp"/>

<body class="bg-custom-client-zone">
<div class="col">
    <h1>Zona de Cliente</h1>
    <%
        try {

            int albumId = Integer.parseInt(request.getParameter("album_id"));
            Database database = new Database();
            database.connect();
            AlbumDao albumDao = new AlbumDao(database.getConnection());
            Album verAlbum = albumDao.getById(albumId);
            int idAlbum = verAlbum.getId_album();
    %>
    <div class="container-centered">
        <div class="card" style="width: 30rem;">
            <img src="../distrosound_images/<%=verAlbum.getImage()%>" class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title"><%=verAlbum.getTitle()%>
                </h5>
                <p class="card-text"><%=verAlbum.getDescription()%>
                </p>
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">Fecha de subida: <%=verAlbum.getUploaded()%>
                </li>
                <li class="list-group-item">Género: <%=verAlbum.getGenre()%>
                </li>
                <li class="list-group-item">Explícito: <%=verAlbum.getExplicit() ? "si" : "no"%>
                </li>
                <li class="list-group-item">ISRC: <%=verAlbum.getISRC()%>
                </li>
                <li class="list-group-item">Precio: <%= String.format("%.2f €", verAlbum.getPrice()) %></li>
            </ul>
            <div class="card-body">
                <a href="deleteAlbum?id_album=<%=idAlbum%>"
                   class="card-link"
                   onclick="return confirm('¿Estás seguro de que deseas eliminar este álbum? Esta acción no se puede deshacer.');">
                    Eliminar
                </a>
                <a href="modify.album.jsp?id_album=<%=idAlbum%>" class="card-link">Modificar</a>
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

