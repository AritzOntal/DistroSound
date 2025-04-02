<script type="text/javascript">
  $(document).ready(function () {
    $("form").on("submit", function (event) {
      event.preventDefault();
      const formValue = $(this).serialize();
      $.ajax("<%= request.getAttribute("mappServlet")%>", {
        type: "POST",
        data: formValue,
        statusCode: {
          200: function (response) {
            if (response === "ok") {
              window.location.href = "index.jsp";
            } else {
              $("#result").html("Error al crear el usuario!").show();
            }
          }
        }
      });
    });
  });
</script>
