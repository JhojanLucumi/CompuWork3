import Clases.Departamento;
import Clases.Empleado;
import Clases.EmpleadoFactory;
import Controlador.DepartamentoController;
import Controlador.ReporteController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Main {

    private static final List<Empleado> empleados = new ArrayList<>();
    private static final List<Departamento> departamentos = new ArrayList<>();
    private static DepartamentoController departamentoController = new DepartamentoController();
    private static ReporteController reporteController = new ReporteController();

    public static void main(String[] args) {
        crearInterfazGrafica();
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

                if (Objects.requireNonNull(tipo).equalsIgnoreCase("Permanente")) {
                    float salarioBase = Float.parseFloat(salarioBaseField.getText());
                    empleados.add(EmpleadoFactory.crearEmpleado("permanente", id, nombre, edad, sexo, new Date(), "Seguro Médico", salarioBase, null, 0f));
                    JOptionPane.showMessageDialog(null, "Empleado permanente creado.");
                } else if (tipo.equalsIgnoreCase("Temporal")) {
                    float tasaPorHora = Float.parseFloat(tasaPorHoraField.getText());
                    empleados.add(EmpleadoFactory.crearEmpleado("temporal", id, nombre, edad, sexo, new Date(), null, 0f, new Date(), tasaPorHora));
                    JOptionPane.showMessageDialog(null, "Empleado temporal creado.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error en los datos ingresados. Por favor, verifica los campos.");
            }
        }
    }

    private static void verEmpleados() {
        StringBuilder sb = new StringBuilder();
        if (empleados.isEmpty()) {
            sb.append("No hay empleados registrados.");
        } else {
            for (Empleado empleado : empleados) {
                sb.append("ID: ").append(empleado.getId())
                        .append(", Nombre: ").append(empleado.getNombre())
                        .append(", Edad: ").append(empleado.getEdad())
                        .append(", Departamento: ").append(empleado.getDepartamento() != null ? empleado.getDepartamento().getNombre() : "No asignado")
                        .append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Empleados", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void actualizarEmpleado() {
        // Implementar la lógica para actualizar un empleado
    }

    private static void eliminarEmpleado() {
        // Implementar la lógica para eliminar un empleado
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
        StringBuilder sb = new StringBuilder();
        if (departamentos.isEmpty()) {
            sb.append("No hay departamentos registrados.");
        } else {
            for (Departamento departamento : departamentos) {
                sb.append("ID: ").append(departamento.getId())
                        .append(", Nombre: ").append(departamento.getNombre())
                        .append(", Jefe: ").append(departamento.getJefe())
                        .append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Departamentos", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void modificarDepartamento() {
        // Implementar la lógica para modificar un departamento
    }

    private static void eliminarDepartamento() {
        // Implementar la lógica para eliminar un departamento
    }

    private static void asignarEmpleado() {
        Empleado empleado = null;
        Departamento departamento = null;
        System.out.println("Empleado o Departamento no pueden ser null.");
        return;

        // Agregar el empleado al departamento
    }


    private static Empleado buscarEmpleadoPorID(int id) {
        if (empleados.isEmpty()) {
            System.out.println("La lista de empleados está vacía o no ha sido inicializada.");
            return null;
        }

        for (Empleado empleado : empleados) {
            if (empleado.getId() == id) {
                return empleado;
            }
        }

        System.out.println("Empleado con ID " + id + " no encontrado.");
        return null;
    }

    // Metodo para buscar un departamento por su ID
    private static Departamento buscarDepartamentoPorID(int id) {
        if (departamentos.isEmpty()) {
            System.out.println("La lista de departamentos está vacía o no ha sido inicializada.");
            return null;
        }

        for (Departamento departamento : departamentos) {
            if (departamento.getId() == id) {
                return departamento;
            }
        }

        System.out.println("Departamento con ID " + id + " no encontrado.");
        return null;
    }

    public static DepartamentoController getDepartamentoController() {
        return departamentoController;
    }

    public static void setDepartamentoController(DepartamentoController departamentoController) {
        Main.departamentoController = departamentoController;
    }

    public static ReporteController getReporteController() {
        return reporteController;
    }

    public static void setReporteController(ReporteController reporteController) {
        Main.reporteController = reporteController;
    }
}

