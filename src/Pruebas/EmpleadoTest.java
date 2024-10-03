package Pruebas;

import Controlador.EmpleadoController;
import Clases.Departamento;
import Clases.EmpleadoPermanente;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class EmpleadoTest {

    @Test
    public void testAgregarEmpleado() {
        EmpleadoController controller = new EmpleadoController();
        Departamento departamento = new Departamento("IT");
        EmpleadoPermanente empleado = new EmpleadoPermanente("Jhojan", 123, 30, "M", new Date(), departamento, 5000.0);

        controller.agregarEmpleado(empleado);

        assertEquals(1, controller.obtenerEmpleados().size());
        assertTrue(controller.obtenerEmpleados().contains(empleado));
        assertTrue(departamento.getEmpleados().contains(empleado));
    }

    @Test
    public void testEliminarEmpleado() {
        EmpleadoController controller = new EmpleadoController();
        Departamento departamento = new Departamento("IT");
        EmpleadoPermanente empleado = new EmpleadoPermanente("Jhojan", 123, 30, "M", new Date(), departamento, 5000.0);

        controller.agregarEmpleado(empleado);
        controller.eliminarEmpleado(123);

        assertEquals(0, controller.obtenerEmpleados().size());
        assertFalse(departamento.getEmpleados().contains(empleado));
    }
}

