package Clases;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReporteDesempenio {
    private final Date fechaGeneracion;
    private final int puntaje;
    private final String observaciones;

    // Constructor
    public ReporteDesempenio(Date fechaGeneracion, int puntaje, String observaciones) {
        this.fechaGeneracion = fechaGeneracion;
        this.puntaje = puntaje;
        this.observaciones = observaciones;
    }

    // Metodo para generar un reporte individual de un empleado
    public String generarReporteIndividual(Empleado empleado) {
        StringBuilder reporte = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        reporte.append("************** Reporte de Desempeño **************\n");
        reporte.append("Fecha: ").append(sdf.format(fechaGeneracion)).append("\n");
        reporte.append("Nombre: ").append(empleado.getNombre()).append("\n");
        reporte.append("ID: ").append(empleado.getId()).append("\n");
        if (empleado.getDepartamento() != null) {
            reporte.append("Departamento: ").append(empleado.getDepartamento().getNombre()).append("\n");
        } else {
            reporte.append("Departamento: No asignado\n");
        }
        reporte.append("Salario: ").append(empleado.calcularSalario()).append("\n");
        reporte.append("Puntaje: ").append(puntaje).append("\n");
        reporte.append("Observaciones: ").append(observaciones).append("\n");
        reporte.append("****************************************************\n");
        return reporte.toString();
    }

    // Metodo para generar un reporte detodo un departamento
    public String generarReportePorDepartamento(Departamento departamento) {
        StringBuilder reporte = new StringBuilder();

        reporte.append("************** Reporte de Desempeño **************\n");
        reporte.append("Departamento: ").append(departamento.getNombre()).append("\n");
        reporte.append("Jefe del Departamento: ").append(departamento.getJefeDepartamento()).append("\n");
        reporte.append("Descripción: ").append(departamento.getDescripcion()).append("\n");
        reporte.append("Empleados:\n");

        List<Empleado> empleados = departamento.getEmpleadosAsignados();
        if (empleados.isEmpty()) {
            reporte.append("No hay empleados asignados en este departamento.\n");
        } else {
            for (Empleado empleado : empleados) {
                reporte.append(generarReporteIndividual(empleado)).append("\n");
            }
        }

        reporte.append("****************************************************\n");
        return reporte.toString();
    }
}

