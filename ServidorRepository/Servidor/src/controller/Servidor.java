package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;
import jdk.jshell.Snippet;

/**
 * Servidor de calculadora que acepta conexiones de clientes y evalúa expresiones
 * matemáticas utilizando JShell.
 * Implementa AutoCloseable para la gestión de recursos del ServerSocket.
 */
public class Servidor implements AutoCloseable {

	public static final int PUERTO_SERVIDOR = 5555;
	private final ServerSocket socketServidor;
	private static final String MENSAJE_ERROR = "Error en el procesamiento de la expresión.";
	private final JShell evaluadorJshell;

	/**
	 * Crea e inicializa el ServerSocket y la instancia de JShell.
	 * @throws IOException Si el puerto está en uso o hay un error de red.
	 */
	public Servidor() throws IOException {
		socketServidor = new ServerSocket(PUERTO_SERVIDOR);
		evaluadorJshell = JShell.create();

		System.out.println("SERVIDOR: Servidor iniciado en el puerto " + PUERTO_SERVIDOR + ".");
		System.out.println("SERVIDOR: Esperando conexión del cliente...");
	}

	/**
	 * Bucle principal del servidor que acepta conexiones entrantes de forma continua.
	 */
	public void run() {
		while (true) {
			try (Socket socketCliente = socketServidor.accept()) {
				System.out.println("SERVIDOR: Cliente aceptado.");
				procesarExpresionCliente(socketCliente);
			} catch (IOException e) {
				System.err.println("SERVIDOR: Error al aceptar o procesar la conexión de un cliente.");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Lee la expresión del cliente, la procesa y envía la respuesta.
	 * @param socketCliente El socket de comunicación con el cliente.
	 */
	private void procesarExpresionCliente(Socket socketCliente) {
		boolean autoFlush = true;

		try (BufferedReader lector = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
			 PrintWriter respuestaACliente = new PrintWriter(socketCliente.getOutputStream(), autoFlush)) {

			String expresionDesdeCliente = lector.readLine();
			System.out.println("SERVIDOR: Tarea recibida: " + expresionDesdeCliente);

			String resultado = evaluarFragmentoConJShell(expresionDesdeCliente);

			System.out.println("SERVIDOR: Expresión procesada: " + expresionDesdeCliente + " -> Resultado: " + resultado);

			if (resultado.equals(MENSAJE_ERROR)) {
				System.err.println("SERVIDOR: Error fatal. El cálculo falló para la expresión: " + expresionDesdeCliente);
			}

			// Envía la respuesta (resultado o mensaje de error) al cliente
			respuestaACliente.println(resultado);

		} catch (IOException e) {
			System.err.println("SERVIDOR: Error de I/O al comunicarse con el cliente.");
			e.printStackTrace();
		}
	}

	/**
	 * Evalúa la expresión matemática recibida utilizando la API JShell.
	 * @param expresion La cadena de expresión a evaluar (ej: "5+5").
	 * @return El resultado de la evaluación como String, o un mensaje de error.
	 */
	private String evaluarFragmentoConJShell(String expresion) {
		System.out.println("SERVIDOR JShell: Evaluando expresión: " + expresion);

		// El método eval devuelve una lista de eventos
		List<SnippetEvent> eventos = evaluadorJshell.eval(expresion);

		System.out.println("SERVIDOR JShell: Eventos de evaluación generados: " + eventos.size());

		// Se asume que solo hay un evento por una expresión simple.
		if (!eventos.isEmpty() && eventos.get(0).causeSnippet() == null) {
			SnippetEvent evento = eventos.get(0);

			// Verifica que la evaluación sea válida y tenga un valor de retorno
			if (evento.status() == Snippet.Status.VALID && evento.value() != null) {
				return evento.value(); // El valor del resultado como String
			}
		}
		return MENSAJE_ERROR;
	}

	/**
	 * Cierra el ServerSocket (implementación de AutoCloseable).
	 * También cierra la instancia de JShell.
	 */
	@Override
	public void close() throws Exception {
		if (socketServidor != null) {
			socketServidor.close();
		}
		if (evaluadorJshell != null) {
			evaluadorJshell.close();
		}
		System.out.println("SERVIDOR: Servidor apagado.");
	}

}