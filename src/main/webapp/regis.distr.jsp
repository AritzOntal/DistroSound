
<jsp:include page="includes/header.jsp"/>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setAttribute("mappServlet", "employee"); %>

<body>
<jsp:include page="includes/ajax.jsp"/>
<jsp:include page="includes/navbar.jsp"/>
<div class="bg-custom-employee">

    <div class="d-flex justify-content-center align-items-center" style="min-height: 100vh;">
        <div class="card p-4 shadow-lg" style="width: 500px;">
            <div class="card-body">
                <h1 class="h3 mb-3 fw-normal text-center">Distro Sound</h1>
                <p class="text-center">Si eres un distribuidor regístrate ahora.</p>

                <form class="row g-3">
                    <div class="col-12">
                        <label for="inputEmail" class="form-label">Email</label>
                        <input type="text" name="email" class="form-control" id="inputEmail">
                    </div>
                    <div class="col-12">
                        <label for="inputLastName" class="form-label">Name</label>
                        <input type="text" name="nombre" class="form-control" id="inputLfdsastName">
                    </div>
                    <div class="col-12">
                        <label for="inputLastName" class="form-label">Last Name</label>
                        <input type="text" name="apellidos" class="form-control" id="inputLastName">
                    </div>
                    <div class="col-md-6">
                        <label for="inputUserName" class="form-label">User name</label>
                        <input type="text" name="username" class="form-control" id="inputUsername">
                    </div>
                    <div class="col-md-6">
                        <label for="inputPassword" class="form-label">Password</label>
                        <input type="password" name="password" class="form-control" id="inputPassword">
                    </div>
                    <div class="col-12">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="gridCheck">
                            <label class="form-check-label" for="gridCheck">
                                Acepto los términos y condiciones
                            </label>
                        </div>
                    </div>
                    <div class="col-12">
                        <button type="submit" class="btn btn-success w-100">Comenzar</button>
                    </div>
                    <div id="result" class="alert alert-danger" role="alert" style="display: none">
                        No se ha podido crear el usuario!
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
