package Controlador;

import Clases.Departamento;
import Clases.Empleado;

import java.util.ArrayList;
import java.util.List;

public class DepartamentoController {
    // Lista de departamentos
    private final List<Departamento> departamentos;
    private int siguienteIdDepartamento;

    // Constructor
    public DepartamentoController() {
        departamentos = new ArrayList<>();
        siguienteIdDepartamento = 1;  // Inicializa con un ID único inicial
    }

    // Metodo para agregar un nuevo departamento
    public void agregarDepartamento(String nombre) {
        int id = siguienteIdDepartamento++;  // Genera un ID único
        String jefeDepartamento = "";  // Si no se pasa un jefe, se deja vacío
        String descripcion = "";       // Si no se pasa una descripción, se deja vacío

        Departamento departamento = new Departamento(id, nombre, jefeDepartamento, descripcion);
        departamentos.add(departamento);
        System.out.println("Departamento " + nombre + " agregado correctamente.");
    }

    // Metodo para eliminar un departamento por nombre
    public boolean eliminarDepartamento(String nombre) {
        Departamento departamento = obtenerDepartamento(nombre);
        if (departamento != null) {
            departamentos.remove(departamento);
            System.out.println("Departamento " + nombre + " eliminado correctamente.");
            return true;
        }
        System.out.println("Departamento " + nombre + " no encontrado.");
        return false;
    }

    // Metodo para obtener un departamento por nombre
    public Departamento obtenerDepartamento(String nombre) {
        return departamentos.stream()
                .filter(departamento -> departamento.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    // Metodo para agregar un empleado a un departamento específico
    public boolean agregarEmpleadoADepartamento(String nombreDepartamento, Empleado empleado) {
        Departamento departamento = obtenerDepartamento(nombreDepartamento);
        if (departamento != null) {
            departamento.agregarEmpleado(empleado);
            System.out.println("Empleado agregado al departamento " + nombreDepartamento);
            return true;
        }
        System.out.println("Departamento " + nombreDepartamento + " no encontrado.");
        return false;
    }

    // Metodo para eliminar un empleado de un departamento
    public boolean eliminarEmpleadoDeDepartamento(String nombreDepartamento, int idEmpleado) {
        Departamento departamento = obtenerDepartamento(nombreDepartamento);
        if (departamento != null) {
            Empleado empleado = departamento.buscarEmpleado(idEmpleado);
            if (empleado != null) {
                departamento.removerEmpleado(idEmpleado);
                System.out.println("Empleado con ID " + idEmpleado + " eliminado del departamento " + nombreDepartamento);
                return true;
            } else {
                System.out.println("Empleado con ID " + idEmpleado + " no encontrado en el departamento " + nombreDepartamento);
            }
        } else {
            System.out.println("Departamento " + nombreDepartamento + " no encontrado.");
        }
        return false;
    }

    // Metodo para listar todos los departamentos
    public List<Departamento> listarDepartamentos() {
        if (departamentos.isEmpty()) {
            System.out.println("No hay departamentos disponibles.");
        } else {
            for (Departamento departamento : departamentos) {
                System.out.println("Departamento: " + departamento.getNombre());
            }
        }
        return departamentos;
    }

    // Metodo para obtener todos los empleados de un departamento
    public List<Empleado> obtenerEmpleadosDeDepartamento(String nombreDepartamento) {
        Departamento departamento = obtenerDepartamento(nombreDepartamento);
        if (departamento != null) {
            return departamento.getEmpleadosAsignados();
        }
        System.out.println("Departamento " + nombreDepartamento + " no encontrado.");
        return new ArrayList<>();  // Retorna lista vacía si no se encuentra el departamento
    }
}
