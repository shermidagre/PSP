package Ej28;

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
                    escritor.println("ECO ->> " + mensaje);
                    System.out.println("1 - Mensaje hardcodeado : " + mensaje +" hardcodeado" );
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
}
