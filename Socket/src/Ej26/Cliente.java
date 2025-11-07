package Ej26;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Cliente {

    InetSocketAddress dir = new InetSocketAddress("localhost", 6666);

    void main() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Envia 3 mensajes al servidor");
            String mensaje1 = sc.nextLine();
            String mensaje2 = sc.nextLine();
            String mensaje3 = sc.nextLine();

            Socket socket = new Socket();
            socket.connect(dir);
            Socket socket2 = new Socket();
            socket2.connect(dir);

            System.out.println("Conectado al servidor ");

            PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);
            escritor.println(mensaje1);
            escritor.println(mensaje2);
            escritor.println(mensaje3);

            System.out.println("Mensaje enviado : " + mensaje1);
            System.out.println("Mensaje enviado : " + mensaje2);
            System.out.println("Mensaje enviado : " + mensaje3);

            BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            List<String> mensaje = lector.readAllLines();


            System.out.println("Servidor dice " + mensaje);
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
