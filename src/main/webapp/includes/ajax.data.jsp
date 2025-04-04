<script type="text/javascript">
    $(document).ready(function () {
        $("form").on("submit", function (event) {
            event.preventDefault();
            const form = $("form")[0]
            const formValue = new FormData(form);
            var servletMapping = "<%= request.getAttribute("mappServlet")%>"
            $.ajax(servletMapping, {
                url: servletMapping,
                type: "POST",
                data: formValue,
                processData: false,
                contentType: false,
                cache: false,
                timeout: 10000,
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
