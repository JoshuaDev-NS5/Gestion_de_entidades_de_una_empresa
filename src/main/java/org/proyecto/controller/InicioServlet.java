package org.proyecto.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.proyecto.dao.ActivoDAO;
import org.proyecto.dao.EmpleadoDAO;
import org.proyecto.dao.EstadoActivoDAO;
import java.io.IOException;

@WebServlet(urlPatterns = {"","/inicio"})
public class InicioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("totalEmpleados", new EmpleadoDAO().getAll().size());
        req.setAttribute("totalActivos", new ActivoDAO().getAll().size());
        req.setAttribute("totalEstados", new EstadoActivoDAO().getAll().size());

        req.getRequestDispatcher("/WEB-INF/views/inicio.jsp").forward(req, resp);
    }
}
