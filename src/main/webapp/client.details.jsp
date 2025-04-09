<%@ page import="java.util.ArrayList" %>
<%@ page import="com.svalero.distrosound.model.Artist" %>
<%@ page import="java.util.List" %>
<%@ page import="com.svalero.distrosound.database.Database" %>
<%@ page import="com.svalero.distrosound.dao.EmployeeDao" %>
<%@ page import="com.svalero.distrosound.dao.ArtistDao" %>
<%@ page import="com.svalero.distrosound.model.Employee" %>
<jsp:include page="includes/header.jsp"/>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<body>
<jsp:include page="includes/navbar.jsp"/>

<% HttpSession session1 = request.getSession(false);
    if (session1 != null && session1.getAttribute("id_employee") != null) {
        int id = Integer.parseInt(request.getParameter("id"));
%>

<div class="container">
    <div class="col">
        <div>
            <h2 class="pb-2 border-bottom">Artista</h2>
        </div>

        <%
            try {
                Database database = new Database();
                database.connect();
                ArtistDao artistDao = new ArtistDao(database.getConnection());
                Artist artist = artistDao.getArtistById(id);

        %>
        <ul class="list-group">
            <li class="list-group-item"><strong>Nombre</strong>: <%=artist.getName()%></li>
            <li class="list-group-item"><strong>Apellidos</strong>: <%=artist.getLast_name()%></li>
            <li class="list-group-item"><strong>Username</strong>: <%=artist.getUsername()%></li>
            <li class="list-group-item"><strong>Email</strong>: <%=artist.getEmail()%></li>
            <li class="list-group-item"><strong>Fecha de nacimiento:</strong>: <%=artist.getBirth_date().toString()%></li>
            <li class="list-group-item"><strong>Royalties:</strong>: <%=artist.getRoyalties()%>%</li>
            <li class="list-group-item"><strong>Premium:</strong>: <%=artist.isPremium()? "Sí" : "No"%></li>

        </ul>
        <%

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
