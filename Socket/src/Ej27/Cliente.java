package Ej27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Cliente {

    InetSocketAddress dir = new InetSocketAddress("localhost", 6666);

    void main() throws IOException {
        Scanner sc = new Scanner(System.in);
        String mensajeUsuario = "";

        try {
            while (mensajeUsuario != "adios"){

            System.out.println("Envia un mensaje al servidor");
            mensajeUsuario = sc.nextLine();

            Socket socket = new Socket();
            socket.connect(dir);

            System.out.println("Conectado al servidor ");

            PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);
            escritor.println(mensajeUsuario);

            System.out.println("Mensaje enviado : " + mensajeUsuario);

            BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            List<String> respuestasServidor = lector.readAllLines();


            System.out.println("Servidor dice " + respuestasServidor);

            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


