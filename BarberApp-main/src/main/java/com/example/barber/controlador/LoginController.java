package com.example.barber.controlador;

import com.example.barber.util.Sesion;
import com.example.barber.modelo.Usuario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import com.example.barber.modelo.GestionUsuarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button btnentrar;

    @FXML
    private Label lblErrorLogin;

    @FXML
    private Label btnRegistrarseCliente;

    @FXML
    void userLogIn(ActionEvent event) {

        String user = username.getText().trim();
        String pass = password.getText();

        if (user.isEmpty() || pass.isEmpty()) {

            lblErrorLogin.setText("Por favor llene todos los campos.");
            return;
        }

        Usuario usuario = GestionUsuarios.obtenerUsuario(user, pass);

        if (usuario == null) {

            lblErrorLogin.setText("Usuario o contraseña incorrectos.");
            password.clear();
            return;
        }

        Sesion.setUsuarioActual(usuario);

        System.out.println("TIPO = [" + usuario.getTipo() + "]");

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/barber/Admin.fxml"));

            Parent root = loader.load();

            Stage ventanaActual
                    = (Stage) btnentrar.getScene().getWindow();

            Stage nuevaVentana = new Stage();

            nuevaVentana.setScene(new Scene(root));
            nuevaVentana.show();

            ventanaActual.close();

        } catch (Exception e) {

            e.printStackTrace();

            lblErrorLogin.setText("Error al abrir la pantalla.");
        }
    }

    @FXML
    void userRegister(MouseEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/barber/register.fxml"));
            Parent root = loader.load();

            Scene nuevaEscena = new Scene(root);

            Stage nuevoStage = new Stage();
            nuevoStage.setTitle("Registro de Cliente - BarberControl");
            nuevoStage.setScene(nuevaEscena);
            nuevoStage.setResizable(false);
            nuevoStage.show();

            Stage ventanaActual = (Stage) btnRegistrarseCliente.getScene().getWindow();
            ventanaActual.close();

        } catch (IOException e) {
            lblErrorLogin.setText("Error al cargar la pantalla de registro.");
            lblErrorLogin.setStyle("-fx-text-fill: #ff3333;");
            e.printStackTrace();
        }
    }

}
