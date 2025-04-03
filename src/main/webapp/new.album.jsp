<jsp:include page="includes/header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setAttribute("mappServlet", "newAlbum"); %>


<body>
<jsp:include page="includes/ajax.jsp"/>
<jsp:include page="includes/navbar.jsp"/>

<% int idArtist = Integer.parseInt(request.getParameter("id_artist")); %>

<div class="container d-flex justify-content-center align-items-center min-vh-100">
    <div class="col-md-6 bg-light p-4 rounded shadow">
        <h4 class="text-center mb-3">Subir album</h4>
        <form>
            <div class="mb-2">
                <label class="form-label">Título</label>
                <input type="text" name="titulo" class="form-control" placeholder="Ingrese el título">
            </div>
            <div class="mb-2">
                   <label class="form-label">Artista</label>
                   <input type="text" name="artista" class="form-control" placeholder="Nombre del artista">
            </div>
            <div class="mb-2">
                <label class="form-label">Género</label>
                <input type="text" name="genero" class="form-control" placeholder="Ingrese el género">
            </div>
            <div class="mb-2">
                <label class="form-label">Portada</label>
                <input type="text" name="url_cover" class="form-control" placeholder="Ingrese la portada">
            </div>
            <div class="mb-2">
                <label class="form-label">ISRC</label>
                <input type="text" name="ISRC" class="form-control" placeholder="Ingrese el ISRC">
            </div>
            <div class="mb-2">
                <label class="form-label">Explícito</label>
                <select name="explicito" class="form-control">
                    <option value="no">No</option>
                    <option value="yes">Sí</option>
                </select>
            </div>
            <div class="mb-2">
                <label class="form-label">Precio ($)</label>
                <input type="number" name="precio" class="form-control" step="0.01" placeholder="Ej: 9.99">
            </div>
            <div class="mb-2">
                 <label class="form-label">Id Artista</label>
                 <input type="number" name="id_artist" value=<%=idArtist%> class="form-control">
            </div>
            <div class="mb-3">
                <label class="form-label">Descripción</label>
                <textarea class="form-control" name="descripcion" rows="3" placeholder="Escribe una descripción..."></textarea>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-primary w-100">Enviar</button>
            </div>
        </form>
    </div>
</div>
</div>
</body>

