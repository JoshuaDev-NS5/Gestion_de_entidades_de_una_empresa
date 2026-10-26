<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar bg-white border-bottom">
    <div class="container-fluid">
        <div class="d-flex align-items-center gap-2">
            <button class="btn" type="button" data-bs-toggle="offcanvas"
                    data-bs-target="#menuLateral" aria-label="Abrir menú">
                <i class="bi bi-list fs-3 text-itam"></i>
            </button>
            <a class="navbar-brand mb-0" href="${pageContext.request.contextPath}/inicio">ITAM</a>
            <span class="text-secondary small d-none d-md-inline">Gestión de activos</span>
        </div>
    </div>
</nav>

<div class="offcanvas offcanvas-start" tabindex="-1" id="menuLateral">
    <div class="offcanvas-header bg-itam text-white">
        <h5 class="offcanvas-title">Bienvenido, Administrador</h5>
        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="offcanvas"></button>
    </div>
    <div class="offcanvas-body p-0">
        <div class="list-group list-group-flush">
            <a class="list-group-item list-group-item-action ${param.activo == 'inicio' ? 'active' : ''}"
               href="${pageContext.request.contextPath}/inicio"><i class="bi bi-house-door me-2"></i>Inicio</a>
            <a class="list-group-item list-group-item-action ${param.activo == 'empleados' ? 'active' : ''}"
               href="${pageContext.request.contextPath}/empleados"><i class="bi bi-people me-2"></i>Empleados</a>
            <a class="list-group-item list-group-item-action ${param.activo == 'activos' ? 'active' : ''}"
               href="${pageContext.request.contextPath}/activos"><i class="bi bi-laptop me-2"></i>Activos</a>
            <a class="list-group-item list-group-item-action ${param.activo == 'estados' ? 'active' : ''}"
               href="${pageContext.request.contextPath}/estados"><i class="bi bi-tag me-2"></i>Estados</a>
            <a class="list-group-item list-group-item-action text-danger mt-2" href="#">
                <i class="bi bi-box-arrow-right me-2"></i>Cerrar sesión</a>
        </div>
    </div>
</div>