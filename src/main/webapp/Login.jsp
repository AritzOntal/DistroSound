<jsp:include page="includes/head.jsp"/>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<body>

<jsp:include page="includes/navbar.jsp"/>

<div class="bg-custom-index">
    <div class="container-centered">
        <div class="card p-4 shadow-lg" style="width: 350px;">
            <div class="card-body">
                <h1 class="h3 mb-3 fw-normal text-center">Please sign in</h1>
                <form>
                    <div class="form-floating mb-2">
                        <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                        <label for="floatingInput">Email address</label>
                    </div>
                    <div class="form-floating mb-2">
                        <input type="password" class="form-control" id="floatingPassword" placeholder="Password">
                        <label for="floatingPassword">Password</label>
                    </div>
                    <div class="form-check text-start my-3">
                        <input class="form-check-input" type="checkbox" value="remember-me" id="flexCheckDefault">
                        <label class="form-check-label" for="flexCheckDefault">Remember me</label>
                    </div>
                    <button class="btn btn-primary w-100 py-2" type="submit">Sign in</button>
                </form>
                <p class="mt-3 mb-0 text-center text-body-secondary">&copy; 2017–2024</p>
            </div>
        </div>
    </div>
</div>

</body>
</html>
