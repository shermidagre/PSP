package Ej32;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Servidor {
    void main() {

        String mensajeUsuarioAntiguo = null;

        try(DatagramSocket socket = new DatagramSocket(6666)){

            System.out.println("Esperando conexiones");
            byte [] bufferRecepcion = new byte [1024];
            DatagramPacket paquete = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);


            while (true){
                socket.receive(paquete); // bloquea el paquete
                String mensajeUsuario = new String(paquete.getData(), 0, paquete.getLength()).trim();
                System.out.println("Recibido: " + mensajeUsuario);

                System.out.println("Cliente conectado ");

                mensajeUsuarioAntiguo = comprobarMensaje(mensajeUsuario, mensajeUsuarioAntiguo);

                String respuesta = "Recibido: " + mensajeUsuario;
                byte[] bufferEnvio = respuesta.getBytes();
                DatagramPacket paqueteEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length,
                        paquete.getAddress(), paquete.getPort());
                socket.send(paqueteEnvio);

            }


        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static String comprobarMensaje(String mensajeUsuario, String mensajeUsuarioAntiguo) {

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
        return mensajeUsuario;
    }
}
