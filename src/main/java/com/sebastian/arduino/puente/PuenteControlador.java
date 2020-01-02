package com.sebastian.arduino.puente;

/**
 * controlador para las acciones para el puente.
 *
 * @author Sebastián Ávila A.
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import jssc.SerialPortException;

public class PuenteControlador {
  private static final Logger LOGGER = Logger.getLogger(PuenteControlador.class.getName());

  @FXML
  void bajar(ActionEvent event) {
    try {
      ConectorServo.instance().bajarPuente();
    } catch (SerialPortException e) {
      mostrarError(e);
    }
  }

  @FXML
  void subir(ActionEvent event) {
    try {
      ConectorServo.instance().subirPuente();
    } catch (SerialPortException e) {
      mostrarError(e);
    }
  }

  private void mostrarError(Throwable e) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Error de comunicación");
    alert.setHeaderText(e.getMessage());
    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
    alert.showAndWait();
    LOGGER.log(Level.SEVERE, e.getMessage(), e);
  }
}
