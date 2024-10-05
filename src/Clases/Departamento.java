package Clases;

import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private int id;
    private String nombre;
    private String jefeDepartamento;
    private String descripcion;
    private final List<Empleado> empleadosAsignados;

    // Constructor
    public Departamento(int id, String nombre, String jefeDepartamento, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.jefeDepartamento = jefeDepartamento;
        this.descripcion = descripcion;
        this.empleadosAsignados = new ArrayList<>();
    }

    // Constructor adicional con solo el nombre (si es necesario)
    public Departamento(String nombre) {
        this.id = 0; // O algún valor predeterminado
        this.nombre = nombre;
        this.jefeDepartamento = ""; // Valor por defecto
        this.descripcion = ""; // Valor por defecto
        this.empleadosAsignados = new ArrayList<>();
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

    public String getJefeDepartamento() {
        return jefeDepartamento;
    }

    public void setJefeDepartamento(String jefeDepartamento) {
        this.jefeDepartamento = jefeDepartamento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Empleado> getEmpleadosAsignados() {
        return empleadosAsignados;
    }

    // Metodos para agregar y remover empleados
    public void agregarEmpleado(Empleado empleado) {
        empleadosAsignados.add(empleado);
        System.out.println("Empleado: " + empleado.getNombre() + " asignado al departamento: " + nombre);
    }

    public void removerEmpleado(int id) {
        Empleado empleado = buscarEmpleado(id);
        if (empleado != null) {
            empleadosAsignados.remove(empleado);
            System.out.println("Empleado con ID: " + id + " removido del departamento: " + nombre);
        } else {
            System.out.println("Empleado con ID: " + id + " no encontrado en el departamento: " + nombre);
        }
    }

    // Metodo para obtener detalles del departamento
    public String getDetalles() {
        StringBuilder detalles = new StringBuilder("Departamento: ").append(nombre)
                .append(", Jefe: ").append(jefeDepartamento).append("\n")
                .append("Empleados Asignados: \n");

        if (empleadosAsignados.isEmpty()) {
            detalles.append("No hay empleados asignados.\n");
        } else {
            for (Empleado empleado : empleadosAsignados) {
                detalles.append("- ").append(empleado.getNombre())
                        .append(" (ID: ").append(empleado.getId()).append(")\n");
            }
        }

        return detalles.toString();
    }

    // Metodo para buscar empleado por ID
    public Empleado buscarEmpleado(int id) {
        for (Empleado empleado : empleadosAsignados) {
            if (empleado.getId() == id) {
                return empleado;
            }
        }
        return null;
    }

    // Métodos para obtener empleados y jefe
    public List<Empleado> getEmpleados() {
        return empleadosAsignados;
    }

    public String getJefe() {
        return jefeDepartamento; // Retorna el nombre del jefe
    }

    public void eliminarEmpleado(EmpleadoPermanente empleado) {
    }
}


