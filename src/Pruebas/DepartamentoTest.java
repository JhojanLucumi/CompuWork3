package Pruebas;

import Controlador.DepartamentoController;
import Clases.Departamento;
import Clases.Empleado;
import Clases.EmpleadoPermanente;
import Clases.EmpleadoTemporal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DepartamentoTest {

    private DepartamentoController departamentoController;

    @BeforeEach
    void setUp() {
        // Inicializa un nuevo controlador de departamentos antes de cada prueba
        departamentoController = new DepartamentoController();
    }

    @Test
    void testAgregarDepartamento() {
        // Agregar un departamento y comprobar si fue agregado correctamente
        departamentoController.agregarDepartamento("IT");
        Departamento dept = departamentoController.obtenerDepartamento("IT");

        assertNotNull(dept, "El departamento no debe ser nulo");
        assertEquals("IT", dept.getNombre(), "El nombre del departamento debe ser 'IT'");
    }

    @Test
    void testEliminarDepartamento() {
        // Agregar un departamento y luego eliminarlo
        departamentoController.agregarDepartamento("Marketing");
        boolean eliminado = departamentoController.eliminarDepartamento("Marketing");

        assertTrue(eliminado, "El departamento 'Marketing' debe ser eliminado");
        assertNull(departamentoController.obtenerDepartamento("Marketing"), "El departamento 'Marketing' no debe existir");
    }

    @Test
    void testAgregarEmpleadoADepartamento() {
        // Agregar un departamento y un empleado
        departamentoController.agregarDepartamento("IT");
        Empleado empleado;
        empleado = new EmpleadoPermanente(1, "Jhojan", 20, "M", new Date(), "Seguro", 5000F);
        boolean agregado = departamentoController.agregarEmpleadoADepartamento("IT", empleado);

        assertTrue(agregado, "El empleado debe ser agregado al departamento 'IT'");

        List<Empleado> empleados = departamentoController.obtenerEmpleadosDeDepartamento("IT");
        assertEquals(1, empleados.size(), "El departamento 'IT' debe tener 1 empleado");
        assertEquals("Jhojan", empleados.getFirst().getNombre(), "El nombre del empleado debe ser 'Jhojan'");
    }

    @Test
    void testEliminarEmpleadoDeDepartamento() {
        // Agregar un departamento y un empleado, luego eliminar al empleado
        departamentoController.agregarDepartamento("Recursos Humanos");
        Empleado empleado = new EmpleadoTemporal(2, "Carlos", 25, "M", new Date(), departamentoController.obtenerDepartamento("Recursos Humanos"), 100);
        departamentoController.agregarEmpleadoADepartamento("Recursos Humanos", empleado);

        boolean eliminado = departamentoController.eliminarEmpleadoDeDepartamento("Recursos Humanos", 2);

        assertTrue(eliminado, "El empleado debe ser eliminado del departamento 'Recursos Humanos'");

        List<Empleado> empleados = departamentoController.obtenerEmpleadosDeDepartamento("Recursos Humanos");
        assertTrue(empleados.isEmpty(), "El departamento 'Recursos Humanos' no debe tener empleados después de eliminar");
    }

    @Test
    void testListarDepartamentos() {
        // Agregar varios departamentos y verificar si la lista es correcta
        departamentoController.agregarDepartamento("IT");
        departamentoController.agregarDepartamento("Ventas");

        List<Departamento> departamentos = departamentoController.listarDepartamentos();
        assertEquals(2, departamentos.size(), "Debe haber 2 departamentos en la lista");
    }

    @Test
    void testObtenerEmpleadosDeDepartamento() {
        // Agregar empleados a un departamento y obtener la lista
        departamentoController.agregarDepartamento("Soporte Técnico");
        Empleado empleado1 = new EmpleadoPermanente(3, "Ana", 30, "F", new Date(), "Beneficios Médicos", 4000F);
        Empleado empleado2 = new EmpleadoTemporal(4, "Luis", 25, "M", new Date(), departamentoController.obtenerDepartamento("Soporte Técnico"), 100);

        departamentoController.agregarEmpleadoADepartamento("Soporte Técnico", empleado1);
        departamentoController.agregarEmpleadoADepartamento("Soporte Técnico", empleado2);

        List<Empleado> empleados = departamentoController.obtenerEmpleadosDeDepartamento("Soporte Técnico");

        assertEquals(2, empleados.size(), "El departamento 'Soporte Técnico' debe tener 2 empleados");
        assertEquals("Ana", empleados.get(0).getNombre(), "El primer empleado debe ser 'Ana'");
        assertEquals("Luis", empleados.get(1).getNombre(), "El segundo empleado debe ser 'Luis'");
    }
}


