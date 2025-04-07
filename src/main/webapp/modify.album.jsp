<%@ page import="java.sql.SQLException" %>
<%@ page import="com.svalero.distrosound.database.Database" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.svalero.distrosound.dao.AlbumDao" %>
<%@ page import="com.svalero.distrosound.model.Album" %>
<%@ page import="static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.title" %>
<jsp:include page="includes/header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setAttribute("mappServlet", "modifyAlbum"); %>

<body>
<jsp:include page="includes/ajax.data.jsp"/>
<jsp:include page="includes/navbar.jsp"/>

<% int idAlbum = Integer.parseInt(request.getParameter("id_album")); %>

<%


    try {
        Database database = new Database();
        database.connect();
        AlbumDao albumModify = new AlbumDao(database.getConnection());
        Album album = albumModify.getById(idAlbum);

        String title = album.getTitle();
        String artist = album.getArtist();
        String genre = album.getGenre();
        String cover = album.getImage();
        String ISRC = album.getISRC();
        boolean explicit = album.getExplicit();
        Float price = album.getPrice();
        String description = album.getDescription();
        int id = album.getId_album();

%>

<div class="container d-flex justify-content-center align-items-center min-vh-100">
    <div class="col-md-6 bg-light p-4 rounded shadow">
        <h4 class="text-center mb-3">Editar álbum</h4>
        <form method="POST" action="yourServletHere">
            <div class="mb-2">
                <label class="form-label">Título</label>
                <input type="text" name="titulo" class="form-control" placeholder="Ingrese el título"
                       value="<%= title != null ? title : "" %>">
            </div>
            <div class="mb-2">
                <label class="form-label">Artista</label>
                <input type="text" name="artista" class="form-control" placeholder="Ingrese el artista"
                       value="<%= artist != null ? artist : "" %>">
            </div>
            <div class="mb-2">
                <label class="form-label">Género</label>
                <input type="text" name="genero" class="form-control" placeholder="Ingrese el género"
                       value="<%= genre != null ? genre : "" %>">
            </div>
            <div class="mb-2">
                <label class="form-label">ISRC</label>
                <input type="text" name="ISRC" class="form-control" placeholder="Ingrese el código ISRC"
                       value="<%= ISRC != null ? ISRC : "" %>">
            </div>
            <div class="mb-2">
                <label class="form-label">Precio</label>
                <input type="text" name="precio" class="form-control" placeholder="Ingrese el precio"
                       value="<%= price != null ? price.toString() : "" %>">
            </div>
            <div class="mb-2">
                <label class="form-label">Descripción</label>
                <textarea name="descripcion" class="form-control"
                          placeholder="Ingrese la descripción"><%= description != null ? description : "" %></textarea>
            </div>
            <div class="mb-2">
                <label class="form-label">¿Es explícito?</label>
                <input type="checkbox" name="explicit" class="form-check-input"
                    <%= explicit ? "checked" : "" %>>
            </div>
            <input type="hidden" name="id_album" value="<%= album.getId_album() %>">
            <div class="mb-2 text-center">
                <button type="submit" class="btn btn-primary">Actualizar</button>
            </div>
        </form>
    </div>

        <%
    }catch (Exception e) {
    e.printStackTrace();
    }
%>
</body>
