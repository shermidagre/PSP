package Ej29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
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

                    System.out.println("Envia una operacion para realizar al servidor");

                    String mensajeUsuario = sc.nextLine().trim();

                    System.out.println("Conectado al servidor ");

                    escritor.println(mensajeUsuario);

                if (mensajeUsuario.equals("adios")) {
                    break;
                } else {

                    String mensaje = mensajeUsuario.toLowerCase() + ":";

                    switch (mensajeUsuario.toLowerCase()) {
                        case "suma":
                        case "resta":
                        case "multiplicacion":
                        case "division":
                            System.out.print("Introduce el primer número: ");
                            String num1 = sc.nextLine();
                            System.out.print("Introduce el segundo número: ");
                            String num2 = sc.nextLine();
                            mensaje += num1 + ";" + num2;
                            break;

                        case "seno":
                        case "coseno":
                        case "tangente":
                            System.out.print("Introduce el ángulo en grados: ");
                            String angulo = sc.nextLine();
                            mensaje += angulo;
                            break;

                        case "interessimple":
                            System.out.print("Introduce el capital: ");
                            String capital = sc.nextLine();
                            System.out.print("Introduce la tasa de interés (%): ");
                            String tasa = sc.nextLine();
                            System.out.print("Introduce el tiempo en años: ");
                            String tiempo = sc.nextLine();
                            mensaje += capital + ";" + tasa + ";" + tiempo;
                            break;

                        default:
                            System.out.println("⚠️ Operación no reconocida.");
                            continue;
                    }
                    System.out.println("Mensaje enviado : " + mensaje);
                    String respuestasServidor = lector.readLine();
                    System.out.println("Seervidor dice : " + respuestasServidor);
                }
            }
            socket.close();
            System.out.println("Conexión cerrada.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


