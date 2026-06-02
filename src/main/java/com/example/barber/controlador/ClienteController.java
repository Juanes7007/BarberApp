/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.barber.controlador;
import com.example.barber.modelo.Citas;
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
public class ClienteController {
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

    private ObservableList<Citas> listaCitas =
            FXCollections.observableArrayList();

    
   
    
}
