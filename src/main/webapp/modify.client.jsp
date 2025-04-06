<jsp:include page="includes/header.jsp"/>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<body>
<jsp:include page="includes/ajax.jsp"/>
<jsp:include page="includes/navbar.jsp"/>
<% request.setAttribute("mappServlet", "modify.artist"); %>


<% int idArtist = Integer.parseInt(request.getParameter("id_artist")); %>

<div class="container d-flex justify-content-center align-items-center min-vh-100">
    <div class="col-md-6 bg-light p-4 rounded shadow">
        <h4 class="text-center mb-3">Modificar datos</h4>
        <form>
            <div class="mb-2">
                <label class="form-label">Nombre</label>
                <input type="text" name="nombre" class="form-control" placeholder="">
            </div>
            <div class="mb-2">
                <label class="form-label">apellidos</label>
                <input type="text" name="apellidos" class="form-control" placeholder="">
            </div>
            <div class="mb-2">
                <label class="form-label">username</label>
                <input type="text" name="username" class="form-control" placeholder="">
            </div>
            <div class="mb-2">
                <label class="form-label">password</label>
                <input type="password" name="password" class="form-control" placeholder="">
            </div>
            <div class="mb-2">
                <label class="form-label">email</label>
                <input type="email" name="email" class="form-control" placeholder="">
            </div>

            <div id="result" class="alert alert-danger" role="alert" style="display: none">
                Lo sentimos. Hubo un problema al modificar el usuario.
            </div>
        </form>
    </div>
</div>
</div>


</body>
