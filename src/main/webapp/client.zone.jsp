<%@ page import="com.svalero.distrosound.database.Database" %>
<%@ page import="com.svalero.distrosound.dao.AlbumDao" %>
<%@ page import="com.svalero.distrosound.model.Album" %>
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


<div class="bg-custom-client-zone">
    <div class="container px-4 py-5 " id="featured-3">
        <div class="row g-4 py-5 row-cols-1 row-cols-lg-3">
            <div class="feature col">
                <div class="bg-white p-4 rounded shadow-lg">
                    <div class="feature-icon d-inline-flex align-items-center justify-content-center text-bg-primary bg-gradient fs-2 mb-3">
                    </div>
                    <h3 class="fs-2 text-body-emphasis">Subir Album</h3>
                    <p>Sube tus discos aquí para solicitar después un lanzamiento a tu distribuidor.</p>
                    <a href="#" class="icon-link">
                        SUBIR
                    </a>
                </div>
            </div>
            <div class="feature col">
                <div class="bg-white p-4 rounded shadow-lg">
                    <div class="feature-icon d-inline-flex align-items-center justify-content-center text-bg-primary bg-gradient fs-2 mb-3">
                    </div>
                    <h3 class="fs-2 text-body-emphasis">Solicitar lanzamiento</h3>
                    <p>Lanza tu álbum en todas las plataformas digitales (Spotify, Apple Music, TIDAL... etc).</p>
                    <a href="#" class="icon-link">
                        LANZAR
                    </a>
                </div>
            </div>
        </div>
        <div>
            <h2 class="pb-2 border-bottomx">Pendientes de lanzamiento</h2>
        </div>
        <div class="container">

            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                <%
                    int idClient;
                    Database database = new Database();
                    database.connect();
                    AlbumDao albumDao = new AlbumDao(database.getConnection());
                    List<Album> albumListById = albumDao.get(2);
                    for (Album album : albumListById) {
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
                                    <button type="button" class="btn btn-sm btn-outline-warning">Editar</button>
                                    <button type="button" class="btn btn-sm btn-outline-success">Solicitar Lanzamineto
                                    </button>
                                </div>
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
</div>

</body>