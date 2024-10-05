package Vista;

import Controlador.EmpleadoController;
import Clases.Departamento;
import Clases.Empleado;
import Clases.EmpleadoPermanente;
import Clases.EmpleadoTemporal;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppGUI extends Application {
    private final EmpleadoController empleadoController = new EmpleadoController();
    private final Departamento departamentoIT = new Departamento("IT");
    private final Departamento departamentoHR = new Departamento("Recursos Humanos");

    @Override
    public void start(Stage stage) {
        // Layout principal
        VBox layout = new VBox();
        layout.setSpacing(10);
        layout.setPadding(new Insets(10));

        // Elementos de la interfaz
        Label label = new Label("Gestión de Empleados");

        // Campos para agregar empleado
        TextField nombreField = new TextField();
        nombreField.setPromptText("Nombre del empleado");

        TextField idField = new TextField();
        idField.setPromptText("ID del empleado");

        TextField edadField = new TextField();
        edadField.setPromptText("Edad del empleado");

        TextField sexoField = new TextField();
        sexoField.setPromptText("Sexo del empleado");

        TextField fechaField = new TextField();
        fechaField.setPromptText("Fecha de contratación (dd/MM/yyyy)");

        TextField beneficiosField = new TextField();
        beneficiosField.setPromptText("Beneficios (solo permanente)");

        TextField salarioField = new TextField();
        salarioField.setPromptText("Salario base (solo permanente)");

        ComboBox<String> tipoEmpleadoBox = new ComboBox<>();
        tipoEmpleadoBox.getItems().addAll("Permanente", "Temporal");
        tipoEmpleadoBox.setValue("Permanente");

        ComboBox<String> departamentoBox = new ComboBox<>();
        departamentoBox.getItems().addAll("IT", "Recursos Humanos");
        departamentoBox.setValue("IT");

        Button agregarBtn = new Button("Agregar Empleado");

        TextArea empleadosArea = new TextArea();
        empleadosArea.setEditable(false);

        // Acciones de botones
        agregarBtn.setOnAction(e -> {
            try {
                String nombre = nombreField.getText();
                int id = Integer.parseInt(idField.getText());
                int edad = Integer.parseInt(edadField.getText());
                String sexo = sexoField.getText();
                Date fechaContratacion = new SimpleDateFormat("dd/MM/yyyy").parse(fechaField.getText());
                String tipo = tipoEmpleadoBox.getValue();
                Departamento departamento = departamentoBox.getValue().equals("IT") ? departamentoIT : departamentoHR;

                if (tipo.equals("Permanente")) {
                    String beneficios = beneficiosField.getText();
                    float salarioBase = Float.parseFloat(salarioField.getText());
                    EmpleadoPermanente empleado = new EmpleadoPermanente(id, nombre, edad, sexo, fechaContratacion, beneficios, salarioBase);
                    empleadoController.agregarEmpleado(empleado);
                } else {
                    EmpleadoTemporal empleado = new EmpleadoTemporal(id, nombre, edad, sexo, fechaContratacion, departamento, 100);
                    empleadoController.agregarEmpleado(empleado);
                }

                empleadosArea.appendText("Empleado agregado: " + nombre + "\n");
                limpiarCampos(nombreField, idField, edadField, sexoField, fechaField, beneficiosField, salarioField);

            } catch (NumberFormatException ex) {
                empleadosArea.appendText("Error: Los campos de ID, edad o salario deben ser numéricos.\n");
            } catch (ParseException ex) {
                empleadosArea.appendText("Error: Formato de fecha incorrecto. Debe ser dd/MM/yyyy.\n");
            }
        });

        Button verEmpleadosBtn = new Button("Ver Empleados");
        verEmpleadosBtn.setOnAction(e -> {
            StringBuilder sb = new StringBuilder();
            for (Empleado empleado : empleadoController.obtenerEmpleados()) {
                sb.append("ID: ").append(empleado.getId())
                        .append(", Nombre: ").append(empleado.getNombre())
                        .append(", Departamento: ").append(empleado.getDepartamento().getNombre())
                        .append("\n");
            }
            empleadosArea.setText(sb.toString());
        });

        // Añadir los elementos al layout
        layout.getChildren().addAll(label, nombreField, idField, edadField, sexoField, fechaField, beneficiosField, salarioField, tipoEmpleadoBox, departamentoBox, agregarBtn, verEmpleadosBtn, empleadosArea);

        // Crear la escena
        Scene scene = new Scene(layout, 400, 500);
        stage.setScene(scene);
        stage.setTitle("Sistema de Gestión de Empleados");
        stage.show();
    }

    private void limpiarCampos(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}




