module com.sebastian.arduino.puente {
    requires com.fazecast.jSerialComm;
    requires java.logging;
    requires javafx.fxml;
    requires javafx.controls;
    requires Jamepad;
    exports com.sebastian.arduino.puente;
    opens com.sebastian.arduino.puente;
}
