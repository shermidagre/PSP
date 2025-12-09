package Ej29;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    void main() {
        try {
            InetSocketAddress dir = new InetSocketAddress("localhost", 6666);


            ServerSocket servidor = new ServerSocket();
            servidor.bind(dir);

            System.out.println("Esperando conexiones");

            while (true){
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado ");
                GestorClientes gestor = new GestorClientes(cliente);
                gestor.start();
            }

            }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
