package com.mycompany.jpmc01;

import Persona.Persona;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Richard
 */
public class FXMLController implements Initializable {

    // Declaramos los botones
    @FXML private Button aniadirBT;
    @FXML private Button modificarBT;
    @FXML private Button eliminarBT;
    @FXML private Button nuevoBT;
    
    // Declaramos los textfileds
    @FXML private TextField nombreTF;
    @FXML private TextField correoTF;
    @FXML private TextField edadTF;
    @FXML private TextField cedulaTF;
    
    // Declaramos la tabla y las columnas
    @FXML private TableView<Persona> tablaPersonas;
    @FXML private TableColumn nombreCL;
    @FXML private TableColumn cedulaCL;
    @FXML private TableColumn edadCL;
    @FXML private TableColumn correoCL;
    ObservableList<Persona> personas;
    
    private int posicionPersonaEnTabla;

    public FXMLController() {
        this.selectorTablaPersonas = new ListChangeListener<Persona>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Persona> c) {
                ponerPersonaSeleccionada();
            }
        };
    }

    /**
     * Método que realiza las acciones tras pulsar el boton "Nuevo"
     *
     * @param event
     */
    @FXML private void nuevo(ActionEvent event) {
        nombreTF.setText("");
        cedulaTF.setText("");
        edadTF.setText("");
        correoTF.setText("");
        modificarBT.setDisable(true);
        eliminarBT.setDisable(true);
        aniadirBT.setDisable(false);
    }

    /**
     * Método que realiza las acciones tras pulsar el boton "Añadir"
     *
     * @param event
     */
    @FXML private void aniadir(ActionEvent event) {
        Persona persona = new Persona();
        persona.nombre.set(nombreTF.getText());
        persona.cedula.set(cedulaTF.getText());
        persona.edad.set(Integer.parseInt(edadTF.getText()));
        persona.cedula.set(correoTF.getText());
        personas.add(persona);
    }

    /**
     * Método que realiza las acciones tras pulsar el boton "Modificar"
     *
     * @param event
     */
    @FXML private void modificar(ActionEvent event) {
        Persona persona = new Persona();
        persona.nombre.set(nombreTF.getText());
        persona.cedula.set(cedulaTF.getText());
        persona.edad.set(Integer.parseInt(edadTF.getText()));
        persona.correo.set(correoTF.getText());
        personas.set(posicionPersonaEnTabla, persona);
    }

    /**
     * Método que realiza las acciones tras pulsar el boton "Eliminar"
     *
     * @param event
     */
    @FXML private void eliminar(ActionEvent event) {
        personas.remove(posicionPersonaEnTabla);
    }
    /**
     * Listener de la tabla personas
     */
    private final ListChangeListener<Persona> selectorTablaPersonas;

    /**
     * PARA SELECCIONAR UNA CELDA DE LA TABLA "tablaPersonas"
     */
    public Persona getTablaPersonasSeleccionada() {
        if (tablaPersonas != null) {
            List<Persona> tabla = tablaPersonas.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final Persona competicionSeleccionada = tabla.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }

    /**
     * Método para poner en los textFields la tupla que selccionemos
     */
    private void ponerPersonaSeleccionada() {
        final Persona persona = getTablaPersonasSeleccionada();
        posicionPersonaEnTabla = personas.indexOf(persona);

        if (persona != null) {

            // Pongo los textFields con los datos correspondientes
            nombreTF.setText(persona.getNombre());
            cedulaTF.setText(persona.getCedula());
            edadTF.setText(persona.getEdad().toString());
            correoTF.setText(persona.getCorreo());

            // Pongo los botones en su estado correspondiente
            modificarBT.setDisable(false);
            eliminarBT.setDisable(false);
            aniadirBT.setDisable(true);

        }
    }

    /**
     * Método para inicializar la tabla
     */
    private void inicializarTablaPersonas() {
        nombreCL.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombre"));
        cedulaCL.setCellValueFactory(new PropertyValueFactory<Persona, String>("cedula"));
        edadCL.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("edad"));
        correoCL.setCellValueFactory(new PropertyValueFactory<Persona, String>("correo"));

        personas = FXCollections.observableArrayList();
        tablaPersonas.setItems(personas);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Inicializamos la tabla
        this.inicializarTablaPersonas();

        // Ponemos estos dos botones para que no se puedan seleccionar
        modificarBT.setDisable(true);
        eliminarBT.setDisable(true);

        // Seleccionar las tuplas de la tabla de las personas
        final ObservableList<Persona> tablaPersonaSel = tablaPersonas.getSelectionModel().getSelectedItems();
        tablaPersonaSel.addListener(selectorTablaPersonas);

        // Inicializamos la tabla con algunos datos aleatorios
        for (int i = 0; i < 20; i++) {
            Persona p1 = new Persona();
            p1.nombre.set("Nombre " + i);
            p1.cedula.set("Cedula" + i);
            p1.edad.set(20 + i);
            p1.correo.set("67589458" + i);
            personas.add(p1);
        }
    }
}
