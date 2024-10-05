package Clases;

import java.util.Date;

public class EmpleadoPermanente extends Empleado {
        public static Departamento departamento;
        private String beneficios;
        private float salarioBase;

    // Constructor
    public EmpleadoPermanente(int id, String nombre, int edad, String sexo, Date fechaContratacion, String beneficios, float salarioBase) {
        super(id, nombre, edad, sexo, fechaContratacion, departamento);  // Llama correctamente al constructor de Empleado
        this.beneficios = beneficios;
        this.salarioBase = salarioBase;
    }

    // Getters y Setters
    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }

    public float getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(float salarioBase) {
        this.salarioBase = salarioBase;
    }

    // Implementación del metodo abstracto para calcular salario
    @Override
    public double calcularSalario() {
        return salarioBase;  // Puedes modificar esta fórmula si hay otros cálculos de salario
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

    @Override
    public void setHorasTrabajadas(int horas) {

    }

    // Metodos adicionales
    @Override
    public void crear() {
        System.out.println("Creando empleado permanente: " + getNombre());
    }

    @Override
    public void actualizar(String nombre, int edad, String sexo) {
        setNombre(nombre);
        setEdad(edad);
        setSexo(sexo);
        System.out.println("Empleado Permanente actualizado: " + getNombre());
    }

    @Override
    public void eliminar() {
        System.out.println("Empleado Permanente eliminado: " + getNombre());
    }

    @Override
    public String getDetalles() {
        return "Empleado Permanente - Nombre: " + getNombre() + ", Edad: " + getEdad() + ", Sexo: " + getSexo() +
                ", Beneficios: " + beneficios + ", Salario Base: " + salarioBase;
    }
}

