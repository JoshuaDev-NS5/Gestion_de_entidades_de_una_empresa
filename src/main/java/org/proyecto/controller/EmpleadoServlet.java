package org.proyecto.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.proyecto.dao.EmpleadoDAO;
import org.proyecto.model.Empleado;

import java.io.IOException;
import java.util.List;

@WebServlet("/empleados")
public class EmpleadoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        List<Empleado> empleados = empleadoDAO.getAll();
        req.setAttribute("empleados", empleados);
        req.getRequestDispatcher("/empleados.jsp").forward(req, resp);
    }

}
