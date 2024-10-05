package Controlador;

import Clases.Empleado;
import Clases.Departamento;
import java.util.List;

public class ReporteController {

    // Metodo para generar reporte de desempeño individual de un empleado
    public String generarReporteIndividual(Empleado empleado) {
        if (empleado != null) {
            StringBuilder reporte = new StringBuilder();
            reporte.append("Reporte de Desempeño - Empleado: ").append(empleado.getNombre()).append("\n")
                    .append("---------------------------------------------------\n")
                    .append("ID: ").append(empleado.getId()).append("\n");

            Departamento departamento = empleado.getDepartamento();
            if (departamento != null) {
                reporte.append("Departamento: ").append(departamento.getNombre()).append("\n");
            } else {
                reporte.append("Departamento: No asignado\n");
            }

            reporte.append("Rol: ").append(empleado.getRol()).append("\n")
                    .append("Desempeño: ").append(empleado.getDesempeno()).append("/100\n")
                    .append("Fecha de Ingreso: ").append(empleado.getFechaIngreso()).append("\n")
                    .append("---------------------------------------------------\n");
            return reporte.toString();
        } else {
            return "Empleado no válido.";
        }
    }

    // Metodo para generar reporte de desempeño de un departamento
    public String generarReporteDepartamento(Departamento departamento) {
        if (departamento != null) {
            StringBuilder reporte = new StringBuilder();
            reporte.append("Reporte de Desempeño - Departamento: ").append(departamento.getNombre()).append("\n")
                    .append("---------------------------------------------------\n");

            List<Empleado> empleados = departamento.getEmpleados();
            if (empleados == null || empleados.isEmpty()) {
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

}

