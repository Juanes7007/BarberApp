module com.example.barber {
    requires javafx.controls;
    requires javafx.media;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.google.gson;
    requires javafx.base;
    requires java.desktop;
    requires javafx.graphics;

    opens com.example.barber to javafx.fxml;
    exports com.example.barber;
    exports com.example.barber.controlador;
    opens com.example.barber.controlador to javafx.fxml;
    opens com.example.barber.modelo to com.google.gson;
    requires javafx.mediaEmpty;
}