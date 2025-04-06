<%@ page import="java.sql.SQLException" %>
<%@ page import="com.svalero.distrosound.database.Database" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.svalero.distrosound.dao.AlbumDao" %>
<%@ page import="com.svalero.distrosound.model.Album" %>
<%@ page import="static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.title" %>
<%@ page import="com.svalero.distrosound.dao.EmployeeDao" %>
<%@ page import="com.svalero.distrosound.model.Employee" %>
<jsp:include page="includes/header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setAttribute("mappServlet", "modifyEmployee"); %>

<body>
<jsp:include page="includes/ajax.data.jsp"/>
<jsp:include page="includes/navbar.jsp"/>

<% int idEmployee = Integer.parseInt(request.getParameter("id_employee")); %>

<%


  try {
    Database database = new Database();
    database.connect();
    EmployeeDao employeeModify = new EmployeeDao(database.getConnection());
    Employee employee = employeeModify.getEmployeeById(idEmployee);

    String name = employee.getName();
    String lastName = employee.getLast_name();
    String email = employee.getEmail();
    boolean distributor = employee.isDistributor();
    Float comision = employee.getComision();
    String username = employee.getUsername();
    String speciality = employee.getSpeciality();
    int id = employee.getId_employee();

%>

<div class="container d-flex justify-content-center align-items-center min-vh-100">
  <div class="col-md-6 bg-light p-4 rounded shadow">
    <h4 class="text-center mb-3">Editar álbum</h4>
    <form method="POST" action="yourServletHere">
      <div class="mb-2">
        <label class="form-label">Nombre</label>
        <input type="text" name="nombre" class="form-control" placeholder="Ingrese el título"
               value="<%= name != null ? name : "" %>">
      </div>
      <div class="mb-2">
        <label class="form-label">Apellidos</label>
        <input type="text" name="apellidos" class="form-control" placeholder="Ingrese el artista"
               value="<%= lastName != null ? lastName : "" %>">
      </div>
      <div class="mb-2">
        <label class="form-label">email</label>
        <input type="text" name="email" class="form-control" placeholder="Ingrese el género"
               value="<%= email != null ? email : "" %>">
      </div>
      <div class="mb-2">
        <label class="form-label">Username</label>
        <input type="text" name="precio" class="form-control" placeholder="Ingrese el precio"
               value="<%= username != null ? username : "" %>">
      </div>
      <div class="mb-2">
        <label class="form-label">Especialidad</label>
        <textarea name="especialidad" class="form-control"
                  placeholder="Ingrese la descripción"><%= speciality != null ? speciality : "" %></textarea>
      </div>
      <div class="mb-2">
        <label class="form-label">¿Es distribuidor?</label>
        <input type="checkbox" name="explicit" class="form-check-input"
          <%= distributor ? "checked" : "" %>>
      </div>
      <input type="hidden" name="id_employee" value="<%= employee.getId_employee() %>">
      <div class="mb-2 text-center">
        <button type="submit" class="btn btn-primary">Actualizar</button>
      </div>
    </form>
  </div>

    <%
    } catch (Exception e) {
    e.printStackTrace();
    }
%>
</body>
