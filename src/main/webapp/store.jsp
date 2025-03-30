<%@ page import="javax.xml.crypto.Data" %>
<%@ page import="com.svalero.distrosound.database.Database" %>
<%@ page import="com.svalero.distrosound.model.Album" %>
<%@ page import="com.svalero.distrosound.dao.AlbumDao" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>DistroSound</title>
    <link rel="stylesheet" href="./css/styless.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>

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
                    <img src="<%= album.getUrl_cover() %>" alt="Imagen desde BD">
                    <div class="card-body">
                        <h3 class="card-text"><%=album.getTitle()%>
                        </h3>
                        <p class="card-text"><%=album.getArtist()%>
                        </p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-outline-primary">Ver</button>
                                <button type="button" class="btn btn-sm btn-outline-success">Comprar</button>
                            </div>
                            <small class="text-body-secondary"> 9,99 €</small>
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
