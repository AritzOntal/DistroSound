<%@ page import="com.svalero.distrosound.servlet.LoginServlet" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">DISTRO SOUND</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span
                class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link" href="store.jsp">Tienda</a></li>

                <%
                    HttpSession currentSession = request.getSession();
                    String role = "anonymous";
                    if (currentSession.getAttribute("role") !=null){
                        role = currentSession.getAttribute("role").toString();
                    }

                    if (role.equals("anonymous")) {
                %>
                <li class="nav-item"><a class="nav-link" aria-current="page" href="index.jsp">Soy Artista</a></li>
                <li class="nav-item"><a class="nav-link" href="regis.distr.jsp">Soy Distribuidor</a></li>
                <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
                <%
                    } else {
                %>

                <li class="nav-item"><a class="nav-link" href="client.zone.jsp">Mi zona</a></li>
                <li class="nav-item"><a class="nav-link" href="/DistroSound/logout">Logout</a></li>

                <%
                    }
                %>

            </ul>
        </div>
    </div>
</nav>
