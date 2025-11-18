package Ej31;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {


    void main() throws IOException {
        try {

            Scanner sc = new Scanner(System.in);
            System.out.println("Escribe un mensaje a enviar:");
            String mensajeUsuario = sc.nextLine();

            DatagramSocket socket = new DatagramSocket();
            InetAddress ipDestino = InetAddress.getByName("localhost");

            byte[] bufferEnvio = mensajeUsuario.getBytes();
            DatagramPacket paquete1 = new DatagramPacket(bufferEnvio, bufferEnvio.length, ipDestino, 6666);




            System.out.println("Paquetes a enviar:" + mensajeUsuario);

            socket.send(paquete1);


            socket.close();
            System.out.println("Conexión cerrada.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


