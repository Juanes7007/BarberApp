package com.example.barber.controlador;

import com.example.barber.modelo.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProveedoresController
        implements Initializable {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TableView<Proveedor> tablaProveedores;

    @FXML
    private TableColumn<Proveedor,String> colId;

    @FXML
    private TableColumn<Proveedor,String> colNombre;

    @FXML
    private TableColumn<Proveedor,String> colTelefono;

    @FXML
    private TableColumn<Proveedor,String> colCorreo;

    private ObservableList<Proveedor> lista =
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

        cargarProveedores();
    }

    private void cargarProveedores() {

        lista.clear();

        lista.addAll(
                GestionProveedores.obtenerProveedores());

        tablaProveedores.setItems(lista);
    }

    @FXML
    void agregarProveedor(ActionEvent e) {

        Proveedor proveedor = new Proveedor(
                String.valueOf(System.currentTimeMillis()),
                txtNombre.getText(),
                txtTelefono.getText(),
                txtCorreo.getText()
        );

        GestionProveedores.registrarProveedor(proveedor);

        cargarProveedores();
    }

    @FXML
    void eliminarProveedor(ActionEvent e) {

        Proveedor seleccionado =
                tablaProveedores.getSelectionModel()
                        .getSelectedItem();

        if (seleccionado == null) return;

        List<Proveedor> proveedores =
                GestionProveedores.obtenerProveedores();

        proveedores.removeIf(
                p -> p.getId().equals(
                        seleccionado.getId()));

        GestionProveedores.guardarProveedores(proveedores);

        cargarProveedores();
    }
}