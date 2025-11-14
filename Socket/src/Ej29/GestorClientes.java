package Ej29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GestorClientes extends Thread {

    private Socket socket;

    public GestorClientes(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        try (
                BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true)) {

            // Logica

            String mensaje;
            while (true) {
                mensaje = lector.readLine();
                if (mensaje.equals("adios")){
                    break;
                }else {
                    String resultado = procesarOperacion(mensaje);
                    escritor.println(resultado);
                    System.out.println("Servidor procesó: " + mensaje + " -> Resultado: " + resultado);
                }

            }


        } catch (IOException ex) {
            throw new RuntimeException( "Error con el cliente:" + ex.getMessage());
        }finally {
            try {
                socket.close();
                System.out.println("Cliente desconectado ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private String procesarOperacion(String entrada) {
        try {
            String[] partes = entrada.split(";");
            if (partes.length == 0) return "error: formato inválido";

            String operacion = partes[0].trim().toLowerCase();

            switch (operacion) {
                case "suma":
                    if (partes.length != 3) return "error: se necesitan 2 operandos";
                    double a = Double.parseDouble(partes[1]);
                    double b = Double.parseDouble(partes[2]);
                    return String.valueOf(a + b);

                case "resta":
                    if (partes.length != 3) return "error: se necesitan 2 operandos";
                    a = Double.parseDouble(partes[1]);
                    b = Double.parseDouble(partes[2]);
                    return String.valueOf(a - b);

                case "multiplicacion":
                    if (partes.length != 3) return "error: se necesitan 2 operandos";
                    a = Double.parseDouble(partes[1]);
                    b = Double.parseDouble(partes[2]);
                    return String.valueOf(a * b);

                case "division":
                    if (partes.length != 3) return "error: se necesitan 2 operandos";
                    a = Double.parseDouble(partes[1]);
                    b = Double.parseDouble(partes[2]);
                    if (b == 0) return "error: división por cero";
                    return String.valueOf(a / b);

                case "seno":
                    if (partes.length != 2) return "error: se necesita 1 valor en grados";
                    double angulo = Double.parseDouble(partes[1]);
                    return String.valueOf(Math.sin(Math.toRadians(angulo)));

                case "coseno":
                    if (partes.length != 2) return "error: se necesita 1 valor en grados";
                    angulo = Double.parseDouble(partes[1]);
                    return String.valueOf(Math.cos(Math.toRadians(angulo)));

                case "tangente":
                    if (partes.length != 2) return "error: se necesita 1 valor en grados";
                    angulo = Double.parseDouble(partes[1]);
                    return String.valueOf(Math.tan(Math.toRadians(angulo)));

                case "interessimple":
                    if (partes.length != 4) return "error: se necesitan capital, tasa y tiempo";
                    double capital = Double.parseDouble(partes[1]);
                    double tasa = Double.parseDouble(partes[2]) / 100; // % a decimal
                    double tiempo = Double.parseDouble(partes[3]);
                    return String.valueOf(capital * tasa * tiempo);

                default:
                    return "error: operación no reconocida";
            }

        } catch (NumberFormatException e) {
            return "error: valores numéricos inválidos";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }
}
