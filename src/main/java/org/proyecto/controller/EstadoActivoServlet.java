package org.proyecto.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.proyecto.dao.EstadoActivoDAO;
import org.proyecto.model.EstadoActivo;

import java.io.IOException;

@WebServlet("/estados")
public class EstadoActivoServlet extends HttpServlet {
    private final EstadoActivoDAO dao = new EstadoActivoDAO();

    private String valor(String texto) {//Limpia el dato de espacios vacios y si esta vacio lo remplaza por un ""
        return texto == null ? "" : texto.trim();
    }
    //Toma la variable de session y guarda dentro de ella los datos de tipo y texto
    private void flash(HttpSession session, String tipo, String texto) {
        session.setAttribute("tipoMensaje", tipo);
        session.setAttribute("mensaje", texto);
    }

    //GET= "Quiero ver la pagina":lista y datos
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Si venimos de un POST, el mensaje quedó guardado en la sesión: lo pasamos al request y lo borramos
        HttpSession session = req.getSession();
        Object mensaje = session.getAttribute("mensaje");
        if (mensaje != null) {
            req.setAttribute("mensaje", mensaje);
            req.setAttribute("tipoMensaje", session.getAttribute("tipoMensaje"));
            session.removeAttribute("mensaje");
            session.removeAttribute("tipoMensaje");
        }

        req.setAttribute("estados", dao.getAll());
        req.getRequestDispatcher("/WEB-INF/views/estados.jsp").forward(req, resp);
    }

    // POST = "quiero cambiar datos": crear, editar o eliminar según el parámetro "accion"
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8"); // antes de leer parámetros, para tildes y ñ

        HttpSession session = req.getSession();//activamos sesion
        String accion = req.getParameter("accion");
        if (accion == null) accion = "";

        String nombre = valor(req.getParameter("nombre"));
        String descripcion = valor(req.getParameter("descripcion"));

        try {
            switch (accion) {
                case "crear" -> {
                    if (nombre.isEmpty()) {
                        flash(session, "warning", "El nombre es obligatorio.");
                    } else if (dao.insert(new EstadoActivo(nombre, descripcion))) {
                        flash(session, "success", "Estado creado correctamente.");
                    } else {
                        flash(session, "danger", "No se pudo crear el estado. Verifica que el nombre no exista.");
                    }
                }
                case "editar" -> {
                    int id = Integer.parseInt(req.getParameter("id"));
                    if (nombre.isEmpty()) {
                        flash(session, "warning", "El nombre es obligatorio.");
                    } else if (dao.update(new EstadoActivo(id, nombre, descripcion))) {
                        flash(session, "success", "Estado actualizado correctamente.");
                    } else {
                        flash(session, "danger", "No se pudo actualizar. Verifica que el nombre no exista.");
                    }
                }
                case "eliminar" -> {
                    int id = Integer.parseInt(req.getParameter("id"));
                    if (dao.DeleteReal(id)) {
                        flash(session, "success", "Estado eliminado.");
                    } else {
                        flash(session, "danger", "No se pudo eliminar: hay activos que usan este estado.");
                    }
                }
                default -> flash(session, "danger", "Acción no válida.");
            }
        }catch(NumberFormatException e){
            flash(session,"danger","Identidad no validada");
        }

        // Post-Redirect-Get: si respondiéramos con forward, al refrescar (F5) se repetiría el POST
        resp.sendRedirect(req.getContextPath() + "/estados");
    }
}