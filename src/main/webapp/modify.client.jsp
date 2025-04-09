<%@ page import="com.svalero.distrosound.database.Database" %>
<%@ page import="com.svalero.distrosound.dao.ArtistDao" %>
<%@ page import="com.svalero.distrosound.model.Artist" %>
<%@ page import="java.time.LocalDate" %>
<jsp:include page="includes/header.jsp"/>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setAttribute("mappServlet", "modifyArtist"); %>

<body>
<jsp:include page="includes/ajax.data.jsp"/>
<jsp:include page="includes/navbar.jsp"/>

<% int idArtist = Integer.parseInt(request.getParameter("id_artist")); %>

<%

    try {
        Database database = new Database();
        database.connect();
        ArtistDao artistModify = new ArtistDao(database.getConnection());
        Artist artist = artistModify.getArtistById(idArtist);

        String name = artist.getName();
        String lastName = artist.getLast_name();
        String email = artist.getEmail();
        String username = artist.getUsername();
        LocalDate birthDate = artist.getBirth_date();
        boolean premium = artist.isPremium();
%>

<div class="container d-flex justify-content-center align-items-center min-vh-100">
    <div class="col-md-6 bg-light p-4 rounded shadow">
        <h4 class="text-center mb-3">Modificar datos</h4>
        <form>
            <div class="mb-2">
                <label class="form-label">Nombre</label>
                <input type="text" name="nombre" class="form-control" placeholder="" value="<%= name %>">
            </div>
            <div class="mb-2">
                <label class="form-label">Apellidos</label>
                <input type="text" name="apellidos" class="form-control" placeholder="" value="<%= lastName %>">
            </div>
            <div class="mb-2">
                <label class="form-label">Username</label>
                <input type="text" name="username" class="form-control" placeholder="" value="<%= username %>">
            </div>
            <div class="mb-2">
                <label class="form-label">Password</label>
                <input type="password" name="password" class="form-control" placeholder="">
            </div>
            <div class="mb-2">
                <label class="form-label">Email</label>
                <input type="text" name="email" class="form-control" placeholder="" value="<%= email %>">
            </div>
            <div class="mb-2">
                <label class="form-label">Fecha de nacimiento</label>
                <input type="date" name="cumpleaños" class="form-control"
                       value="<%= birthDate != null ? birthDate.toString() : "" %>">
            </div>
            <div class="mb-2">
                <label class="form-label">¿Premium?</label>
                <select name="premium" class="form-control">
                    <option value="no" <%= !premium ? "selected" : "" %>>No</option>
                    <option value="yes" <%= premium ? "selected" : "" %>>Si</option>
                </select>
            </div>
            <input type="hidden" name="id_artist" value="<%= artist.getId() %>">
            <div class="mb-2 text-center">
                <button type="submit" class="btn btn-primary">Actualizar</button>
            </div>
            <div id="result" class="alert alert-danger" role="alert" style="display: none">
                Lo sentimos. Hubo un problema al modificar el usuario.
            </div>
        </form>
    </div>
</div>

<%
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
</body>
