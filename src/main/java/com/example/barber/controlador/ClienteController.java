/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.barber.controlador;

import com.example.barber.modelo.Citas;
import com.example.barber.modelo.CitasDTO;
import com.example.barber.modelo.GestionCitas;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 *
 * @author YO
 */
public class ClienteController implements Initializable {

    @FXML
    private ComboBox<String> cbBarbero;

    @FXML
    private ComboBox<String> cbServicio;

    @FXML
    private ComboBox<String> cbHora;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private Button btnAgendar;
    @FXML
    private TableView<Citas> tblCitas;

    @FXML
    private TableColumn<Citas, String> colServicio;

    @FXML
    private TableColumn<Citas, String> colBarbero;

    @FXML
    private TableColumn<Citas, String> colFecha;

    @FXML
    private TableColumn<Citas, String> colHora;

    private ObservableList<Citas> listaCitas
            = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbBarbero.getItems().addAll("CarlillosElPillo", "JotaCorritoPititos", "AndresitoMamador", "Sal-Piconsillo");
        cbServicio.getItems().addAll("Mamada de pene", "Jota se deja Follar", "Bacano");
        cbHora.getItems().addAll(
                "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM",
                "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM",
                "5:00 PM", "6:00 PM", "7:00 PM", "8:00 PM"
        );

        colBarbero.setCellValueFactory(c -> c.getValue().barberoProperty());
        colServicio.setCellValueFactory(c -> c.getValue().servicioProperty());
        colHora.setCellValueFactory(c -> c.getValue().horaProperty());
        colFecha.setCellValueFactory(c -> c.getValue().fechaProperty());
        tblCitas.setItems(listaCitas);
    }

    @FXML
    private void agendarCita() {

        if (cbBarbero.getValue() == null
                || cbServicio.getValue() == null
                || cbHora.getValue() == null
                || dpFecha.getValue() == null) {

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setContentText("Por favor completa todos los campos.");
            alerta.showAndWait();
            return;

        }
        if (!GestionCitas.VerificarDisponibilidad(dpFecha.getValue().toString(), cbHora.getValue())) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setContentText("Esa hora ya está ocupada, elige otra.");
            alerta.showAndWait();
            return;
        }

        CitasDTO nuevaCita = new CitasDTO(
                cbHora.getValue(),
                "",
                cbServicio.getValue(),
                "",
                cbBarbero.getValue(),
                dpFecha.getValue().toString()
        );
        List<CitasDTO> citasGuardadas = GestionCitas.obtenerCitas();
        citasGuardadas.add(nuevaCita);
        GestionCitas.guardarCitas(citasGuardadas);
        Citas citaTabla = new Citas(
                nuevaCita.getHora(),
                "",
                nuevaCita.getServicio(),
                "",
                nuevaCita.getBarbero(),
                nuevaCita.getFecha()
        );
        listaCitas.add(citaTabla);

        cbBarbero.getSelectionModel().selectFirst();
        cbServicio.getSelectionModel().selectFirst();
        cbHora.getSelectionModel().selectFirst();
        dpFecha.setValue(null);

    }

}
