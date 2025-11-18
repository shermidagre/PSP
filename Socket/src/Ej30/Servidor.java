package Ej30;

import Ej28.GestorClientes;

import java.net.*;

public class Servidor {
    void main() {
        try {

            DatagramSocket socket = new DatagramSocket(6666);
            DatagramSocket socketEnvio = new DatagramSocket();
            InetAddress ipDestino = InetAddress.getByName("localhost");

            byte [] bufferRecepcion = new byte [1024];
            byte [] bufferRecepcion2 = new byte [1024];
            byte [] bufferRecepcion3 = new byte [1024];


            DatagramPacket paquete = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);
            DatagramPacket paquete2 = new DatagramPacket(bufferRecepcion2, bufferRecepcion2.length);
            DatagramPacket paquete3 = new DatagramPacket(bufferRecepcion3, bufferRecepcion3.length);

            System.out.println("Esperando conexiones");

            while (true){
                socket.receive(paquete); // bloquea el paquete
                socket.receive(paquete2);
                socket.receive(paquete3);

                System.out.println("Cliente conectado ");
                String msg = new String(paquete.getData(),0, paquete.getLength());
                String msg2 = new String(paquete2.getData(),0, paquete2.getLength());
                String msg3 = new String(paquete3.getData(),0, paquete3.getLength());


                System.out.println("Recibido " + msg);
                System.out.println("Recibido " + msg2);
                System.out.println("Recibido " + msg3);

                System.out.println("---------------------");
                System.out.println("Paquetes a enviar :");

                byte[] bufferEnvio = "Pierde".getBytes();
                byte[] bufferEnvio2 = "La".getBytes();
                byte[] bufferEnvio3 = "Cabeza".getBytes();

                DatagramPacket paqueteEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, ipDestino, 6666);
                DatagramPacket paqueteEnvio2 = new DatagramPacket(bufferEnvio2, bufferEnvio2.length, ipDestino, 6666);
                DatagramPacket paqueteEnvio3 = new DatagramPacket(bufferEnvio3, bufferEnvio3.length, ipDestino, 6666);


                socketEnvio.send(paqueteEnvio);
                socketEnvio.send(paqueteEnvio2);
                socketEnvio.send(paqueteEnvio3);

                socketEnvio.close();
                socket.close();


                //GestorClientes gestor = new GestorClientes(cliente);
                //gestor.start();
            }

            }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
