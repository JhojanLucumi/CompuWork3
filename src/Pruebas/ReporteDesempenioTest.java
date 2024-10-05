package Pruebas;

import Clases.Empleado;
import Clases.EmpleadoPermanente;
import Clases.EmpleadoTemporal;
import Clases.Departamento;
import Controlador.ReporteController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ReporteDesempenioTest {

    private ReporteController reporteController;
    private Departamento departamentoIT;
    private Empleado empleado1;
    private Empleado empleado2;

    @BeforeEach
    void setUp() {
        // Inicialización antes de cada prueba
        reporteController = new ReporteController();

        // Crear un departamento y algunos empleados
        departamentoIT = new Departamento("IT");

        empleado1 = new EmpleadoPermanente(1, "Ana", 30, "F", new Date(), "Beneficios Médicos", 5000);
        empleado2 = new EmpleadoTemporal(2, "Luis", 25, "M", new Date(), departamentoIT, 100);

        // Agregar empleados al departamento
        departamentoIT.agregarEmpleado(empleado1);
        departamentoIT.agregarEmpleado(empleado2);
    }

    @Test
    void testGenerarReporteIndividual() {
        // Generar un reporte para un empleado individual
        String reporte = reporteController.generarReporteIndividual(empleado1);

        assertNotNull(reporte, "El reporte no debe ser nulo");
        assertTrue(reporte.contains("Ana"), "El reporte debe contener el nombre del empleado 'Ana'");
        assertTrue(reporte.contains("5000"), "El reporte debe incluir el salario del empleado");
    }

    @Test
    void testGenerarReporteDepartamento() {
        // Generar un reporte para un departamento completo
        String reporteDepartamento = reporteController.generarReporteDepartamento(departamentoIT);

        assertNotNull(reporteDepartamento, "El reporte del departamento no debe ser nulo");
        assertTrue(reporteDepartamento.contains("IT"), "El reporte debe mencionar el nombre del departamento 'IT'");
        assertTrue(reporteDepartamento.contains("Ana"), "El reporte debe incluir al empleado 'Ana'");
        assertTrue(reporteDepartamento.contains("Luis"), "El reporte debe incluir al empleado 'Luis'");
    }

    @Test
    void testReporteConMetricasDeDesempenio() {
        // Prueba donde los empleados tienen diferentes métricas de desempeño
        empleado1.setHorasTrabajadas(160);
        empleado2.setHorasTrabajadas(120);

        String reporte = reporteController.generarReporteIndividual(empleado1);

        assertTrue(reporte.contains("160"), "El reporte debe incluir las horas trabajadas del empleado");
        assertFalse(reporte.contains("120"), "El reporte del empleado 1 no debe incluir información del empleado 2");
    }

    @Test
    void testReporteVacio() {
        // Probar si no hay empleados en un departamento
        Departamento departamentoVacio = new Departamento("Sin Empleados");
        String reporte = reporteController.generarReporteDepartamento(departamentoVacio);

        assertNotNull(reporte, "El reporte no debe ser nulo incluso si no hay empleados");
        assertTrue(reporte.contains("No hay empleados"), "El reporte debe indicar que no hay empleados en el departamento");
    }
}




