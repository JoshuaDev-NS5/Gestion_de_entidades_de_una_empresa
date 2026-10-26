package org.proyecto;

import org.proyecto.dao.ActivoDAO;
import org.proyecto.dao.EmpleadoDAO;
import org.proyecto.model.Activo;
import org.proyecto.model.Empleado;

import java.util.List;

//TIP Para <b>ejecutar</b> el código, pulsar <shortcut actionId="Run"/> o
// Haz clic en el ícono <icon src="AllIcons.Actions.Execute"/> del margen.
public class Main {
    public static void main(String[] args) {
        ActivoDAO activoDAO = new ActivoDAO();
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        //Empleado emp = empleadoDAO.getById(2);
        Activo act = activoDAO.getById(2);

        act.setEmpleado(null);

        activoDAO.update(act);

        List<Activo> activos = activoDAO.getAll();

        for (Activo a : activos) {
            System.out.println(a.informacion());
            System.out.println("-----------------------------");
        }

        List<Empleado> empleados = empleadoDAO.getAll();

        for (Empleado e : empleados) {
            System.out.println(e);
            System.out.println("-----------------------------");
        }
    }
}