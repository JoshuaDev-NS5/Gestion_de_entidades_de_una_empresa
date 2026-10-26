package org.proyecto.dao;


import org.proyecto.config.Conexion;
import org.proyecto.model.Activo;
import org.proyecto.model.Empleado;
import org.proyecto.model.EstadoActivo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivoDAO {
    public boolean insert(Activo activo) {
        String sql = "INSERT INTO Activos (nombre, marca, nro_serie,id_estado, id_empleado) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setString(1, activo.getNombre());
            ps.setString(2, activo.getMarca());
            ps.setString(3, activo.getNroSerie());

            if(activo.getEstado() != null ){
                ps.setInt(4,activo.getEstado().getId_estado());
            }else {
                ps.setNull(4, Types.INTEGER);
            }

            if(activo.getEmpleado() != null ){
                ps.setInt(5,activo.getEmpleado().getIdEmpleado());
            }else {
                ps.setNull(5, Types.INTEGER);
            }


            int filas_afectadas = ps.executeUpdate();
            return filas_afectadas > 0;
        } catch (SQLException e) {
            if (e.getErrorCode() == 2627 || e.getErrorCode() == 2601
                    || e.getMessage().contains("UNIQUE KEY constraint")) {
                System.out.println("Error: el número de serie \"" + activo.getNroSerie() + "\" ya existe.");
                return false;
            }
            System.err.println("Error al registrar el activo.");
            System.err.println("Detalle: " + e.getMessage());
            return false;
        }
    }

    public List<Activo> getAll() {
        List<Activo> activos = new ArrayList<>();
        String sql = "SELECT * FROM Activos";
        EmpleadoDAO empleadoDAO= new EmpleadoDAO();
        EstadoActivoDAO estadoActivoDAO=new EstadoActivoDAO();
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {

            while (rs.next()) {
                Activo activo = new Activo();

                activo.setIdActivo(rs.getInt("id_activo"));
                activo.setNombre(rs.getString("nombre"));
                activo.setMarca(rs.getString("marca"));
                activo.setNroSerie(rs.getString("nro_serie"));

                int id_Estado=rs.getInt("id_estado");
                EstadoActivo estadoActivo=estadoActivoDAO.getById(id_Estado);
                activo.setEstado(estadoActivo);

                int idEmpleado=rs.getInt("id_empleado");
                Empleado e= empleadoDAO.getById(idEmpleado);
                activo.setEmpleado(e);

                activos.add(activo);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar los activos.");
            System.err.println("Detalle: " + e.getMessage());
        }

        return activos;
    }

    public Activo getById(int id) {
        Activo activo = null;
        String sql = "SELECT * FROM Activos WHERE id_activo = ?";
        EmpleadoDAO empleadoDAO= new EmpleadoDAO();
        EstadoActivoDAO estadoActivoDAO=new EstadoActivoDAO();
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    activo = new Activo();

                    activo.setIdActivo(rs.getInt("id_activo"));
                    activo.setNombre(rs.getString("nombre"));
                    activo.setMarca(rs.getString("marca"));
                    activo.setNroSerie(rs.getString("nro_serie"));


                    int id_estado=rs.getInt("id_estado");
                    EstadoActivo estadoActivo= estadoActivoDAO.getById(id_estado);
                    activo.setEstado(estadoActivo);

                    int idEmpleado=rs.getInt("id_empleado");
                    Empleado e= empleadoDAO.getById(idEmpleado);
                    activo.setEmpleado(e);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar el activo con ID: " + id);
            System.err.println("Detalle: " + e.getMessage());
        }

        return activo;
    }

    public boolean update(Activo activo) {
        String sql = "UPDATE Activos SET nombre = ?, marca = ?, nro_serie = ?, id_estado = ?, id_empleado = ?  WHERE id_activo = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setString(1, activo.getNombre());
            ps.setString(2, activo.getMarca());
            ps.setString(3, activo.getNroSerie());
            if(activo.getEstado() !=null){
                ps.setInt(4,activo.getEstado().getId_estado());
            }else {
                ps.setNull(4,Types.INTEGER);
            }

            if(activo.getEmpleado() != null){
                ps.setInt(5,activo.getEmpleado().getIdEmpleado());
            }else {
                ps.setNull(5, Types.INTEGER);
            }

            ps.setInt(6, activo.getIdActivo());


            int filas_afectadas= ps.executeUpdate();
            return  filas_afectadas>0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar el activo con ID: " + activo.getIdActivo());
            System.err.println("Detalle: " + e.getMessage());
            return false;
        }
    }

    public void delete(int id) {
        String sql = "UPDATE Activos SET id_estado = (select id_estado from Estados_Activo where nombre = 'De Baja') WHERE id_activo = 1";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al eliminar el activo con ID: " + id);
            System.err.println("Detalle: " + e.getMessage());
        }
    }

    public List<Activo> ActivosConEstado(){
        List<Activo> activos = new ArrayList<>();
        String sql="SELECT * FROM  Activos WHERE id_estado IS NOT  NULL";
        EmpleadoDAO empleadoDAO= new EmpleadoDAO();
        EstadoActivoDAO estadoActivoDAO=new EstadoActivoDAO();
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {

            while (rs.next()) {
                Activo activo = new Activo();

                activo.setIdActivo(rs.getInt("id_activo"));
                activo.setNombre(rs.getString("nombre"));
                activo.setMarca(rs.getString("marca"));
                activo.setNroSerie(rs.getString("nro_serie"));

                int id_Estado=rs.getInt("id_estado");
                EstadoActivo estadoActivo=estadoActivoDAO.getById(id_Estado);
                activo.setEstado(estadoActivo);

                int idEmpleado=rs.getInt("id_empleado");
                Empleado e= empleadoDAO.getById(idEmpleado);
                activo.setEmpleado(e);

                activos.add(activo);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar los activos.");
            System.err.println("Detalle: " + e.getMessage());
        }

        return activos;
    }
}
