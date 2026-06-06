package com.example.barber.controlador;

import com.example.barber.modelo.Cliente;
import com.example.barber.modelo.GestionClientes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ClientesController implements Initializable {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TableView<Cliente> tablaClientes;

    @FXML
    private TableColumn<Cliente,String> colId;

    @FXML
    private TableColumn<Cliente,String> colNombre;

    @FXML
    private TableColumn<Cliente,String> colTelefono;

    @FXML
    private TableColumn<Cliente,String> colCorreo;

    private ObservableList<Cliente> lista =
            FXCollections.observableArrayList();

    @Override
    public void initialize(URL url,
                           ResourceBundle rb) {

        colId.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre"));

        colTelefono.setCellValueFactory(
                new PropertyValueFactory<>("telefono"));

        colCorreo.setCellValueFactory(
                new PropertyValueFactory<>("correo"));

        cargarClientes();
    }

    private void cargarClientes() {

        lista.clear();

        lista.addAll(
                GestionClientes.obtenerClientes());

        tablaClientes.setItems(lista);
    }

    @FXML
    void agregarCliente(ActionEvent e) {

        Cliente cliente = new Cliente(
                String.valueOf(System.currentTimeMillis()),
                txtNombre.getText(),
                txtTelefono.getText(),
                txtCorreo.getText()
        );

        GestionClientes.registrarCliente(cliente);

        cargarClientes();
    }

    @FXML
    void eliminarCliente(ActionEvent e) {

        Cliente seleccionado =
                tablaClientes.getSelectionModel()
                        .getSelectedItem();

        if (seleccionado == null) return;

        List<Cliente> clientes =
                GestionClientes.obtenerClientes();

        clientes.removeIf(
                c -> c.getId().equals(
                        seleccionado.getId()));

        GestionClientes.guardarClientes(clientes);

        cargarClientes();
    }
}