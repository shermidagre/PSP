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

	/**
	 * Constructor para la conexión del cliente.
	 * @param direccionServidor La dirección IP del servidor.
	 * @param numeroPuerto El número de puerto para la conexión.
	 */
	public ConexionCliente(String direccionServidor, int numeroPuerto) {
		this.direccionServidor = direccionServidor;
		this.numeroPuerto = numeroPuerto;
		System.out.println("CLIENTE: Intentando conectar con el servidor...");
	}

	/**
	 * Establece una conexión temporal, envía una expresión y espera la respuesta del servidor.
	 * @param expresion La expresión matemática a enviar (ej: "5*5").
	 * @return La respuesta (resultado) del servidor como String.
	 * @throws IOException Si ocurre un error de conexión o I/O.
	 */
	public String enviarExpresion(String expresion) throws IOException {
		String respuestaDesdeServidor = "ERROR_CONEXION";

		System.out.println("CLIENTE: Creando socket para enviar. Puerto: " + numeroPuerto + ", Dirección: " + direccionServidor);

		// Intentamos establecer la conexión y usar el recurso dentro del try-with-resources
		try (Socket socketConexion = new Socket(direccionServidor, numeroPuerto)) {
			this.socketCliente = socketConexion; // Se asigna para el AutoCloseable final
			System.out.println("CLIENTE: Conexión establecida con el servidor.");

			// 1. Envío de la expresión
			System.out.println("CLIENTE: Enviando expresión: " + expresion);
			PrintWriter escritorSalida = new PrintWriter(socketCliente.getOutputStream(), true); // AutoFlush = true
			escritorSalida.println(expresion);

			// 2. Lectura de la respuesta
			try (InputStreamReader lectorStream = new InputStreamReader(socketCliente.getInputStream());
				 BufferedReader lectorBuffer = new BufferedReader(lectorStream)) {

				respuestaDesdeServidor = lectorBuffer.readLine();
				System.out.println("CLIENTE: Respuesta recibida del servidor: " + respuestaDesdeServidor);

			} catch (IOException e) {
				System.err.println("CLIENTE: Error al leer la respuesta del servidor.");
				e.printStackTrace();
			}

		} catch (UnknownHostException e) {
			System.err.println("CLIENTE: Error: Servidor no encontrado en la dirección " + direccionServidor);
			throw e;
		} catch (IOException e) {
			System.err.println("CLIENTE: Error de I/O al conectar o enviar/recibir datos.");
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