package org.proyecto.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.proyecto.dao.EmpleadoDAO;
import org.proyecto.model.Empleado;

import java.io.IOException;
import java.util.List;

@WebServlet("/empleados")
public class EmpleadoServlet extends HttpServlet {
    EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    private String valor( String valor){
        return valor == null ? "":valor.trim();
    }

    //flash se encarga de guardar el tipo y mensaje del error en la variable sesion
    private void flash(HttpSession session,String tipo, String texto){
        session.setAttribute("tipoMensaje", tipo);
        session.setAttribute("mensaje",texto);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession(); //activamos la sesion
        Object mensaje=session.getAttribute("mensaje");
        if(mensaje != null){
            req.setAttribute("mensaje",mensaje);//traemos desde el session la informacion a una variable normal para utilizarlo
            req.setAttribute("tipo",session.getAttribute("tipoMensaje"));
            session.removeAttribute("mensaje");//Borramos la información de sesion para liberarlo
            session.removeAttribute("tipoMensaje");
        }

        List<Empleado> empleados = empleadoDAO.getAll();
        req.setAttribute("empleados", empleados);
        req.getRequestDispatcher("/WEB-INF/views/empleados.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws SecurityException, IOException{
        req.setCharacterEncoding("UTF-8"); // antes de leer parámetros, para tildes y ñ.
        //Le indica al servidor que lea la solicitud http con la codificacion UTF-8

        HttpSession session=req.getSession();//activamos sesion
        String accion=req.getParameter("accion");//recivira por parte de la solicitud si se desea hacer una accion
        if(accion==null){
            accion = "";
        }

        String nombre= valor(req.getParameter("nombre"));
        String area=valor(req.getParameter("area"));
//
        double sueldo=Double.parseDouble(req.getParameter("sueldo"));
        String estado=valor(req.getParameter("estado"));


        try {
            switch (accion){
                case "crear"->{
                    if(nombre.isEmpty()){
                        flash(session,"warning","El nombre es un campo obligatorio");
                    }else if(empleadoDAO.insert(new Empleado(nombre,area,sueldo,estado))){
                        flash(session, "success", "Empleado creado correctamente.");
                    }else {
                        flash(session,"danger", "No se pudo crear el estado");
                    }
                }
                case "editar"->{
                    int id=Integer.parseInt(req.getParameter("id"));
                    if(id < 0 ){
                        flash(session,"warning","El ID no esta asociado a ningun empleado");
                    }
                    if(nombre.isEmpty()){
                        flash(session,"warning","El nombre es un campo obligatorio");
                    }
                    else if(empleadoDAO.update(new Empleado(id,nombre,area,sueldo,estado))){
                        flash(session,"success","Empleado actualizado id: "+ id);
                    }
                    else {
                        flash(session,"danger","No se logro actualizar el empleado");
                    }
                }
                case "eliminar"->{
                    int id=Integer.parseInt(req.getParameter("id"));
                    if (empleadoDAO.delete(id)){
                        flash(session,"success","Empleado " + id + " eliminado");
                    }else {
                        flash(session,"danger","El empleado " + id + " no logro eliminarse");
                    }
                }
                default -> flash(session,"danger","Acción no valida");
            }

        }catch (NumberFormatException e){
            flash(session,"danger","Identidad de accion no valida");
        }

        resp.sendRedirect(req.getContextPath() + "/empleados");//Actua como respuesta en la pagina, la pagina necesita la direccion

    }

}
