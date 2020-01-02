package com.sebastian.arduino.puente;

import jssc.SerialPort;
import jssc.SerialPortException;

/**
 * realiza las operaciones de conexión con el servo a través del arduino.
 *
 * @author Sebastián Ávila A.
 */
public final class ConectorServo {

  private static SerialPort SP;
  private static boolean iniciado = false;
  
  private static ConectorServo cs;
  
  public static synchronized ConectorServo instance() throws SerialPortException {
      if (cs == null) {
          cs = new ConectorServo();
      }
      iniciar();
      return cs;
  }

  private static void iniciar() throws SerialPortException {
    if (!iniciado) {
      iniciado = true;
      SP = new SerialPort("/dev/ttyACM0");
      SP.openPort();
      SP.setParams(
          SerialPort.BAUDRATE_9600,
          SerialPort.DATABITS_8,
          SerialPort.STOPBITS_1,
          SerialPort.PARITY_NONE);
      SP.purgePort(SerialPort.PURGE_TXCLEAR | SerialPort.PURGE_RXCLEAR);
    }
  }

  private ConectorServo() {}

  public void subirPuente() throws SerialPortException {
    SP.writeInt(1);
  }

  public void bajarPuente() throws SerialPortException {
    SP.writeInt(2);
  }

  public void cerrar() throws SerialPortException {
    SP.closePort();
  }
}
