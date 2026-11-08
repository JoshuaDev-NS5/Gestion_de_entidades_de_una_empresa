package org.proyecto.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"","/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws  ServletException,IOException{
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }

    @Override
    protected void  doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //Tomamos las variables que llegaran de la solicitud post
        String username=req.getParameter("username");
        String password=req.getParameter("password");

        String usernameAdmin = "admin";
        String passwordAdmin = "123";

        if (username.equals(usernameAdmin) && password.equals(passwordAdmin)) {
            //DAR ACCESO AL SISTEMA
            req.getSession().setAttribute("username", username);
            resp.sendRedirect(req.getContextPath() + "/inicio");//LLeva por Path porque la web necesita informacion de la ruta
        } else {
            //DENEGAR ACCESO AL SISTEMA Y MANDAR ERROR
            req.setAttribute("mensajeError", "El usuario o la contraseña son incorrectos.");//Crea una variable que lleva informacion de error
            req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);//No necesita PATH porque esta a nivel de raiz.
        }
    }
}
