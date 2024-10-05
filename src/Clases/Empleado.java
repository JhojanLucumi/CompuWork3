package Clases;

import java.util.Date;

public abstract class Empleado {
    private int id;
    private String nombre;
    private int edad;
    private String sexo;
    private Date fechaContratacion;
    private Departamento departamento;

    // Constructor
    public Empleado(int id, String nombre, int edad, String sexo, Date fechaContratacion, Departamento departamento) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.fechaContratacion = fechaContratacion;
        this.departamento = departamento;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    // Metodo abstracto para calcular salario que será implementado por las subclases
    public abstract double calcularSalario();

    public abstract String getRol();

    public abstract String getDesempeno();

    public abstract Date getFechaIngreso();

    public abstract void setHorasTrabajadas(int horas);

    // Metodos adicionales
    public abstract void crear();

    public abstract void actualizar(String nombre, int edad, String sexo);

    public abstract void eliminar();

    public abstract String getDetalles();
}

