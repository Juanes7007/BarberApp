package com.example.barber.controlador;
import com.example.barber.modelo.GestionUsuarios;
import com.example.barber.modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.UUID;


public class RegisterController {

    @FXML
    private TextField usernameregister;

    @FXML
    private TextField correoregister;

    @FXML
    private PasswordField passwordregister;

    @FXML
    private TextField passwordvisibleregister;

    @FXML
    private PasswordField password2register;

    @FXML
    private TextField passwordvisibleregister2;

    @FXML
    private Button btnregistrarse;

    @FXML
    private Label lblErrorRegister;


    private String getContrasena() {
        if (passwordregister.isVisible()) {
            return passwordregister.getText();
        } else {
            return passwordvisibleregister.getText();
        }
    }

    private String getConfirmacionContrasena() {
        if (password2register.isVisible()) {
            return password2register.getText();
        } else {
            return passwordvisibleregister2.getText();
        }
    }

    @FXML
    void botonregistrarse(ActionEvent event){
        String user = usernameregister.getText().trim();
        String correo = correoregister.getText().trim();
        String pass = getContrasena();
        String passConfirm = getConfirmacionContrasena();

        if (user.isEmpty() || pass.isEmpty() || passConfirm.isEmpty()) {
            lblErrorRegister.setText("Por favor, llene los campos obligatorios (*)");
            return;
        }
        if (correo.isEmpty()) {
            correo = "none";
        } else {

            String verifCorreo = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

            if (!correo.matches(verifCorreo)) {
                lblErrorRegister.setText("El formato del correo electrónico no es valido");
                lblErrorRegister.setStyle("-fx-text-fill: #ff3333;");
                return;
            }
        }

        if (!pass.equals(passConfirm)) {
            lblErrorRegister.setText("Las contraseñas no coinciden");
            lblErrorRegister.setStyle("-fx-text-fill: #ff3333;");
            return;
        }



        String idAlea = UUID.randomUUID().toString().substring(0, 8);
        Usuario nuevoCliente = new Usuario(idAlea, user, pass, "Cliente", correo);
        boolean siga = GestionUsuarios.registrarUsuario(nuevoCliente);
        if (siga) {
            lblErrorRegister.setText("¡Registro exitoso! Ya puedes iniciar sesión");
            lblErrorRegister.setStyle("-fx-text-fill: #00ff00;");
            usernameregister.clear();
            correoregister.clear();
            passwordregister.clear();
            passwordvisibleregister.clear();
            password2register.clear();
            passwordvisibleregister2.clear();
        } else {
            lblErrorRegister.setText("El nombre de usuario ya se encuentra registrado");

        }


    }


    @FXML
    private void verContraseña(MouseEvent event) {
        if (passwordregister.isVisible()) {
            passwordvisibleregister.setText(passwordregister.getText());
            passwordregister.setVisible(false);
            passwordvisibleregister.setVisible(true);
            passwordvisibleregister.requestFocus();
            passwordvisibleregister.positionCaret(passwordvisibleregister.getText().length());
        } else {
            passwordregister.setText(passwordvisibleregister.getText());
            passwordvisibleregister.setVisible(false);
            passwordregister.setVisible(true);
            passwordregister.requestFocus();
            passwordregister.positionCaret(passwordregister.getText().length());
        }
    }

    @FXML
    private void verContraseña2(MouseEvent event) {
        if (password2register.isVisible()) {
            passwordvisibleregister2.setText(password2register.getText());
            password2register.setVisible(false);
            passwordvisibleregister2.setVisible(true);
            passwordvisibleregister2.requestFocus();
            passwordvisibleregister2.positionCaret(passwordvisibleregister2.getText().length());
        } else {
            password2register.setText(passwordvisibleregister2.getText());
            passwordvisibleregister2.setVisible(false);
            password2register.setVisible(true);
            password2register.requestFocus();
            password2register.positionCaret(password2register.getText().length());
        }
    }



    @FXML
    private void volverLogin(MouseEvent event){
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/barber/login.fxml"));
            Parent root = loader.load();

            Scene nuevaEscena = new Scene(root);


            Stage nuevoStage = new Stage();
            nuevoStage.setTitle("BarberControl - Iniciar Sesión");
            nuevoStage.setScene(nuevaEscena);
            nuevoStage.setResizable(false);
            nuevoStage.show();


            Stage ventanaActual = (Stage) btnregistrarse.getScene().getWindow();
            ventanaActual.close();

        } catch (IOException e) {
            lblErrorRegister.setText("Error al cargar la pantalla de inicio de sesión ");
            lblErrorRegister.setStyle("-fx-text-fill: #ff3333;");
            e.printStackTrace();
        }
    }

}
