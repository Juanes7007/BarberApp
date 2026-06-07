/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.barber.controlador;

import com.example.barber.modelo.CitasDTO;
import com.example.barber.modelo.GestionCitas;
import com.example.barber.modelo.Usuario;
import com.example.barber.util.Sesion;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Admin
 */
public class BarberoController {

    private List<CitasDTO> todasLasCitas = new ArrayList<>();

    public BarberoController() {

        System.out.println("Controlador creado");

    }

    @FXML
    public void initialize() {
        

        
        
        BaHora.setCellValueFactory(
                new PropertyValueFactory<>("hora"));

        BaCliente.setCellValueFactory(
                new PropertyValueFactory<>("cliente"));

        BaServicio.setCellValueFactory(
                new PropertyValueFactory<>("servicio"));

        BaEstado.setCellValueFactory(
                new PropertyValueFactory<>("estado"));

        cargarCitas();
    }
    
    @FXML
private DatePicker dFecha;
    
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

    public void cargarCitas() {

        Usuario usuario = Sesion.getUsuarioActual();

        List<CitasDTO> todas = GestionCitas.obtenerCitas();

        ObservableList<CitasDTO> lista
                = FXCollections.observableArrayList();

        for (CitasDTO cita : todas) {

            System.out.println(
    "Usuario: " + Sesion.getUsuarioActual().getUsername()
    + " | Barbero cita: " + cita.getBarbero()
);
            
            if (cita.getBarbero().equals(usuario.getUsername())) {

                lista.add(cita);
            }
        }

        Tacitas.setItems(lista);
        System.out.println("Citas cargadas: " + lista.size());
    }

    public void filtrarPorBarbero(String barbero) {

    }

  @FXML
public void filtrarPorFecha() {

    if (dFecha.getValue() == null) {
        cargarCitas();
        return;
    }

    String fechaSeleccionada = dFecha.getValue().toString();

    Usuario usuario = Sesion.getUsuarioActual();

    ObservableList<CitasDTO> lista =
            FXCollections.observableArrayList();

    for (CitasDTO cita : GestionCitas.obtenerCitas()) {

        if (cita.getBarbero().equals(usuario.getUsername())
                && cita.getFecha().equals(fechaSeleccionada)) {

            lista.add(cita);
        }
    }

    Tacitas.setItems(lista);
}

    public void actualizarEstado(String nuevoEstado) {

        CitasDTO cita
                = Tacitas.getSelectionModel().getSelectedItem();

        if (cita == null) {
            return;
        }

        List<CitasDTO> citas
                = GestionCitas.obtenerCitas();

        for (CitasDTO c : citas) {

            if (c.getHora().equals(cita.getHora())
                    && c.getFecha().equals(cita.getFecha())
                    && c.getCliente().equals(cita.getCliente())) {

                c.setEstado(nuevoEstado);
                break;
            }
        }

        GestionCitas.guardarCitas(citas);

        cargarCitas();
    }
    @FXML
private void marcarCompletada() {
    actualizarEstado("Completada");
}

@FXML
private void marcarCancelada() {
    actualizarEstado("Cancelada");
}

}
