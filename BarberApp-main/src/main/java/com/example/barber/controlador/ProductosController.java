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

public class ProductosController
        implements Initializable {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtStock;

    @FXML
    private TextField txtCompra;

    @FXML
    private TextField txtVenta;

    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TableColumn<Producto,String> colId;

    @FXML
    private TableColumn<Producto,String> colNombre;

    @FXML
    private TableColumn<Producto,Integer> colStock;

    @FXML
    private TableColumn<Producto,Double> colCompra;

    @FXML
    private TableColumn<Producto,Double> colVenta;

    private ObservableList<Producto> lista =
            FXCollections.observableArrayList();

    @Override
    public void initialize(URL url,
                           ResourceBundle rb) {

        colId.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre"));

        colStock.setCellValueFactory(
                new PropertyValueFactory<>("stock"));

        colCompra.setCellValueFactory(
                new PropertyValueFactory<>("precioCompra"));

        colVenta.setCellValueFactory(
                new PropertyValueFactory<>("precioVenta"));

        cargarProductos();
    }

    private void cargarProductos() {

        lista.clear();

        lista.addAll(
                GestionProductos.obtenerProductos());

        tablaProductos.setItems(lista);
    }

    @FXML
    void agregarProducto(ActionEvent e) {

        Producto producto = new Producto(
                String.valueOf(System.currentTimeMillis()),
                txtNombre.getText(),
                Integer.parseInt(txtStock.getText()),
                Double.parseDouble(txtCompra.getText()),
                Double.parseDouble(txtVenta.getText())
        );

        GestionProductos.registrarProducto(producto);

        cargarProductos();
    }

    @FXML
    void eliminarProducto(ActionEvent e) {

        Producto seleccionado =
                tablaProductos.getSelectionModel()
                        .getSelectedItem();

        if (seleccionado == null) return;

        List<Producto> productos =
                GestionProductos.obtenerProductos();

        productos.removeIf(
                p -> p.getId().equals(
                        seleccionado.getId()));

        GestionProductos.guardarProductos(productos);

        cargarProductos();
    }
}