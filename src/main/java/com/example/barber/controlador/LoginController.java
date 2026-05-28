package com.example.barber.controlador;

import com.example.barber.modelo.GestionUsuarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button btnentrar;

    @FXML
    private Label errorlogin;

    @FXML
    void userLogIn(ActionEvent event) {
        String user = username.getText().trim();
        String pass = password.getText();

        if (user.isEmpty() || pass.isEmpty()) {
            errorlogin.setText("Por favor, llene todos los campos.");
            errorlogin.setStyle("-fx-text-fill: #ff3333;");
            return;
        }


        if (GestionUsuarios.validarLogin(user, pass)) {
            errorlogin.setText("¡Bienvenido "+user+"!");
            errorlogin.setStyle("-fx-text-fill: #00ff00;");
            Stage ventana = (Stage) btnentrar.getScene().getWindow();
            ventana.close();
        } else {
            password.clear();
            errorlogin.setText("Usuario o contraseña incorrectos.");
            errorlogin.setStyle("-fx-text-fill: #ff3333;");
        }
    }
}