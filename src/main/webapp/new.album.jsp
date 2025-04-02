<jsp:include page="includes/header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setAttribute("mappServlet", "newAlbum"); %>
<jsp:include page="includes/ajax.jsp"/>


<body>
<jsp:include page="includes/navbar.jsp"/>

<div class="container d-flex justify-content-center align-items-center min-vh-100">
    <div class="col-md-6 bg-light p-4 rounded shadow">
        <h4 class="text-center mb-3">Subir album</h4>
        <form>
            <div class="mb-2">
                <label class="form-label">URL</label>
                <input type="text" name="url" class="form-control" placeholder="Ingrese la URL">
            </div>
            <div class="mb-2">
                <label class="form-label">ISRC</label>
                <input type="text" name="ISRC" class="form-control" placeholder="Ingrese el ISRC">
            </div>
            <div class="mb-2">
                <label class="form-label">Título</label>
                <input type="text" name="titulo" class="form-control" placeholder="Ingrese el título">
            </div>
            <div class="mb-2">
                <label class="form-label">Género</label>
                <input type="text" name="genre" class="form-control" placeholder="Ingrese el género">
            </div>
            <div class="mb-2">
                <label class="form-label">Artista</label>
                <input type="text" name="artist" class="form-control" placeholder="Nombre del artista">
            </div>
            <div class="mb-2">
                <label class="form-label">Explícito</label>
                <select name="explicito" class="form-control">
                    <option value="no">No</option>
                    <option value="yes">Sí</option>
                </select>
            </div>
            <div class="mb-2">
                <label class="form-label">Fecha de Lanzamiento</label>
                <input type="date" name="fechaLanzamiento" class="form-control">
            </div>
            <div class="mb-2">
                <label class="form-label">Duración (min)</label>
                <input type="text" name="duracion" class="form-control" placeholder="Ej: 3:45">
            </div>
            <div class="mb-2">
                <label class="form-label">Precio ($)</label>
                <input type="number" name="precio" class="form-control" step="0.01" placeholder="Ej: 9.99">
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

