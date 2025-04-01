<%@ page import="com.svalero.distrosound.model.User" %>
<%@ page import="com.svalero.distrosound.dao.UserDao" %>
<jsp:include page="includes/header.jsp"/>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%--Hago una funcion asincrona, para que el envio del formulario espere a la respuesta del servidor
              antes de ir a lo loco.--%>
<script type="text/javascript">
    $(document).ready(function () {
        $("form").on("submit", function (event) {
            event.preventDefault();
            const formValue = $(this).serialize();
            $.ajax("login", {
                type: "POST",
                data: formValue,
                statusCode: {
                    200: function (response) {
                        if (response === "ok") {
                            window.location.href = "index.jsp";
                        } else {
                            $("#result").html();
                        }
                    }
                }
            });
        });
    });
</script>

<body>
<jsp:include page="includes/navbar.jsp"/>
<div class="bg-custom-index">
    <div class="container-centered">
        <div class="card p-4 shadow-lg" style="width: 350px;">
            <div class="card-body">
                <h1 class="h3 mb-3 fw-normal text-center">Indetifícate</h1>
                <form>
                    <div class="form-floating mb-2">
                        <input type="text" name="username" class="form-control" id=username
                               placeholder="Nombre de usuario">
                        <label for="username">username</label>
                    </div>
                    <div class="form-floating mb-2">
                        <input type="password" name="password" class="form-control" id="password"
                               placeholder="Contraseña">
                        <label for="password">password</label>
                    </div>
                    <div class="form-check text-start my-3">
                        <input class="form-check-input" type="checkbox" value="remember-me" id="flexCheckDefault">
                        <label class="form-check-label" for="flexCheckDefault">Recordarme</label>
                    </div>
                    <button class="btn btn-primary w-100 py-2" type="submit">Iniciar sesión</button>
                    <div id="result" class="alert alert-danger" role="alert" style="display: none">
                        El usuario o contraseña son incorrectos!
                    </div>
                </form>
                <p class="mt-3 mb-0 text-center text-body-secondary">&copy; 2017–2024</p>
            </div>
        </div>
    </div>
</div>

</body>
</html>
