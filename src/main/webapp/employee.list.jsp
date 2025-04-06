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

%>
<div class="container">
    <div class="col">
        <div>
            <h2 class="pb-2 border-bottomx">Lista de distribuidores</h2>
        </div>

        <%
            try {
                Database database = new Database();
                database.connect();
                EmployeeDao employeeDao = new EmployeeDao(database.getConnection());

                List<Employee> employeeList = employeeDao.getAll();


                for (Employee employee : employeeList) {

        %>
        <ul class="list-group">
            <li class="list-group-item d-flex justify-content-between align-items-center">
                <span><%=employee.getLast_name()%>, <%=employee.getName()%></span>
                <a href="employee.details.jsp?id=<%=employee.getId_employee()%>" class="btn btn-info btn-sm">Ver</a>
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
