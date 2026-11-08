package org.proyecto.dao;

import org.proyecto.config.Conexion;
import org.proyecto.model.Empleado;
import org.proyecto.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public Usuario getByUsername(String username) {
        Usuario usuario = null;
        String sql = "SELECT * FROM Usuarios WHERE username = ?";
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();

                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setUsername(rs.getString("username"));
                    usuario.setPassword(rs.getString("password"));

                    int idEmpleado = rs.getInt("id_empleado");
                    Empleado empleado = empleadoDAO.getById(idEmpleado);
                    usuario.setEmpleado(empleado);

                    usuario.setEstado(rs.getString("estado"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar el usuario: " + username);
            System.err.println("Detalle: " + e.getMessage());
        }

        return usuario;
    }

}