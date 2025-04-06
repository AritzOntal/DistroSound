<jsp:include page="includes/header.jsp"/>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<body>

<% HttpSession session1 = request.getSession(false);
    if (session1 != null && session1.getAttribute("id_employee") != null) {
        int idEmployee = (int) session1.getAttribute("id_employee");
%>

<jsp:include page="includes/navbar.jsp"/>

<div class="bg-custom-client-zone">
    <div class="container px-4 py-5" id="featured-3">
        <div class="row g-4 row-cols-1 row-cols-lg-2">
            <div class="col">
                <div class="bg-white p-4 rounded shadow-lg h-100">
                    <h3 class="fs-4 text-body-emphasis">GESTIÓN</h3>
                    <p>Visualiza aqui la lista de artistas y distribuidores.</p>
                    <a href="client.list.jsp" class="btn btn-success btn-sm mt-3">VER ARTISTAS</a>
                    <a href="employee.list.jsp" class="btn btn-success btn-sm mt-3">VER DISTRIBUIDORES</a>
                </div>
            </div>
            <div class="col">
                <div class="bg-white p-4 rounded shadow-lg h-100">
                    <h3 class="fs-4 text-body-emphasis">Mi perfil</h3>
                    <p>Actualiza, modifica o elimina tu perfil.</p>
                    <a href="modify.employee.jsp?id_employee=<%=idEmployee%>"
                       class="btn btn-primary btn-sm">Modificar</a>
                    <a href="deleteEmployee?id_employee=<%=idEmployee%>" class="btn btn-danger btn-sm">Eliminar</a>
                </div>
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