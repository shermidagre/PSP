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


    void main() throws IOException {
        try {

            InetSocketAddress dir = new InetSocketAddress("localhost", 6666);

            Scanner sc = new Scanner(System.in);

            Socket socket = new Socket();

            socket.connect(dir);

            PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));



            while (true) {

                    System.out.println("Envia un mensaje al servidor");
                    String mensajeUsuario = sc.nextLine();

                    System.out.println("Conectado al servidor ");

                    escritor.println(mensajeUsuario);

                if (mensajeUsuario.equals("adios")) {
                    break;
                } else {

                    System.out.println("Mensaje enviado : " + mensajeUsuario);


                    String respuestasServidor = lector.readLine();


                    System.out.println("Seervidor dice : " + respuestasServidor);

                }
            }
            socket.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


