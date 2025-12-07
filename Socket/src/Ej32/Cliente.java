package Ej32;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {


    void main() throws IOException {
        try ( Scanner sc = new Scanner(System.in)){
            while (true) {
                enviarMensaje(sc);;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    // Lo que he añadido y justificacion :
    /*
    basicamente envia los dato sin recibirlos, por lo cual nunca iban a llegar y arregle el bucle metiendo el datagream socket arriba
     */
    static void enviarMensaje(Scanner sc) {
        try (DatagramSocket socket = new DatagramSocket()){
            System.out.println("Escribe un mensaje a enviar:");
            String mensajeUsuario = sc.nextLine();

            if (mensajeUsuario.equalsIgnoreCase("salir")){
                System.exit(0); // https://codegym.cc/es/groups/posts/es.384.sistema-exit-en-java
            }

            InetAddress ipDestino = InetAddress.getByName("localhost");
            int puerto = 5555;

            byte[] bufferEnvio = mensajeUsuario.getBytes();
            DatagramPacket paqueteEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, ipDestino, puerto);

            socket.send(paqueteEnvio);

            byte[] bufferRecepcion = new byte[1024];
            DatagramPacket paqueteRecepcion = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);

            socket.receive(paqueteRecepcion);

            String respuestaServidor = new String(paqueteRecepcion.getData(), 0, paqueteRecepcion.getLength()).trim();

            System.out.println("Respuesta del Servidor :" + respuestaServidor);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


