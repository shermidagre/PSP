package Ej30;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class Cliente {


    void main() throws IOException {
        try {

            DatagramSocket socket = new DatagramSocket();
            InetAddress ipDestino = InetAddress.getByName("localhost");

            byte[] bufferEnvio = "sin tv".getBytes();
            byte[] bufferEnvio2 = "y sin cerveza".getBytes();
            byte[] bufferEnvio3 = "Homer".getBytes();

            DatagramPacket paquete1 = new DatagramPacket(bufferEnvio, bufferEnvio.length, ipDestino, 6666);
            DatagramPacket paquete2 = new DatagramPacket(bufferEnvio2, bufferEnvio2.length, ipDestino, 6666);
            DatagramPacket paquete3 = new DatagramPacket(bufferEnvio3, bufferEnvio3.length, ipDestino, 6666);


            Scanner sc = new Scanner(System.in);


            System.out.println("Paquetes a enviar:");
            socket.send(paquete1);
            socket.send(paquete2);
            socket.send(paquete3);


            socket.close();
            System.out.println("Conexión cerrada.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


