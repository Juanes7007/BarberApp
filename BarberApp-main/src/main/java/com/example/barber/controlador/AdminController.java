package com.example.barber.controlador;

import com.example.barber.modelo.GestionUsuarios;
import com.example.barber.modelo.Usuario;
import com.example.barber.modelo.Servicio;

import com.example.barber.util.Sesion;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private TabPane tabPanePrincipal;

    @FXML
    private Tab tabDashboard;

    @FXML
    private Tab tabBarberos;

    @FXML
    private Tab tabServicios;

    @FXML
    private Tab tabClientes;

    @FXML
    private Tab tabFacturas;

    @FXML
    private Tab tabLiquidaciones;
    
    @FXML
    private Tab tabHistorial;

     @FXML
    private Tab tabCitas;
      
    
    @FXML
    private Tab tabReportes;
    @FXML
    private TableView<Usuario> tablaContratados;

    @FXML
    private TextField txtNombreBarbero;

    @FXML
    private TextField txtTelefonoBarbero;

    @FXML
    private TextField txtNombreServicio;

    @FXML
    private TextField txtPrecioServicio;

    @FXML
    private TableView<Usuario> tablaBarberos;

    @FXML
    private TableColumn<Usuario, String> colNombreBarbero;

    @FXML
    private TableColumn<Usuario, String> colTelefonoBarbero;

    @FXML
    private TableView<Servicio> tablaServicios;

    @FXML
    private TableColumn<Servicio, String> colNombreServicio;

    @FXML
    private TableColumn<Servicio, Double> colPrecioServicio;

    @FXML
    private TableColumn<Usuario, String> colNombreContratado;

    @FXML
    private TableColumn<Usuario, String> colTelefonoContratado;

    @FXML
    private Label lblTotalRecaudado;

    @FXML
    private Label lblEstado;

    private ObservableList<Usuario> listaBarberos
            = FXCollections.observableArrayList();

    private ObservableList<Usuario> listaContratados
            = FXCollections.observableArrayList();

    private ObservableList<Servicio> listaServicios
            = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Usuario usuario = Sesion.getUsuarioActual();

        if (usuario != null) {

            String tipo = usuario.getTipo().toUpperCase().trim();
            switch (tipo) {

                case "ADMIN":
                    break;

                case "BARBER":

                    tabPanePrincipal.getTabs().remove(tabDashboard);
                    tabPanePrincipal.getTabs().remove(tabBarberos);
                    tabPanePrincipal.getTabs().remove(tabClientes);
                    tabPanePrincipal.getTabs().remove(tabFacturas);

                    break;

                case "CLIENTE":

                    tabPanePrincipal.getTabs().remove(tabDashboard);
                    tabPanePrincipal.getTabs().remove(tabBarberos);
                    tabPanePrincipal.getTabs().remove(tabReportes);
                    tabPanePrincipal.getTabs().remove(tabLiquidaciones);
                     tabPanePrincipal.getTabs().remove(tabClientes);
                       tabPanePrincipal.getTabs().remove(tabCitas);
                    break;
            }
            tablaBarberos.getSelectionModel()
                    .selectedItemProperty()
                    .addListener((obs, anterior, actual) -> {

                        if (actual != null) {
                            txtNombreBarbero.setText(actual.getUsername());
                            txtTelefonoBarbero.setText(actual.getCorreo());
                        }
                    });

            tablaServicios.getSelectionModel()
                    .selectedItemProperty()
                    .addListener((obs, anterior, actual) -> {

                        if (actual != null) {
                            txtNombreServicio.setText(actual.getNombre());
                            txtPrecioServicio.setText(
                                    String.valueOf(actual.getPrecio()));
                        }
                    });

            colNombreBarbero.setCellValueFactory(
                    new PropertyValueFactory<>("username"));

            colTelefonoBarbero.setCellValueFactory(
                    new PropertyValueFactory<>("correo"));

            colNombreContratado.setCellValueFactory(
                    new PropertyValueFactory<>("username"));

            colTelefonoContratado.setCellValueFactory(
                    new PropertyValueFactory<>("correo"));

            colNombreServicio.setCellValueFactory(
                    new PropertyValueFactory<>("nombre"));

            colPrecioServicio.setCellValueFactory(
                    new PropertyValueFactory<>("precio"));

            cargarBarberos();

            tablaServicios.setItems(listaServicios);

            Usuario contratadoPorDefecto = new Usuario(
                    "999",
                    "Juan Barber",
                    "1234",
                    "BARBER",
                    "3000000000",
                    true
            );

            listaContratados.add(contratadoPorDefecto);

            tablaContratados.setItems(listaContratados);
        }
    }
    
    @FXML
    private void cerrarSesion(ActionEvent event) {

        try {

            Sesion.setUsuarioActual(null);

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/barber/login.fxml"));

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            Stage actual
                    = (Stage) lblEstado.getScene().getWindow();

            actual.close();

        } catch (Exception e) {

            e.printStackTrace();
            lblEstado.setText("Error al cerrar sesión");
        }
    }
    
    @FXML
    private void reservarServicio(ActionEvent event) {

        lblEstado.setText("Servicio reservado");
    }

    private void cargarBarberos() {

        listaBarberos.clear();

        List<Usuario> usuarios
                = GestionUsuarios.obtenerUsuarios();

        for (Usuario u : usuarios) {

            if (u.getTipo().equals("BARBER")) {

                listaBarberos.add(u);
            }
        }

        tablaBarberos.setItems(listaBarberos);
    }

    @FXML
    void contratarBarbero(ActionEvent event) {

        Usuario seleccionado
                = tablaBarberos.getSelectionModel()
                        .getSelectedItem();

        if (seleccionado == null) {

            lblEstado.setText("Seleccione un barbero");
            return;
        }

        if (!listaContratados.contains(seleccionado)) {

            listaContratados.add(seleccionado);

            lblEstado.setText("Barbero contratado");
        }
    }

    @FXML
    void despedirBarbero(ActionEvent event) {

        Usuario seleccionado
                = tablaContratados.getSelectionModel()
                        .getSelectedItem();

        if (seleccionado == null) {

            lblEstado.setText("Seleccione un contratado");
            return;
        }

        listaContratados.remove(seleccionado);

        lblEstado.setText("Barbero despedido");
    }

    @FXML
    void agregarBarbero(ActionEvent event) {

        String nombre
                = txtNombreBarbero.getText().trim();

        String telefono
                = txtTelefonoBarbero.getText().trim();

        if (nombre.isEmpty() || telefono.isEmpty()) {

            lblEstado.setText("Complete todos los campos");
            return;
        }

        Usuario nuevo = new Usuario(
                String.valueOf(System.currentTimeMillis()),
                nombre,
                "1234",
                "BARBER",
                telefono,
                true
        );

        if (GestionUsuarios.registrarUsuario(nuevo)) {

            txtNombreBarbero.clear();
            txtTelefonoBarbero.clear();

            cargarBarberos();

            lblEstado.setText("Barbero agregado");

        } else {

            lblEstado.setText("Ese usuario ya existe");
        }
    }

    @FXML
    void editarBarbero(ActionEvent event) {

        Usuario seleccionado
                = tablaBarberos.getSelectionModel()
                        .getSelectedItem();

        if (seleccionado == null) {

            lblEstado.setText("Seleccione un barbero");
            return;
        }

        List<Usuario> usuarios
                = GestionUsuarios.obtenerUsuarios();

        for (Usuario u : usuarios) {

            if (u.getId().equals(seleccionado.getId())) {

                u.setUsername(
                        txtNombreBarbero.getText());

                u.setCorreo(
                        txtTelefonoBarbero.getText());

                break;
            }
        }

        GestionUsuarios.guardarUsuarios(usuarios);

        cargarBarberos();

        lblEstado.setText("Barbero actualizado");
        txtNombreBarbero.clear();
        txtTelefonoBarbero.clear();
    }

    @FXML
    void eliminarBarbero(ActionEvent event) {

        Usuario seleccionado
                = tablaBarberos.getSelectionModel()
                        .getSelectedItem();

        if (seleccionado == null) {

            lblEstado.setText("Seleccione un barbero");
            return;
        }

        List<Usuario> usuarios
                = GestionUsuarios.obtenerUsuarios();

        usuarios.removeIf(
                u -> u.getId().equals(
                        seleccionado.getId())
        );

        GestionUsuarios.guardarUsuarios(usuarios);

        cargarBarberos();

        lblEstado.setText("Barbero eliminado");
    }

    @FXML
    void agregarServicio(ActionEvent event) {

        try {

            String nombre
                    = txtNombreServicio.getText();

            double precio
                    = Double.parseDouble(
                            txtPrecioServicio.getText());

            Servicio servicio
                    = new Servicio(
                            String.valueOf(System.currentTimeMillis()),
                            nombre,
                            precio);

            listaServicios.add(servicio);

            txtNombreServicio.clear();
            txtPrecioServicio.clear();

            lblEstado.setText("Servicio agregado");
            actualizarCaja();

        } catch (Exception e) {

            lblEstado.setText("Precio invalido");
        }
    }

    @FXML
    void editarServicio(ActionEvent event) {

        Servicio seleccionado
                = tablaServicios.getSelectionModel()
                        .getSelectedItem();

        if (seleccionado == null) {

            lblEstado.setText("Seleccione un servicio");
            return;
        }

        try {

            seleccionado.setNombre(
                    txtNombreServicio.getText());

            seleccionado.setPrecio(
                    Double.parseDouble(
                            txtPrecioServicio.getText()));

            tablaServicios.refresh();

            lblEstado.setText("Servicio actualizado");
            actualizarCaja();
            txtNombreServicio.clear();
            txtPrecioServicio.clear();

        } catch (Exception e) {

            lblEstado.setText("Precio invalido");
        }
    }

    @FXML
    void eliminarServicio(ActionEvent event) {

        Servicio seleccionado
                = tablaServicios.getSelectionModel()
                        .getSelectedItem();

        if (seleccionado == null) {

            lblEstado.setText("Seleccione un servicio");
            return;
        }

        listaServicios.remove(seleccionado);

        lblEstado.setText("Servicio eliminado");
        actualizarCaja();
        txtNombreServicio.clear();
        txtPrecioServicio.clear();
    }

    @FXML
    void calcularCaja(ActionEvent event) {

        double total = 0;

        for (Servicio s : listaServicios) {

            total += s.getPrecio();
        }

        lblTotalRecaudado.setText("$ " + total);
    }

    private void actualizarCaja() {

        double total = 0;

        for (Servicio s : listaServicios) {
            total += s.getPrecio();
        }

        lblTotalRecaudado.setText("$ " + total);
    }
}
