package Ej26;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
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
            String mensaje2 = lector.readLine();
            String mensaje3 = lector.readLine();

            escritor.println("Mensaje recibido en el servidor : " + mensaje);
            escritor.println("Mensaje recibido en el servidor : " + mensaje2);
            escritor.println("Mensaje recibido en el servidor : " + mensaje3);

            System.out.println("1 - Mensaje hardcodeado : " + mensaje +" hardcodeado"  );
            System.out.println("2 - Mensaje hardcodeado : " + mensaje2 +" hardcodeado"  );
            System.out.println("3 - Mensaje hardcodeado : " + mensaje3 +" hardcodeado"  );


            socket.close();
            servidor.close();
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
