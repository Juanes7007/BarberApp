package com.example.barber.controlador;

import com.example.barber.modelo.GestionUsuarios;
import com.example.barber.modelo.Usuario;
import com.example.barber.util.Sesion;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

import java.io.IOException;

public class LoginController {

    @FXML
    private ImageView imgcandado;

    @FXML
    private ImageView imgcandadoAbierto;

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
    private Label btncerrar;

    @FXML
    private TextField passwordvisible;


    @FXML
    void userLogIn(ActionEvent event) {
        String user = username.getText().trim();
        String pass = password.isVisible() ? password.getText() : passwordvisible.getText();

        if (user.isEmpty() || pass.isEmpty()) {
            lblErrorLogin.setText("Por favor, llene todos los campos.");
            lblErrorLogin.setStyle("-fx-text-fill: #ff3333;");
            reproducirSonido("/com/example/barber/sonidos/incorrecto.mp3");
            return;
        }

        Usuario usuarioLogueado = GestionUsuarios.obtenerUsuario(user, pass);

        if (usuarioLogueado != null) {
            lblErrorLogin.setText("¡Bienvenido " + usuarioLogueado.getUsername() + "!");
            lblErrorLogin.setStyle("-fx-text-fill: #00ff00;");


            imgcandado.setVisible(false);
            imgcandadoAbierto.setVisible(true);
            reproducirSonido("/com/example/barber/sonidos/correcto.mp3");
            PauseTransition pausa = new PauseTransition(Duration.seconds(1.2));

            pausa.setOnFinished(animationEvent -> {

                Sesion.setUsuarioActual(usuarioLogueado);

                System.out.println("TIPO = [" + usuarioLogueado.getTipo() + "]");

                try {

                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("/com/example/barber/Admin.fxml"));

                    Parent root = loader.load();

                    Stage ventanaActual = (Stage) btnentrar.getScene().getWindow();

                    Stage nuevaVentana = new Stage();

                    nuevaVentana.setScene(new Scene(root));
                   nuevaVentana.initStyle(StageStyle.UNDECORATED);
                    nuevaVentana.show();
                      
                    ventanaActual.close();

                } catch (Exception e) {

                    e.printStackTrace();

                    lblErrorLogin.setText("Error al abrir la pantalla.");
                }
            });

            pausa.play();

        } else {
            password.clear();
            passwordvisible.clear();
            lblErrorLogin.setText("Usuario o contraseña incorrectos.");
            lblErrorLogin.setStyle("-fx-text-fill: #ff3333;");
            reproducirSonido("/com/example/barber/sonidos/incorrecto.mp3");
        }
    }

    @FXML
    void userRegister(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/barber/register.fxml"));
            Parent root = loader.load();

            Scene nuevaEscena = new Scene(root);
            Stage nuevoStage = new Stage();
            nuevoStage.initStyle(StageStyle.UNDECORATED);

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

    @FXML
    void cerrarPrograma(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void verContrasena(MouseEvent event) {
        if (password.isVisible()) {
            passwordvisible.setText(password.getText());
            password.setVisible(false);
            passwordvisible.setVisible(true);
            passwordvisible.requestFocus();
            passwordvisible.positionCaret(passwordvisible.getText().length());
        } else {
            password.setText(passwordvisible.getText());
            passwordvisible.setVisible(false);
            password.setVisible(true);
            password.requestFocus();
            password.positionCaret(password.getText().length());
        }
    }


    private void reproducirSonido(String rutaRecurso) {
        try {

            String url = getClass().getResource(rutaRecurso).toExternalForm();
            AudioClip clip = new AudioClip(url);
            clip.play();
        } catch (Exception e) {
            System.out.println("No se pudo reproducir el sonido: " + e.getMessage());
        }
    }

}