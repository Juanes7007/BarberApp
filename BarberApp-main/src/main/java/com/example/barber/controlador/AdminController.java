package com.example.barber.controlador;

import com.example.barber.modelo.CitasDTO;
import com.example.barber.modelo.GestionCitas;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

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
    private ComboBox<String> cbBarbero;

    @FXML
    private ComboBox<String> cbServicio;

    @FXML
    private ComboBox<String> cbHora;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private DatePicker dFecha;

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

    @FXML
    private TableView<CitasDTO> citas;

    @FXML
    private TableColumn<CitasDTO, String> BarHora;

    @FXML
    private TableColumn<CitasDTO, String> BarCliente;

    @FXML
    private TableColumn<CitasDTO, String> BarServicio;

    @FXML
    private TableColumn<CitasDTO, String> BarEstado;

    @FXML
    private TableView<CitasDTO> Tacitas;

    @FXML
    private TableColumn<CitasDTO, String> BaHora;

    @FXML
    private TableColumn<CitasDTO, String> BaCliente;

    @FXML
    private TableColumn<CitasDTO, String> BaServicio;

    @FXML
    private TableColumn<CitasDTO, String> BaEstado;

    @FXML
    private TableColumn<CitasDTO, Void> BaAccion;

    private ObservableList<Usuario> listaBarberos
            = FXCollections.observableArrayList();

    private ObservableList<Usuario> listaContratados
            = FXCollections.observableArrayList();

    private ObservableList<Servicio> listaServicios
            = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (Usuario u : GestionUsuarios.obtenerUsuarios()) {
            System.out.println(
                    u.getUsername()
                    + " | "
                    + u.getPassword()
                    + " | "
                    + u.getTipo()
            );
        }

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

            cargarCitas();
            cargarComboBoxCitas();

            BaHora.setCellValueFactory(
                    new PropertyValueFactory<>("hora"));

            BaCliente.setCellValueFactory(
                    new PropertyValueFactory<>("cliente"));

            BaServicio.setCellValueFactory(
                    new PropertyValueFactory<>("servicio"));

            BaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

            BaAccion.setCellFactory(col -> new TableCell<CitasDTO, Void>() {

                private final Button btnCompletar = new Button("Completar");
                private final Button btnCancelar = new Button("Cancelar");

                private final HBox box = new HBox(10, btnCompletar, btnCancelar);

                {
                    btnCompletar.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                    btnCancelar.setStyle("-fx-background-color: red; -fx-text-fill: white;");

                    btnCompletar.setOnAction(e -> {
                        CitasDTO cita = getTableView().getItems().get(getIndex());

                        // Modifica el objeto que YA ESTÁ en la tabla
                        cita.setEstado("Completado");

                        // Ahora guarda al archivo
                        List<CitasDTO> lista = GestionCitas.obtenerCitas();
                        for (CitasDTO c : lista) {
                            if (c.getId().equals(cita.getId())) {
                                c.setEstado("Completado");
                                break;
                            }
                        }
                        GestionCitas.guardarCitas(lista);

                        Tacitas.refresh(); // Ahora sí refresca porque el objeto en memoria ya cambió
                    });

                    btnCancelar.setOnAction(e -> {

                        CitasDTO cita = getTableView().getItems().get(getIndex());

                        cita.setEstado("Completado");

                        // Ahora guarda al archivo
                        List<CitasDTO> lista = GestionCitas.obtenerCitas();
                        for (CitasDTO c : lista) {
                            if (c.getId().equals(cita.getId())) {
                                c.setEstado("Completado");
                                break;
                            }
                        }
                        GestionCitas.guardarCitas(lista);

                        Tacitas.refresh(); //ojo dañan esto mamones
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        return;
                    }

                    CitasDTO cita = getTableView().getItems().get(getIndex());

                    if (cita == null) {
                        setGraphic(null);
                        return;
                    }

                    String estado = cita.getEstado();

                    if (estado == null || estado.isBlank()) {
                        estado = "Pendiente";
                        cita.setEstado("Pendiente");
                    }

                    boolean esPendiente = estado.equals("Pendiente");

                    btnCompletar.setDisable(!esPendiente);
                    btnCancelar.setDisable(!esPendiente);

                    setGraphic(box);
                }
            });
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
            lblEstado.setText("Error al cerrar sesiÃ³n");
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

    private void cargarCitas() {
        BarHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        BarCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        BarServicio.setCellValueFactory(new PropertyValueFactory<>("servicio"));
        BarEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        List<CitasDTO> todas = GestionCitas.obtenerCitas();
        ObservableList<CitasDTO> lista = FXCollections.observableArrayList();
        String tipo = Sesion.getUsuarioActual().getTipo();
        for (CitasDTO cita : todas) {

            if (tipo.equals("ADMIN")) {

                lista.add(cita);

            } else if (Sesion.getUsuarioActual()
                    .getUsername()
                    .equals(cita.getBarbero())) {

                lista.add(cita);
            }
        }
        citas.setItems(lista);

    }

    private void cargarComboBoxCitas() {

        cbBarbero.getItems().clear();

        for (Usuario u : GestionUsuarios.obtenerUsuarios()) {

            if (u.getTipo().equalsIgnoreCase("BARBER")) {
                cbBarbero.getItems().add(u.getUsername());
            }
        }

        cbServicio.getItems().clear();

        cbServicio.getItems().addAll(
                "Corte",
                "Barba",
                "Corte + Barba"
        );

        cbHora.getItems().addAll(
                "9:00 AM", "10:00 AM", "11:00 AM",
                "12:00 PM", "1:00 PM", "2:00 PM",
                "3:00 PM", "4:00 PM", "5:00 PM",
                "6:00 PM", "7:00 PM", "8:00 PM"
        );
    }

    @FXML
    private void filtrarPorFecha() {

        if (dFecha.getValue() == null) {
            cargarCitas();
            return;
        }

        String fechaSeleccionada = dFecha.getValue().toString();

        Usuario usuario = Sesion.getUsuarioActual();

        ObservableList<CitasDTO> lista
                = FXCollections.observableArrayList();

        for (CitasDTO cita : GestionCitas.obtenerCitas()) {

            if (cita.getBarbero().equals(usuario.getUsername())
                    && cita.getFecha().equals(fechaSeleccionada)) {

                lista.add(cita);
            }
        }

        Tacitas.setItems(lista);

    }

    @FXML
    private void agendarCita() {
        if (cbBarbero.getValue() == null
                || cbServicio.getValue() == null
                || cbHora.getValue() == null
                || dpFecha.getValue() == null) {
            lblEstado.setText("Por favor completa todos los campos.");
            return;
        }
        if (!GestionCitas.VerificarDisponibilidad(dpFecha.getValue().toString(), cbHora.getValue())) {
            lblEstado.setText("Esa hora ya esta ocupada, elige otra.");
            return;
        }
        CitasDTO nuevaCita = new CitasDTO(
                cbHora.getValue(),
                Sesion.getUsuarioActual().getUsername(),
                cbServicio.getValue(),
                "",
                cbBarbero.getValue(),
                dpFecha.getValue().toString()
        );
        List<CitasDTO> citasGuardadas = GestionCitas.obtenerCitas();
        citasGuardadas.add(nuevaCita);
        GestionCitas.guardarCitas(citasGuardadas);
        cbBarbero.getSelectionModel().selectFirst();
        cbServicio.getSelectionModel().selectFirst();
        cbHora.getSelectionModel().selectFirst();
        dpFecha.setValue(null);
        lblEstado.setText("¡Cita agendada!");
        cargarCitas();
    }
}
