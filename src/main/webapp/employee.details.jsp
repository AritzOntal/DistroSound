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
            <h2 class="pb-2 border-bottom">Distribuidor</h2>
        </div>

        <%
            try {
                Database database = new Database();
                database.connect();
                EmployeeDao employeeDao = new EmployeeDao(database.getConnection());
                Employee employee = employeeDao.getEmployeeById(id);

                %>
        <ul class="list-group">
            <li class="list-group-item"><strong>Nombre</strong>: <%=employee.getName()%></li>
            <li class="list-group-item"><strong>Apellidos</strong>: <%=employee.getLast_name()%></li>
            <li class="list-group-item"><strong>Username</strong>: <%=employee.getUsername()%></li>
            <li class="list-group-item"><strong>Comisión</strong>: <%=employee.getComision()%></li>
            <li class="list-group-item"><strong>Especialidad</strong>: <%=employee.getSpeciality()%></li>
            <li class="list-group-item"><strong>Fecha socio</strong>: <%=employee.getHiring_date()%></li>
            <li class="list-group-item"><strong>Activo</strong>: <%=employee.isActive() ? "Si": "No"%></li>

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
