package main;

import Clases.Departamento;
import Clases.Empleado;
import Clases.EmpleadoPermanente;
import Clases.EmpleadoTemporal;
import Controlador.DepartamentoController;
import Controlador.ReporteController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Empleado> empleados = new ArrayList<>();
    private static List<Departamento> departamentos = new ArrayList<>();
    private static DepartamentoController departamentoController = new DepartamentoController();
    private static ReporteController reporteController = new ReporteController();

    public static void main(String[] args) {
        crearInterfazGrafica();
        // Puedes llamar a la consola como un metodo separado, si lo deseas.
    }

    private static void crearInterfazGrafica() {
        JFrame frame = new JFrame("Sistema de Gestión de Empleados y Departamentos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        JMenuBar menuBar = crearMenuBar();
        frame.setJMenuBar(menuBar);

        JPanel panel = new JPanel(new BorderLayout());
        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel);

        frame.setVisible(true);
    }

    private static JMenuBar crearMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Menú de empleados
        JMenu menuEmpleados = new JMenu("Empleados");
        menuEmpleados.add(crearMenuItem("Agregar Empleado", e -> agregarEmpleado()));
        menuEmpleados.add(crearMenuItem("Ver Empleados", e -> verEmpleados()));
        menuEmpleados.add(crearMenuItem("Actualizar Empleado", e -> actualizarEmpleado()));
        menuEmpleados.add(crearMenuItem("Eliminar Empleado", e -> eliminarEmpleado()));
        menuBar.add(menuEmpleados);

        // Menú de departamentos
        JMenu menuDepartamentos = new JMenu("Departamentos");
        menuDepartamentos.add(crearMenuItem("Crear Departamento", e -> agregarDepartamento()));
        menuDepartamentos.add(crearMenuItem("Ver Departamentos", e -> verDepartamentos()));
        menuDepartamentos.add(crearMenuItem("Modificar Departamento", e -> modificarDepartamento()));
        menuDepartamentos.add(crearMenuItem("Eliminar Departamento", e -> eliminarDepartamento()));
        menuDepartamentos.add(crearMenuItem("Asignar Empleado a Departamento", e -> asignarEmpleado()));
        menuBar.add(menuDepartamentos);

        return menuBar;
    }

    private static JMenuItem crearMenuItem(String texto, ActionListener listener) {
        JMenuItem menuItem = new JMenuItem(texto);
        menuItem.addActionListener(listener);
        return menuItem;
    }

    private static void agregarEmpleado() {
        JTextField idField = new JTextField();
        JTextField nombreField = new JTextField();
        JTextField edadField = new JTextField();
        String[] opcionesSexo = {"Hombre", "Mujer", "No binario"};
        JComboBox<String> sexoBox = new JComboBox<>(opcionesSexo);
        String[] opcionesTipo = {"Permanente", "Temporal"};
        JComboBox<String> tipoBox = new JComboBox<>(opcionesTipo);
        JTextField salarioBaseField = new JTextField(); // Solo para empleados permanentes
        JTextField tasaPorHoraField = new JTextField(); // Solo para empleados temporales

        Object[] message = {
                "ID:", idField,
                "Nombre:", nombreField,
                "Edad:", edadField,
                "Sexo:", sexoBox,
                "Tipo (permanente/temporal):", tipoBox,
                "Salario Base (solo para permanentes):", salarioBaseField,
                "Tasa por Hora (solo para temporales):", tasaPorHoraField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Agregar Empleado", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(idField.getText());
                String nombre = nombreField.getText();
                int edad = Integer.parseInt(edadField.getText());
                String sexo = (String) sexoBox.getSelectedItem();
                String tipo = (String) tipoBox.getSelectedItem();

                if (tipo.equalsIgnoreCase("Permanente")) {
                    float salarioBase = Float.parseFloat(salarioBaseField.getText());
                    empleados.add(EmpleadoFactory.crearEmpleado("permanente", id, nombre, edad, sexo, new Date(), "Seguro Médico", salarioBase, null, 0));
                    JOptionPane.showMessageDialog(null, "Empleado creado.");
                } else if (tipo.equalsIgnoreCase("Temporal")) {
                    float tasaPorHora = Float.parseFloat(tasaPorHoraField.getText());
                    empleados.add(EmpleadoFactory.crearEmpleado("temporal", id, nombre, edad, sexo, new Date(), null, 0, new Date(), tasaPorHora));
                    JOptionPane.showMessageDialog(null, "Empleado creado.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error en los datos ingresados. Por favor, verifica los campos.");
            }
        }
    }

    private static void verEmpleados() {
        // Implementar la lógica para ver empleados
    }

    private static void agregarDepartamento() {
        JTextField idField = new JTextField();
        JTextField nombreField = new JTextField();
        JTextField jefeField = new JTextField();
        JTextField descripcionField = new JTextField();

        Object[] message = {
                "ID:", idField,
                "Nombre del Departamento:", nombreField,
                "Jefe de Departamento:", jefeField,
                "Descripción:", descripcionField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Crear Departamento", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int id = Integer.parseInt(idField.getText());
            String nombre = nombreField.getText();
            String jefe = jefeField.getText();
            String descripcion = descripcionField.getText();

            departamentos.add(new Departamento(id, nombre, jefe, descripcion));
            JOptionPane.showMessageDialog(null, "Departamento creado.");
        }
    }

    private static void verDepartamentos() {
        // Implementar la lógica para ver departamentos
    }

    private static void modificarDepartamento() {
        // Implementar la lógica para modificar un departamento
    }

    private static void eliminarDepartamento() {
        // Implementar la lógica para eliminar un departamento
    }

    private static void asignarEmpleado() {
        // Implementar la lógica para asignar empleado a departamento
    }

    // Métodos para buscar empleados y departamentos (si es necesario)

    private static Empleado buscarEmpleadoPorID(int id) {
        for (Empleado empleado : empleados) {
            if (empleado.getId() == id) {
                return empleado;
            }
        }
        return null;
    }

    private static Departamento buscarDepartamentoPorID(int id) {
        for (Departamento departamento : departamentos) {
            if (departamento.getId() == id) {
                return departamento;
            }
        }
        return null;
    }
}
