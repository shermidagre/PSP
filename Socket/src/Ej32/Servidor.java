package Ej32;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Servidor {
    void main() {

        String mensajeUsuarioAntiguo = null;
        String mensajeUsuario = null;

        try {
            DatagramSocket socket = new DatagramSocket(6666);
            DatagramSocket socketEnvio = new DatagramSocket();
            InetAddress ipDestino = InetAddress.getByName("localhost");

            byte [] bufferRecepcion = new byte [1024];


            DatagramPacket paquete = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);

            System.out.println("Esperando conexiones");

            while (true){
                socket.receive(paquete); // bloquea el paquete
                System.out.println("Cliente conectado ");
                comprobarMensaje(mensajeUsuario,mensajeUsuarioAntiguo);
                enviarMensaje(socketEnvio, ipDestino, mensajeUsuario);
            }


            }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void comprobarMensaje(String mensajeUsuario, String mensajeUsuarioAntiguo) {

        if (mensajeUsuario.length() > mensajeUsuarioAntiguo.length() ){
            System.out.println("El mensaje es más largo que el anterior");
            mensajeUsuarioAntiguo = mensajeUsuario;

        } else if (mensajeUsuario.length() < mensajeUsuarioAntiguo.length()){
            System.out.println("El mensaje es más corto que el anterior");

        } else if(mensajeUsuario.length() == mensajeUsuarioAntiguo.length()){
            System.out.println("El mensaje tiene la misma longitud que el anterior");
        }else {
            System.out.println("Es el primer mensaje recibido");
        }
    }

    public static void enviarMensaje(DatagramSocket socketEnvio, InetAddress ipDestino, String mensajeUsuario) {
        try {
            byte[] bufferEnvio = mensajeUsuario.getBytes();
            DatagramPacket paquete1 = new DatagramPacket(bufferEnvio, bufferEnvio.length, ipDestino, 6666);

            System.out.println("Paquetes a enviar:" + mensajeUsuario);

            socketEnvio.send(paquete1);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
