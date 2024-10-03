package Controlador;

import Clases.Empleado;
import Clases.Departamento;
import java.util.List;

public class ReporteController {

    // Metodo para generar reporte de desempeño individual de un empleado
    public String generarReporteIndividual(Empleado empleado) {
        if (empleado != null) {
            StringBuilder reporte = new StringBuilder();
            reporte.append("Reporte de Desempeño - Empleado: ").append(empleado.getNombre()).append("\n");
            reporte.append("---------------------------------------------------\n");
            reporte.append("ID: ").append(empleado.getId()).append("\n");

            Departamento departamento = empleado.getDepartamento();
            if (departamento != null) {
                reporte.append("Departamento: ").append(departamento.getNombre()).append("\n");
            } else {
                reporte.append("Departamento: No asignado\n");
            }

            reporte.append("Rol: ").append(empleado.getRol()).append("\n");
            reporte.append("Desempeño: ").append(empleado.getDesempeno()).append("/100\n");
            reporte.append("Fecha de Ingreso: ").append(empleado.getFechaIngreso()).append("\n");
            reporte.append("---------------------------------------------------\n");
            return reporte.toString();
        } else {
            return "Empleado no válido.";
        }
    }

    // Metodo para generar reporte de desempeño de un departamento
    public String generarReporteDepartamento(Departamento departamento) {
        if (departamento != null) {
            StringBuilder reporte = new StringBuilder();
            reporte.append("Reporte de Desempeño - Departamento: ").append(departamento.getNombre()).append("\n");
            reporte.append("---------------------------------------------------\n");

            List<Empleado> empleados = departamento.getEmpleados();
            if (empleados.isEmpty()) {
                reporte.append("No hay empleados asignados a este departamento.\n");
            } else {
                for (Empleado empleado : empleados) {
                    reporte.append("Empleado: ").append(empleado.getNombre())
                            .append(" - Desempeño: ").append(empleado.getDesempeno()).append("/100\n");
                }
            }
            reporte.append("---------------------------------------------------\n");
            return reporte.toString();
        } else {
            return "Departamento no válido.";
        }
    }

    // Metodo para generar un reporte resumen de todos los departamentos
    public String generarReporteGeneral(List<Departamento> departamentos) {
        if (departamentos != null && !departamentos.isEmpty()) {
            StringBuilder reporte = new StringBuilder();
            reporte.append("Reporte General de Desempeño\n");
            reporte.append("---------------------------------------------------\n");

            for (Departamento departamento : departamentos) {
                reporte.append("Departamento: ").append(departamento.getNombre()).append("\n");

                List<Empleado> empleados = departamento.getEmpleados();
                if (empleados.isEmpty()) {
                    reporte.append("  No hay empleados asignados.\n");
                } else {
                    for (Empleado empleado : empleados) {
                        reporte.append("  Empleado: ").append(empleado.getNombre())
                                .append(" - Desempeño: ").append(empleado.getDesempeno()).append("/100\n");
                    }
                }
                reporte.append("---------------------------------------------------\n");
            }
            return reporte.toString();
        } else {
            return "No hay departamentos disponibles para generar un reporte.";
        }
    }
}
