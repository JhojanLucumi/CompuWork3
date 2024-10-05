package Clases;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class EmpleadoTemporal extends Empleado {
    private Date fechaFinalContrato;
    private float tasaPorHora;       // Pago por hora
    private int horasTrabajadas;     // Horas trabajadas para calcular el salario

    // Constructor con los parámetros correctos
    public EmpleadoTemporal(int id, String nombre, int edad, String sexo, Date fechaContratacion, Date fechaFinalContrato, float tasaPorHora) {
        super(id, nombre, edad, sexo, fechaContratacion, null);  // Enviar null si no tienes un departamento
        this.fechaFinalContrato = fechaFinalContrato;
        this.tasaPorHora = tasaPorHora;
        this.horasTrabajadas = 0;  // Inicializamos las horas trabajadas a 0
    }

    public EmpleadoTemporal(int id, String carlos, int edad, String m, Date fechaContratacion, Departamento recursosHumanos, int tasaPorHora) {
        super(id, carlos, edad, m, fechaContratacion, recursosHumanos);
    }

    // Getters y Setters
    public Date getFechaFinalContrato() {
        return fechaFinalContrato;
    }

    public void setFechaFinalContrato(Date fechaFinalContrato) {
        this.fechaFinalContrato = fechaFinalContrato;
    }

    public float getTasaPorHora() {
        return tasaPorHora;
    }

    public void setTasaPorHora(float tasaPorHora) {
        this.tasaPorHora = tasaPorHora;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    // Calcula la duración del contrato en días
    public long getDuracionContratoEnDias() {
        long diffInMillies = Math.abs(fechaFinalContrato.getTime() - getFechaContratacion().getTime());
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    // Implementación del metodo abstracto para calcular salario
    @Override
    public double calcularSalario() {
        // Cálculo del salario basado en horas trabajadas y la tasa por hora
        return tasaPorHora * horasTrabajadas;
    }

    @Override
    public String getRol() {
        return "";
    }

    @Override
    public String getDesempeno() {
        return "";
    }

    @Override
    public Date getFechaIngreso() {
        return null;
    }

    // Implementación de métodos abstractos
    @Override
    public void crear() {
        System.out.println("Creando empleado temporal: " + getNombre());
    }

    @Override
    public void actualizar(String nombre, int edad, String sexo) {
        setNombre(nombre);
        setEdad(edad);
        setSexo(sexo);
        System.out.println("Empleado Temporal actualizado: " + getNombre());
    }

    @Override
    public void eliminar() {
        System.out.println("Empleado Temporal eliminado: " + getNombre());
    }

    @Override
    public String getDetalles() {
        return "Empleado Temporal - Nombre: " + getNombre() + ", Edad: " + getEdad() + ", Sexo: " + getSexo() +
                ", Tasa por Hora: " + tasaPorHora + ", Horas Trabajadas: " + horasTrabajadas +
                ", Duración del contrato: " + getDuracionContratoEnDias() + " días.";
    }
}




