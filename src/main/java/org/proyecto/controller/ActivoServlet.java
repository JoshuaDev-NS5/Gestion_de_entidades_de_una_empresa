package org.proyecto.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.proyecto.dao.ActivoDAO;
import org.proyecto.model.Activo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/activos")
public class ActivoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        ActivoDAO activoDAO= new ActivoDAO();

        List<Activo> Activos=activoDAO.getAll();
        req.setAttribute("Activos",Activos);
        req.getRequestDispatcher("/activos.jsp").forward(req,resp);
    }
}
