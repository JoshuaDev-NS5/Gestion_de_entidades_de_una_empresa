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
        <jsp:include page="/WEB-INF/includes/navbar.jsp">
            <jsp:param name="activo" value="estados"/>
        </jsp:include>

        <main class="container  py-4" style="border:solid 1px red">
            <div class="d-flex align-items-center mb-3">
                <h1 class="h3 mb-0">Estados de activo</h1>
                <button type="button" class="btn btn-itam ms-auto" onclick="abrirNuevo()">
                   <i class="bi bi-plus-lg"></i> Nuevo estado
                </button>
            </div>

            <c:if test="${not empty mensaje}">
               <div class="alert alert-${tipoMensaje} alert-dismissible fade show" role="alert">
                    <c:out value="${mensaje}"/>
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
               </div>
            </c:if>


            <div class="card shadow-sm">
                <div class="table-responsive">
                    <table class="table table-hover align-middle mb-0">
                        <thead class="table-light">
                              <tr>
                                  <th style="width:20px">ID</th>
                                  <th class="text-center">Nombre</th>
                                  <th class="text-center">Descripción</th>
                                  <th class="text-center" style="width:120px">Acciones</th>
                               </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="e" items="${estados}">
                                <tr>
                                    <td>${e.id_estado}</td>
                                    <td><c:out value="${e.nombre}"/></td>
                                    <td><c:out value="${e.descripcion}"/></td>
                                    <td class="text-end">
                                        <button type="button" class="btn btn-sm btn-outline-primary"
                                                      data-id="${e.id_estado}"
                                                      data-nombre="<c:out value='${e.nombre}'/>"
                                                      data-descripcion="<c:out value='${e.descripcion}'/>"
                                                     onclick="abrirEditar(this)" aria-label="Editar">
                                            <i class="bi bi-pencil"></i>
                                        </button>
                                        <form method="post" action="${pageContext.request.contextPath}/estados"
                                                    class="d-inline"
                                                    onsubmit="return confirm('¿Eliminar este estado?');">
                                                    <input type="hidden" name="accion" value="eliminar">
                                                    <input type="hidden" name="id" value="${e.id_estado}">
                                                    <button type="submit" class="btn btn-sm btn-outline-danger" aria-label="Eliminar">
                                                   <i class="bi bi-trash"></i>
                                              </button>
                                        </form>
                                    </td>
                                </tr>

                            </c:forEach>
                             <c:if test="${empty estados}">
                                  <tr><td colspan="4" class="text-center text-secondary py-4">No hay estados registrados.</td></tr>
                             </c:if>
                        </tbody>
                    </table>
                </div>
            </div>

        </main>

        <!--Modal para crear y editar-->
        <!-- Un solo modal sirve para crear y para editar -->
        <div class="modal fade" id="modalEstado" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog">
                <form class="modal-content" method="post" action="${pageContext.request.contextPath}/estados">
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
             const modalEstado = new bootstrap.Modal(document.getElementById('modalEstado'));

                function abrirNuevo() {
                    document.getElementById('modalTitulo').textContent = 'Nuevo estado';
                    document.getElementById('accion').value = 'crear';
                    document.getElementById('id').value = '0';
                    document.getElementById('nombre').value = '';
                    document.getElementById('descripcion').value = '';
                    modalEstado.show();
                }

                function abrirEditar(boton) {
                    document.getElementById('modalTitulo').textContent = 'Editar estado';
                    document.getElementById('accion').value = 'editar';
                    document.getElementById('id').value = boton.dataset.id;
                    document.getElementById('nombre').value = boton.dataset.nombre;
                    document.getElementById('descripcion').value = boton.dataset.descripcion;
                    modalEstado.show();
                }
        </script>
    </body>
</html>