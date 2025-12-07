package Ej32;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Servidor {
    void main() {

        String mensajeUsuarioAntiguo = null;

       // String mensajeUsuario = null; ya entendi a lo que te referias, lo dejo comentado en forma a que lo borro

        try(DatagramSocket socket = new DatagramSocket(5555)) {

            System.out.println("Servidor iniciado en el puerto 5555.");

            byte[] bufferRecepcion = new byte[1024];
            DatagramPacket paquete = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);

            InetAddress ipDestino = InetAddress.getByName("localhost");

            System.out.println("Esperando conexiones");

            while (true) {
                socket.receive(paquete); // bloquea el paquete
                System.out.println("Cliente conectado ");

                // Conversión de bytes a String
                String mensajeUsuario = new String(paquete.getData(), 0, paquete.getLength()).trim();
                System.out.println("Mensaje Recibido de " + paquete.getAddress().getHostAddress() + ":" + paquete.getPort());
                System.out.println("Contenido : " + mensajeUsuario);


                mensajeUsuarioAntiguo = comprobarMensaje(mensajeUsuario, mensajeUsuarioAntiguo);

                String respuesta = "Respuesta servidor : " + mensajeUsuarioAntiguo;
                byte[] bufferEnvio = respuesta.getBytes();

                DatagramPacket paqueteEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length,
                        paquete.getAddress(), paquete.getPort());

                socket.send(paqueteEnvio);
                System.out.println("Respuesta enviada: '" + respuesta + "'");
            }
            }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    // tuve que pasar la funcion a string porque si no no iba a hacer el return del mensaje
    public static String comprobarMensaje(String mensajeUsuario, String mensajeUsuarioAntiguo) {

        if (mensajeUsuarioAntiguo == null) {
            System.out.println("Es el primer mensaje recibido.");
        } else if (mensajeUsuario.length() > mensajeUsuarioAntiguo.length()) {
            System.out.println("El mensaje es más largo que el anterior.");
        } else if (mensajeUsuario.length() < mensajeUsuarioAntiguo.length()) {
            System.out.println("El mensaje es más corto que el anterior.");
        } else { // mensajeActual.length() == mensajeUsuarioAntiguo.length()
            System.out.println("El mensaje tiene la misma longitud que el anterior.");
        }
        return mensajeUsuario;
    }
}
