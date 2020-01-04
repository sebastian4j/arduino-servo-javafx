package com.sebastian.arduino.puente;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * sudo mvn clean package exec:java
 * 
 * info:
 * 
 * https://core-electronics.com.au/tutorials/serial-communications-arduino-uno.html
 *
 * @author Sebastián Ávila A.
 */
public class App extends Application {
  private static final Logger LOGGER = Logger.getLogger(App.class.getName());

  @Override
  public void stop() {
      
    try {
      ConectorServo.instance().cerrar();
    } catch (Exception e) {
        LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
    Platform.exit();
    System.exit(Thread.MAX_PRIORITY);
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("flechas.fxml"));
    loader.setResources(ResourceBundle.getBundle("textos"));
    primaryStage.setTitle("Control Puente");
    final Scene scene = new Scene(loader.load());
    primaryStage.setScene(scene);
    primaryStage.setFullScreen(true);
    primaryStage.show();
  }

  public static void main(String[] args) {    
      new Thread(new GamepadController()).start();
    launch(args);
  }  
}
