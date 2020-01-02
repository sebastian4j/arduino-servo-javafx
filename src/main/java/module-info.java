module com.sebastian.arduino.puente {
    requires jssc;
    requires java.logging;
    requires javafx.fxml;
    requires javafx.controls;
    exports com.sebastian.arduino.puente;
    opens com.sebastian.arduino.puente;
}
