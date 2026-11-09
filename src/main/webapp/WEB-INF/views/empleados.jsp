<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="java.util.List" %>
<%@ page import="org.proyecto.model.Empleado"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <jsp:include page="/WEB-INF/includes/head.jsp">
           <jsp:param name="titulo" value="Empleados"/>
        </jsp:include>
    </head>

    <body>
        <jsp:include page="/WEB-INF/includes/navbar.jsp">
            <jsp:param name="activo" value="estados"/>
        </jsp:include>

        <main class="container py-4">
            <div class="d-flex align-items-center mb-3" >
                <h1 class="h3 mb-0">Empleados</h1>
                <button type="button" class="btn btn-itam ms-auto" onclick="abrirNuevo()">
                    <i class="bi bi-plus-lg"></i>Nuevo estado
                </button>
            </div>
            <!--Mensaje de error-->
            <% if(request.getAttribute("mensaje") != null) {%>
                <div class="alert alert-${tipoMensaje} alert-dismissible fade show" role="alert">
                      <%=request.getAttribute("mensaje")%>
                      <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>

            <%}%>
            <div class="card shadow-sm">
                <div class="table-responsive">
                    <table class="table table-hover align-middle mb-0">
                        <thead class="table-light">
                            <tr>
                               <th style="width:20px">ID</th>
                               <th class="text-center">Nombre</th>
                               <th class="text-center">Area</th>
                               <th class="text-center">Sueldo</th>
                               <th class="text-center">Estado</th>
                               <th class="text-center" style="width:120px">Acciones</th>
                            </tr>
                        </thead>

                        <tbody>
                            <%
                                List<Empleado> empleados =(List<Empleado>)request.getAttribute("empleados");

                                for(Empleado emp: empleados){
                            %>
                                <tr>
                                    <td><%= emp.getIdEmpleado()%></td>
                                    <td class="text-center"><%= emp.getNombre()%></td>
                                    <td class="text-center"><%= emp.getArea()%></td>
                                    <td class="text-center"><%= emp.getSueldo()%></td>
                                    <td class="text-center"><%= emp.getEstado()%></td>
                                    <!--Crear acciones-->
                                </tr>

                            <%
                            }
                            %>
                        </tbody>
                    </table>
                </div>

            </div>

        </main>

        <!---Modal para crear y editar-->
        <div class="modal fade" id="modalEstado" tabidex="-1" aria-hidden="true">
            <div class="modal-dialog">
                <form class="modal-content" method="post" action="${pageContext.request.contextPath}/empleados" >
                    <div class="modal-header bg-itam text-white">
                      <h5 class="modal-title" id="modalTitulo">Nuevo estado</h5>
                      <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                       <input type="hidden" name="accion" id="accion" value="crear">
                       <input type="hidden" name="id" id="id" value="0">
                       <div class="mb-3">
                          <label class="form-label" for="nombre">Nombre</label>
                          <input type="text" class="form-control" name="nombre" id="nombre" required>
                       </div>
                       <div class="mb-3">
                          <label class="form-label" for="descripcion">Descripción</label>
                          <textarea class="form-control" name="descripcion" id="descripcion" rows="2"></textarea>
                       </div>
                    </div>
                    <div class="modal-footer">
                       <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">Cancelar</button>
                       <button type="submit" class="btn btn-itam">Guardar</button>
                    </div>
                </form>
            </div>
        </div>

        <jsp:include page="/WEB-INF/includes/scripts.jsp"/>
        <script>
            const modalEstado= new bootstrap.Modal(document.getElementById('modalEstado'));

            function abrirNuevo(){
                document.getElementById('modalTitulo').textContent = 'Nuevo estado';
                document.getElementById('accion').value = 'crear';
                document.getElementById('id').value = '0';
                document.getElementById('nombre').value = '';
                document.getElementById('descripcion').value = '';
                modalEstado.show();

            }
        </script>
    </body>
</html>