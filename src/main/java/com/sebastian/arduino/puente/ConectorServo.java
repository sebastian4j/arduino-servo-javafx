package com.sebastian.arduino.puente;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import java.io.IOException;

/**
 * realiza las operaciones de conexión con el servo a través del arduino.
 *
 * @author Sebastián Ávila A.
 */
public final class ConectorServo {

    private static SerialPort SP;
    private static boolean iniciado = false;

    private static ConectorServo cs;

    public static synchronized ConectorServo instance() {
        if (cs == null) {
            cs = new ConectorServo();
        }
        iniciar();
        return cs;
    }

    private static void iniciar() {
        if (!iniciado) {
            iniciado = true;
            SP = SerialPort.getCommPort("/dev/ttyACM0");
            SP.setComPortParameters(9600, 8, 1, 0);
            SP.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING, 0, 0);
            SP.openPort();
            SP.addDataListener(new SerialPortDataListener() {
                @Override
                public int getListeningEvents() {
                    return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
                }

                @Override
                public void serialEvent(SerialPortEvent event) {
                    if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
                        return;
                    }
                    byte[] newData = new byte[SP.bytesAvailable()];
                    int numRead = SP.readBytes(newData, newData.length);
                }
            });
        }
    }

    private ConectorServo() {
    }

    public void subirPuente() throws IOException {
        SP.getOutputStream().write(1);
    }

    public void bajarPuente() throws IOException {
        SP.getOutputStream().write(2);
    }

    public void cerrar() {
        SP.closePort();
    }

}
