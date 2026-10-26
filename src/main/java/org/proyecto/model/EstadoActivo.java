package org.proyecto.model;

public class EstadoActivo {
    private int id_estado;
    private String nombre;
    private String descripcion;

    public EstadoActivo(){

    }
    public EstadoActivo(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public EstadoActivo(int idEstado, String nombre, String descripcion) {
        this.id_estado = idEstado;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String informacion() {
        String informacion;

        informacion = "ID: " + id_estado + "\n" +
                "Nombre: " + nombre + "\n" +
                "Descripcion: " + descripcion;

        return informacion;
    }

    @Override
    public String toString() {
        return nombre;
    }
}