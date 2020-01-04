package com.sebastian.arduino.puente;

import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/** @author Sebastián Ávila A. */
public class GamepadController implements Runnable {
  private static final ControllerManager CM;

  static {
    CM = new ControllerManager();
    CM.initSDLGamepad();
  }

  @Override
  public void run() {
    while (true) {
      try {
        ControllerState currState = CM.getState(0);
        if (currState.a && currState.aJustPressed) {
          ConectorServo.instance().subirPuente();
        } else if (currState.b && currState.bJustPressed) {
          ConectorServo.instance().bajarPuente();
        }
        Thread.sleep(100);
      } catch (InterruptedException ex) {
        Logger.getLogger(GamepadController.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
        Logger.getLogger(GamepadController.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
}
