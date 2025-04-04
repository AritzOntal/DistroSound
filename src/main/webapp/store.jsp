<%@ page import="javax.xml.crypto.Data" %>
<%@ page import="com.svalero.distrosound.database.Database" %>
<%@ page import="com.svalero.distrosound.model.Album" %>
<%@ page import="com.svalero.distrosound.dao.AlbumDao" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.List" %>
<jsp:include page="includes/header.jsp"/>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<body>
<jsp:include page="includes/navbar.jsp"/>
<div class="bg-custom">
    <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <%
                Database database = new Database();
                database.connect();
                AlbumDao albumDao = new AlbumDao(database.getConnection());
                List<Album> albumList = albumDao.getAll();
                for (Album album : albumList) {
            %>
            <div class="col">
                <div class="card shadow-sm">
                    <img src="../distrosound_images/<%= album.getImage() %>" alt="Imagen desde BD">
                    <div class="card-body">
                        <h3 class="card-text"><%=album.getTitle()%>
                        </h3>
                        <p class="card-text"><%=album.getArtist()%>
                        </p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <a href="album.jsp?album_id=<%=album.getId_album()%>" type="button"
                                   class="btn btn-sm btn-outline-primary">Ver</a>
                                <a href="album.jsp?album_id=<%=album.getId_album()%>" type="button"
                                   class="btn btn-sm btn-outline-success">Comprar</a>
                            </div>
                            <small class="text-body-secondary"><%=album.getPrice()%> €</small>
                        </div>
                    </div>
                </div>
            </div>
            <%
                }
            %>
        </div>
    </div>
</div>
</body>
