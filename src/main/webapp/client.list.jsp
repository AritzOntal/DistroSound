<%@ page import="java.util.ArrayList" %>
<%@ page import="com.svalero.distrosound.model.Artist" %>
<%@ page import="java.util.List" %>
<%@ page import="com.svalero.distrosound.database.Database" %>
<%@ page import="com.svalero.distrosound.dao.EmployeeDao" %>
<%@ page import="com.svalero.distrosound.dao.ArtistDao" %>
<jsp:include page="includes/header.jsp"/>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<body>
<jsp:include page="includes/navbar.jsp"/>

<% HttpSession session1 = request.getSession(false);
    if (session1 != null && session1.getAttribute("id_employee") != null) {

%>
<div class="container">
    <div class="col">
        <jsp:include page="includes/search.jsp"/>
        <div>
            <h2 class="pb-2 border-bottomx">Lista de artistas</h2>
        </div>

        <%
            try {
                Database database = new Database();
                database.connect();
                ArtistDao albumDao = new ArtistDao(database.getConnection());
                String query = request.getParameter("search");

                List<Artist> artistList;

                if (query != null && !query.trim().isEmpty()) {
                    artistList = albumDao.getAllArtist(query);
                } else {
                    artistList = albumDao.getAll();
                }

                for (Artist artist : artistList) {

        %>
        <ul class="list-group">
            <li class="list-group-item d-flex justify-content-between align-items-center">
                <span><%=artist.getLast_name()%>, <%=artist.getName()%></span>
                <a href="client.details.jsp?id=<%=artist.getId()%>" class="btn btn-info btn-sm">Ver</a>
            </li>
        </ul>
        <%
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>
    </div>

    <%
        } else {
            response.sendRedirect("login.jsp");
        }
    %>

</div>
</body>