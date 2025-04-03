<%@ page import="com.svalero.distrosound.database.Database" %>
<%@ page import="com.svalero.distrosound.dao.AlbumDao" %>
<%@ page import="com.svalero.distrosound.model.Album" %>
<%@ page import="java.util.List" %>
<%@ page import="com.svalero.distrosound.model.Artist" %>
<jsp:include page="includes/header.jsp"/>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<body>

<% HttpSession session1 = request.getSession(false);
if (session1 != null && session1.getAttribute("id_artist") != null) {
int idArtist = (int) session1.getAttribute("id_artist");

%>

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
                    <a href="new.album.jsp?id_artist=<%=idArtist%>" class="icon-link">
                        SUBIR
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
                    Database database = new Database();
                    database.connect();
                    AlbumDao albumDao = new AlbumDao(database.getConnection());
                    //TODO RECOGER EL ID DEL ARTISTA PARA VER SUS ALBUNES
                    List<Album> albumListById = albumDao.get(idArtist);
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
                                    <a href="album.client.jsp?album_id=<%=album.getId_album()%>" type="button"
                                       class="btn btn-sm btn-outline-primary">Ver detalles</a>
                                    <a href="album.client.jsp?album_id=<%=album.getId_album()%>"
                                       class="btn btn-sm btn-outline-warning">Editar</a>
                                    <a href="album.client.jsp?album_id=<%=album.getId_album()%>"
                                       class="btn btn-sm btn-outline-success">Solicitar Lanzamineto</a>
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
<%
    } else {

        response.sendRedirect("login.jsp");

    }

%>

</body>