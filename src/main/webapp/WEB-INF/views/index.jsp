<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
            <jsp:include page="/WEB-INF/includes/head.jsp">
                <jsp:param name="titulo" value="Estados"/>

            </jsp:include>
    </head>

    <body>
            <main class="py-3 row justify-content-center mt-5">
                <div id="login" class="col-lg-3 col-sm-6 col-10 rounded-3  border-3 " style="box-shadow: 2px 1px 20px 1px rgba(0,0,0,0.3);" >
                    <h3 class="pt-2 text-success" style="text-align:center;">Acceso al Sistema</h3>

                    <!--Mensaje de error-->
                    <% if (request.getAttribute("mensajeError") != null) { %>
                       <p style="color: red"><%= request.getAttribute("mensajeError") %></p>
                        <% } %>
                    <form action="${pageContext.request.contextPath}/login" method="POST">
                      <div class="mb-3">
                       <label for="username" class="form-label text-success">Usuario</label>
                         <input type="text"  class="form-control border-success" name="username" id="username" required>
                       </div>
                       <div class="mb-3">
                        <label for="password" class="form-label text-success">Contraseña</label>
                        <input type="password" class="form-control border-success"  name="password" id="password" required>
                       </div>
                       <div style="text-align: center;" >
                        <button type="submit"  class="btn btn-success" style="width:100%; ">Ingresar</button>
                       </div>
                    </form>

                <div>
            </main>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
        </body>
</html>