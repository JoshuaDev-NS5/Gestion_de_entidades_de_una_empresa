package org.proyecto.model;

public class Usuario {
    private int idUsuario;
    private String username;
    private String password;
    private Empleado empleado;
    private String estado;

    public Usuario() {
    }

    public Usuario(String username, String password, Empleado empleado, String estado) {
        this.username = username;
        this.password = password;
        this.empleado = empleado;
        this.estado = estado;
    }

    public Usuario(int idUsuario, String username, String password, Empleado empleado, String estado) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.empleado = empleado;
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}