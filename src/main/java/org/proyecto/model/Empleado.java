package org.proyecto.model;

public class Empleado {
    private int idEmpleado;
    private String nombre;
    private String area;
    private double sueldo;
    private String estado;
    //private List<Activo> activos;

    public Empleado() {
    }

    public Empleado(String nombre, String area, double sueldo, String estado) {
        this.nombre = nombre;
        this.area = area;
        this.sueldo = sueldo;
        this.estado = estado;
    }

    public Empleado(int idEmpleado, String nombre, String area, double sueldo, String estado) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.area = area;
        this.sueldo = sueldo;
        this.estado = estado;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String informacion() {
        String informacion;

        informacion = "ID: " + idEmpleado + "\n" +
                "Nombre: " + nombre + "\n" +
                "Área: " + area + "\n" +
                "Sueldo: " + sueldo + "\n" +
                "Estado: " + estado;

        return informacion;
    }

    @Override
    public String toString() {
        String informacion;

        informacion = "ID: " + idEmpleado + "\n" +
                "Nombre: " + nombre + "\n" +
                "Área: " + area + "\n" +
                "Sueldo: " + sueldo + "\n" +
                "Estado: " + estado;

        return informacion;
    }
}