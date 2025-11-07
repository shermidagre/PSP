package Ej27;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
            Socket socket = servidor.accept();


            PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String mensaje = lector.readLine();


            while ((mensaje != null)) {



                if (mensaje.equals("adios")){
                    socket.close();
                    servidor.close();
                    break;
                }else {
                    escritor.println("Mensaje recibido en el servidor : " + mensaje);
                    System.out.println("1 - Mensaje hardcodeado : " + mensaje +" hardcodeado" );
                }



            }}catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
