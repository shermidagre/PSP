package Ej31;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Servidor {
    void main() {
        try {

            DatagramSocket socket = new DatagramSocket(6666);
            DatagramSocket socketEnvio = new DatagramSocket();
            InetAddress ipDestino = InetAddress.getByName("localhost");

            byte [] bufferRecepcion = new byte [1024];

            Scanner sc = new Scanner(System.in);

            DatagramPacket paquete = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);

            System.out.println("Esperando conexiones");

            while (true){
                socket.receive(paquete); // bloquea el paquete


                System.out.println("Cliente conectado ");
                String msg = new String(paquete.getData(),0, paquete.getLength());


                System.out.println("Recibido " + msg);

                System.out.println("---------------------");
                System.out.println("Paquetes a enviar :");

                System.out.println("Escribe un mensaje a enviar:");
                String mensajeUsuario = sc.nextLine();

                byte[] bufferEnvio = mensajeUsuario.getBytes();

                DatagramPacket paqueteEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, ipDestino, 6666);

                socketEnvio.send(paqueteEnvio);

                socketEnvio.close();
                socket.close();

            }

            }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
