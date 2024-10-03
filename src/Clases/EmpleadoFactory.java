package Clases;

import java.util.Date;

public class EmpleadoFactory {

    public static Empleado crearEmpleado(String tipo, int id, String nombre, int edad, String sexo, Date fechaContratacion,
                                         String beneficios, Float salarioBase, Date fechaFinalContrato, Float tasaPorHora) {
        if ("permanente".equalsIgnoreCase(tipo)) {
            if (beneficios == null || salarioBase == null) {
                throw new IllegalArgumentException("Beneficios y salario base son obligatorios para empleados permanentes");
            }
            return new EmpleadoPermanente(id, nombre, edad, sexo, fechaContratacion, beneficios, salarioBase);
        } else if ("temporal".equalsIgnoreCase(tipo)) {
            if (fechaFinalContrato == null || tasaPorHora == null) {
                throw new IllegalArgumentException("Fecha final de contrato y tasa por hora son obligatorias para empleados temporales");
            }
            return new EmpleadoTemporal(id, nombre, edad, sexo, fechaContratacion, fechaFinalContrato, tasaPorHora);
        } else {
            throw new IllegalArgumentException("Tipo de empleado no válido: " + tipo);
        }
    }
}

