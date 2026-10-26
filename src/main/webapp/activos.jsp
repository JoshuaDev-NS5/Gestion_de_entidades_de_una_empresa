<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.proyecto.model.Empleado" %>
<%@ page import="org.proyecto.model.Activo" %>
<!DOCTYPE html>
  <html lang=es>
  <head>
    <meta charset="UTF-8">
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ITAM - Activos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
              rel="stylesheet">

  </head>
  <body class="bg-light">

<!-- Barra de navegación -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">

            <a class="navbar-brand" href="#">
                ITAM
            </a>

            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle"
                        type="button"
                        data-bs-toggle="dropdown"
                        aria-expanded="false">
                    Menú
                </button>

                <ul class="dropdown-menu dropdown-menu-end">
                    <li>
                        <a class="dropdown-item" href="#">Inicio</a>
                    </li>
                    <li>
                        <a class="dropdown-item" href="#">Empleados</a>
                    </li>
                    <li>
                        <a class="dropdown-item" href="#">Cerrar sesión</a>
                    </li>
                </ul>
            </div>

        </div>
    </nav>

    <!--Crud de Activos-->
    <div class="">
        <!--Row titulo y boton-->
        <div class="row row-col-12 mt-1 ">
            <div class="col text-center">
             <h1 class="text-black">Activos</h1>
                <table class="table" >
                      <tr>
                        <th scope="col" >ID</th>
                        <th scope="col" >Nombre</th>
                        <th scope="col" >Área</th>
                        <th scope="col" >Sueldo</th>
                        <th scope="col" >Estado</th>
                        <th scope="col" >ID_Empleado</th>
                      </tr>

                      <%
                        List<Activo> activos = (List<Activo>) request.getAttribute("Activos");

                        for (Activo emp : activos) {
                      %>
                          <tr>
                            <td><%= emp.getIdActivo()%></td>
                            <td><%=emp.getNombre()%> </td>
                            <td><%=emp.getMarca()%> </td>
                            <td><%=emp.getNroSerie()%> </td>
                            <td><%=emp.getEstado().getNombre()%></td>
                            <td><%=emp.getEmpleado() !=null ? emp.getEmpleado().getNombre() : "Sin asignar"%></td>
                          </tr>

                      <%
                        }
                      %>

                    </table>


            </div>
            <div class="col text-center d-grid gap-2">
                <button type="button" class="btn btn-success">Nuevo empleado</button>
                <button type="button" class="btn btn-success">Editar empleado</button>
                <button type="button" class="btn btn-success">Activar empleado</button>
                <button type="button" class="btn btn-success">Eliminar empleado</button>
            </div>
        </div>
    </div>



        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>