<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <jsp:include page="/WEB-INF/includes/head.jsp">
                <jsp:param name="titulo" value="Inicio"/>
         </jsp:include>
    </head>

    <body>
        <jsp:include page="/WEB-INF/includes/navbar.jsp">
            <jsp:param name="activo" value="inicio"/>
        </jsp:include>

        <main class="container py-4">
            <div class="p-4 mb-4 rounded-3 bg-itam text-white">
                <h1 class="h3">Control de empleados y activos</h1>
                <p class="mb-0">Registra a tu personal, los equipos de la empresa y el estado de cada uno.</p>
            </div>

            <div class="row g-3">
                <div class="col-md-4">
                    <a href="${pageContext.request.contextPath}/empleados" class="text-decoration-none">
                        <div class="card shadow-sm h-100"><div class="card-body">
                            <div class="text-secondary small">Empleados</div>
                            <div class="fs-2 fw-medium text-dark">${totalEmpleados}</div>
                        </div></div>
                    </a>
                </div>
                <div class="col-md-4">
                    <a href="${pageContext.request.contextPath}/activos" class="text-decoration-none">
                        <div class="card shadow-sm h-100"><div class="card-body">
                            <div class="text-secondary small">Activos</div>
                            <div class="fs-2 fw-medium text-dark">${totalActivos}</div>
                        </div></div>
                    </a>
                </div>
                <div class="col-md-4">
                    <a href="${pageContext.request.contextPath}/estados" class="text-decoration-none">
                        <div class="card shadow-sm h-100"><div class="card-body">
                            <div class="text-secondary small">Estados de activo</div>
                            <div class="fs-2 fw-medium text-dark">${totalEstados}</div>
                        </div></div>
                    </a>
                </div>
            </div>
        </main>

    <jsp:include page="/WEB-INF/includes/scripts.jsp"/>
    </body>
</html>

