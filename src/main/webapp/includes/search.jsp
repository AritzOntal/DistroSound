<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="container" style="max-width: 400px;">
  <form method="get" class="mb-2">
    <div class="input-group input-group-sm">
      <input type="text" name="search" class="form-control form-control-sm"
             placeholder="Buscar..."
             value="<%= request.getParameter("search") != null ? request.getParameter("search") : "" %>">
      <button class="btn custom-btn" type="submit">🔍 Buscar</button>
    </div>
  </form>
</div>
