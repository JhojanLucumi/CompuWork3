package Controlador;

import Clases.Empleado;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoController {
    private final List<Empleado> empleados;

    // Constructor
    public EmpleadoController() {
        this.empleados = new ArrayList<>();
    }

    // Metodo para agregar un empleado
    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
        if (empleado.getDepartamento() != null) {  // Verifica si el empleado tiene un departamento asignado
            empleado.getDepartamento().agregarEmpleado(empleado);
            System.out.println("Empleado " + empleado.getNombre() + " agregado al departamento " + empleado.getDepartamento().getNombre());
        } else {
            System.out.println("Empleado " + empleado.getNombre() + " agregado sin departamento.");
        }
    }

    // Metodo para eliminar un empleado por ID
    public void eliminarEmpleado(int id) {
        Empleado empleado = obtenerEmpleadoPorId(id);
        if (empleado != null) {
            if (empleado.getDepartamento() != null) {
                empleado.getDepartamento().removerEmpleado(id);
                System.out.println("Empleado " + empleado.getNombre() + " eliminado del departamento " + empleado.getDepartamento().getNombre());
            }
            empleados.remove(empleado);
            System.out.println("Empleado " + empleado.getNombre() + " eliminado correctamente.");
        } else {
            System.out.println("Empleado con ID " + id + " no encontrado.");
        }
    }

    // Metodo para obtener un empleado por ID
    private Empleado obtenerEmpleadoPorId(int id) {
        return empleados.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Metodo para obtener la lista de empleados
    public List<Empleado> obtenerEmpleados() {
        return empleados;
    }
}
