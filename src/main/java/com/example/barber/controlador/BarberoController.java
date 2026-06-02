/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.barber.controlador;

/**
 *
 * @author DURLEY
 */
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
/**
 *
 * @author Admin
 */
public class BarberoController {
    /**
    
 
    private List<Citas> todasLasCitas = new ArrayList<>();
    
    public BarberoController() {
        
        System.out.println("Controlador creado");
        cargarDatosPrueba();
    }
    @FXML
public void initialize() {
    System.out.println("INITIALIZE EJECUTADO");
}
    @FXML
private DatePicker dpFecha;

@FXML
private TableView<?> citas;

@FXML
private TableColumn<?, ?> BarHora;

@FXML
private TableColumn<?, ?> BarCliente;

@FXML
private TableColumn<?, ?> BarServicio;

@FXML
private TableColumn<?, ?> BarEstado;

@FXML
private TableColumn<?, ?> BarAccion;

public void cargarCitas() {

}

public void filtrarPorBarbero(String barbero) {

}

public void filtrarPorFecha() {

}

public void actualizarEstado() {

}

    public void cargarDatosPrueba() {

        todasLasCitas.add(
            new Citas("08:00","Juan","Corte","Pendiente","Carlos","2026-06-01")
        );

        todasLasCitas.add(
            new Citas("09:00","Pedro","Barba","Pendiente","Andres","2026-06-01")
        );
        System.out.println("Cantidad de citas: " + todasLasCitas.size());
}
*  */
}