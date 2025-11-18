package Ej32;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {


    void main() throws IOException {
        try {

            Scanner sc = new Scanner(System.in);

            while (true) {
                enviarMensaje(sc);;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    static void enviarMensaje(Scanner sc) {
        try {
            System.out.println("Escribe un mensaje a enviar:");
            String mensajeUsuario = sc.nextLine();

            DatagramSocket socket = new DatagramSocket();
            InetAddress ipDestino = InetAddress.getByName("localhost");

            byte[] bufferEnvio = mensajeUsuario.getBytes();
            DatagramPacket paquete1 = new DatagramPacket(bufferEnvio, bufferEnvio.length, ipDestino, 6666);

            System.out.println("Paquetes a enviar:" + mensajeUsuario);

            socket.send(paquete1);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


