package org.proyecto.dao;

import org.proyecto.config.Conexion;
import org.proyecto.model.EstadoActivo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoActivoDAO {
    public  boolean  insert(EstadoActivo estado){
        String sql= "INSERT INTO Estados_Activo (nombre, descripcion) VALUES (?,?)";
        try (Connection CNX = Conexion.getConnection();
             PreparedStatement ps= CNX.prepareStatement(sql);) {
            ps.setString(1,estado.getNombre());
            ps.setString(2,estado.getDescripcion());

            int filas_afectadas = ps.executeUpdate();

            return filas_afectadas >0;//Si el valor es tru significa que las filas fueron afectadas

        }catch (SQLException ES){
            if (ES.getErrorCode() == 2627 || ES.getErrorCode() == 2601
                    || ES.getMessage().contains("UNIQUE KEY constraint")) {
                System.out.println("No se puede ingresar el estado \"" + estado.getNombre()
                        + "\": ya existe un estado con ese nombre.");
                return false;
            }
            System.err.println("Error al registrar un estado.");
            System.err.println("Detalle: " + ES.getMessage());
            return false;

        }
    }

    public List<EstadoActivo> getAll(){
        List<EstadoActivo> Lista_estados = new ArrayList<>();
        String sql="select * from Estados_Activo";
        try (Connection CNX = Conexion.getConnection();
             PreparedStatement PS = CNX.prepareStatement(sql);
             ResultSet rs= PS.executeQuery() ) {

            while (rs.next()){
                EstadoActivo estado=new EstadoActivo();

                estado.setId_estado(rs.getInt("id_estado"));
                estado.setNombre(rs.getString("nombre"));
                estado.setDescripcion(rs.getString("descripcion"));


                Lista_estados.add(estado);
            }


        }catch (SQLException ES){
            //throw new RuntimeException(e);
            System.err.println("Error al listar todos los estados.");
            System.err.println("Detalle: " + ES.getMessage());

        }

        return Lista_estados;
    }

    public EstadoActivo getById(int id) {
        EstadoActivo estado = null;
        String sql = "select * from Estados_Activo where id_estado = ?";
        try (Connection CNX = Conexion.getConnection();
             PreparedStatement PS = CNX.prepareStatement(sql)) {

            PS.setInt(1, id);
            try (ResultSet rs = PS.executeQuery()) {
                if (rs.next()) {
                    estado=new EstadoActivo();
                    estado.setId_estado(rs.getInt("id_estado"));
                    estado.setNombre(rs.getString("nombre"));
                    estado.setDescripcion(rs.getString("descripcion"));
                }
            }

        } catch (SQLException ES) {
            //throw new RuntimeException(e);
            System.err.println("Error al registrar un estado.");
            System.err.println("Detalle: " + ES.getMessage());

        }
        return estado;



    }

    public boolean update(EstadoActivo est){
        String sql="UPDATE Estados_Activo set nombre = ? , descripcion = ? where id_estado = ?";
        try (Connection CNX= Conexion.getConnection();
             PreparedStatement ps=CNX.prepareStatement(sql)){

            ps.setString(1,est.getNombre());
            ps.setString(2,est.getDescripcion());
            ps.setInt(3,est.getId_estado());
            int filas_afectadas= ps.executeUpdate();
            return filas_afectadas > 0;

        }catch (SQLException e){
            if (e.getErrorCode() == 2627 || e.getErrorCode() == 2601
                    || e.getMessage().contains("UNIQUE KEY constraint")) {
                System.out.println("Error: ya existe otro estado con el nombre \"" + est.getNombre() + "\".");
                return false;
            }
            System.err.println("Error al actualizar un estado");
            System.err.println("Detalle: " + e.getMessage());
            return false;
        }

    }
    //Creacion de delete fisico
    public boolean DeleteReal(int ID){
        String SQL="DELETE FROM Estados_Activo WHERE id_estado = ? ";
        try (Connection CNX = Conexion.getConnection();
             PreparedStatement ps =CNX.prepareStatement(SQL) ) {

            ps.setInt(1,ID);

            int filas_afectadas=ps.executeUpdate();
            return filas_afectadas > 0;

        }catch (SQLException ES){
            if (ES.getErrorCode() == 547) {
                System.out.println("No se puede eliminar: existen activos que usan este estado.");
                return false;
            }
            System.err.println("Error al eliminar un estado");
            System.err.println("Detalle: " + ES.getMessage());
            return false;
        }
    }

}