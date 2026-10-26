package org.proyecto.dao;

import org.proyecto.config.Conexion;
import org.proyecto.model.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    public void insert(Empleado empleado) {
        String sql = "INSERT INTO Empleados (nombre, area, sueldo, estado) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getArea());
            ps.setDouble(3, empleado.getSueldo());
            ps.setString(4, empleado.getEstado());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al registrar el empleado.");
            System.err.println("Detalle: " + e.getMessage());
        }
    }

    public List<Empleado> getAll() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM Empleados";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Empleado empleado = new Empleado();

                empleado.setIdEmpleado(rs.getInt("id_empleado"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setArea(rs.getString("area"));
                empleado.setSueldo(rs.getDouble("sueldo"));
                empleado.setEstado(rs.getString("estado"));

                empleados.add(empleado);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar los empleados.");
            System.err.println("Detalle: " + e.getMessage());
        }

        return empleados;
    }

    public Empleado getById(int id) {
        Empleado empleado = null;
        String sql = "SELECT * FROM Empleados WHERE id_empleado = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    empleado = new Empleado();

                    empleado.setIdEmpleado(rs.getInt("id_empleado"));
                    empleado.setNombre(rs.getString("nombre"));
                    empleado.setArea(rs.getString("area"));
                    empleado.setSueldo(rs.getDouble("sueldo"));
                    empleado.setEstado(rs.getString("estado"));
                }
            }

        } catch ( SQLException e) {
            System.err.println("Error al buscar el empleado con ID: " + id);
            System.err.println("Detalle: " + e.getMessage());
        }

        return empleado;
    }

    public void update (Empleado  empleado) {
        String sql = "UPDATE Empleados SET nombre = ?, area = ?, sueldo = ?, estado = ? WHERE id_empleado = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getArea());
            ps.setDouble(3, empleado.getSueldo());
            ps.setString(4, empleado.getEstado());
            ps.setInt(5, empleado.getIdEmpleado());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar el empleado con ID: " + empleado.getIdEmpleado());
            System.err.println("Detalle: " + e.getMessage());
        }
    }

    public void delete (int id) {
        String sql = "UPDATE Empleados SET estado = 'baja' WHERE id_empleado = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al eliminar el empleado con ID: " + id);
            System.err.println("Detalle: " + e.getMessage());
        }
    }
}
