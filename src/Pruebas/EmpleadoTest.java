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
        EmpleadoPermanente empleado = new EmpleadoPermanente(123, "Ana", 30, "F", new Date(), "Beneficios Médicos", 5000.0F);

        // Asocia el empleado con el departamento
        departamento.agregarEmpleado(empleado);
        controller.agregarEmpleado(empleado);

        assertEquals(1, controller.obtenerEmpleados().size());
        assertTrue(controller.obtenerEmpleados().contains(empleado));
        assertTrue(departamento.getEmpleados().contains(empleado), "El departamento debería contener al empleado agregado");
    }

    @Test
    public void testEliminarEmpleado() {
        EmpleadoController controller = new EmpleadoController();
        Departamento departamento = new Departamento("IT");
        EmpleadoPermanente empleado = new EmpleadoPermanente(123, "Ana", 30, "F", new Date(), "Beneficios Médicos", 5000.0F);

        // Asocia el empleado con el departamento
        departamento.agregarEmpleado(empleado);
        controller.agregarEmpleado(empleado);

        // Elimina el empleado
        controller.eliminarEmpleado(123);
        departamento.eliminarEmpleado(empleado); // Asegura que también se elimina del departamento

        assertEquals(0, controller.obtenerEmpleados().size());
        assertFalse(departamento.getEmpleados().contains(empleado), "El empleado debería haber sido eliminado del departamento");
    }
}

