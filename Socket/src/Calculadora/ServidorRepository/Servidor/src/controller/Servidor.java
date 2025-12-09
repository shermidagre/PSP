package controller;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Servidor de calculadora que acepta conexiones de clientes.
 * Implementa AutoCloseable para la gestión de recursos del ServerSocket.
 */
public class Servidor implements AutoCloseable {

    public static final int PUERTO_SERVIDOR = 5555;
    private final ServerSocket socketServidor;
    // manejo de los hilos hasta un maximo de clientes
    private final ExecutorService pool = Executors.newFixedThreadPool(2);

    // El MENSAJE_ERROR se ha movido a HiloCliente que es quien evalúa ahora

    /**
     * Crea e inicializa el ServerSocket y la instancia.
     * @throws IOException Si el puerto está en uso o hay un error de red.
     */
    public Servidor() throws IOException {
        socketServidor = new ServerSocket(PUERTO_SERVIDOR);

        System.out.println("SERVIDOR: Servidor iniciado en el puerto " + PUERTO_SERVIDOR + ".");
        System.out.println("SERVIDOR: Esperando conexión del cliente...");
    }

    /**
     * Bucle principal del servidor que acepta conexiones entrantes de forma continua.
     * Genera un nuevo Hilo por cada cliente (Nivel 2).
     */
    public void run() {
        while (true) {
            try {
                // Se queda esperando hasta que llega un cliente
                Socket socketCliente = socketServidor.accept();
                System.out.println("SERVIDOR: Cliente aceptado desde: " + socketCliente.getInetAddress());

                // Creamos el hilo encargado de este cliente y lo iniciamos
                pool.execute(new HiloCliente(socketCliente));

            } catch (IOException e) {
                System.err.println("SERVIDOR: Error al aceptar conexión de un cliente.");
                e.printStackTrace();
            }
        }
    }

    // Log
    public static synchronized void escribirLog(String ip, String operacion, String resultado) {
        try (FileWriter fw = new FileWriter("log.txt", true); // 'true' para no borrar lo anterior
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println("[" + LocalDateTime.now() + "] IP: " + ip +
                    " | Op: " + operacion + " | Res: " + resultado);

        } catch (IOException e) {
            System.err.println("No se pudo escribir en el log.");
        }
    }

    /**
     * Cierra el ServerSocket (implementación de AutoCloseable).
     */
    @Override
    public void close() throws Exception {
        if (socketServidor != null) {
            socketServidor.close();
        }
        System.out.println("SERVIDOR: Servidor apagado.");
    }
}