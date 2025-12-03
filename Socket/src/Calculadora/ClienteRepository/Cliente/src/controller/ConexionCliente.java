package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Maneja la conexión de red y la comunicación de expresiones con el servidor.
 * Implementa AutoCloseable para asegurar el cierre del socket.
 */
public class ConexionCliente implements AutoCloseable {

    private Socket socketCliente;
    private final String direccionServidor;
    private final int numeroPuerto;

    // Variables para mantener los flujos abiertos (Persistencia - Nivel 1)
    private PrintWriter escritorSalida;
    private BufferedReader lectorBuffer;

    /**
     * Constructor para la conexión del cliente.
     * @param direccionServidor La dirección IP del servidor.
     * @param numeroPuerto El número de puerto para la conexión.
     */
    public ConexionCliente(String direccionServidor, int numeroPuerto) throws IOException {
        this.direccionServidor = direccionServidor;
        this.numeroPuerto = numeroPuerto;
        System.out.println("CLIENTE: Intentando conectar con el servidor...");

        // MOVIDO AQUÍ: Intentamos establecer la conexión una sola vez al iniciar
        try {
            this.socketCliente = new Socket(direccionServidor, numeroPuerto);
            this.escritorSalida = new PrintWriter(socketCliente.getOutputStream(), true); // AutoFlush = true
            this.lectorBuffer = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));

            System.out.println("CLIENTE: Conexión establecida con el servidor (Persistente).");
        } catch (UnknownHostException e) {
            System.err.println("CLIENTE: Error: Servidor no encontrado en la dirección " + direccionServidor);
            throw e;
        } catch (IOException e) {
            System.err.println("CLIENTE: Error de I/O al conectar.");
            throw e;
        }
    }

    /**
     * Envía una expresión y espera la respuesta del servidor usando la conexión existente.
     * @param expresion La expresión matemática a enviar (ej: "5*5").
     * @return La respuesta (resultado) del servidor como String.
     * @throws IOException Si ocurre un error de conexión o I/O.
     */
    public String enviarExpresion(String expresion) throws IOException {
        String respuestaDesdeServidor = "ERROR_CONEXION";

        // Ya no creamos el socket aquí ("try-with-resources" eliminado de este método para no cerrar conexión)
        try {
            // 1. Envío de la expresión
            System.out.println("CLIENTE: Enviando expresión: " + expresion);
            escritorSalida.println(expresion);

            // 2. Lectura de la respuesta
            respuestaDesdeServidor = lectorBuffer.readLine();
            System.out.println("CLIENTE: Respuesta recibida del servidor: " + respuestaDesdeServidor);

            if (respuestaDesdeServidor == null) {
                throw new IOException("El servidor ha cerrado la conexión.");
            }

        } catch (IOException e) {
            System.err.println("CLIENTE: Error al leer/enviar datos al servidor.");
            e.printStackTrace();
            throw e;
        }

        return respuestaDesdeServidor;
    }

    /**
     * Cierra el socket del cliente si está abierto (implementación de AutoCloseable).
     */
    @Override
    public void close() throws Exception {
        if (socketCliente != null && !socketCliente.isClosed()) {
            System.out.println("CLIENTE: Cerrando conexión de socket.");
            socketCliente.close();
        }
    }
}