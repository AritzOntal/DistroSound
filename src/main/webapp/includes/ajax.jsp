<script type="text/javascript">
  $(document).ready(function () {
    $("form").on("submit", function (event) {
      event.preventDefault();
      const formValue = $(this).serialize();
      var servletMapping = "<%= request.getAttribute("mappServlet")%>"
        $.ajax(servletMapping, {
        type: "POST",
        data: formValue,
        statusCode: {
          200: function (response) {
            if (response === "ok") {
              window.location.href = "index.jsp";
            } else {
              $("#result").html(response).show();
            }
          }
        }
      });
    });
  });
</script>
