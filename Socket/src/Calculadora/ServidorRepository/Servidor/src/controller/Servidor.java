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
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 * Servidor de calculadora que acepta conexiones de clientes y evalúa expresiones
 * matemáticas utilizando JShell.
 * Implementa AutoCloseable para la gestión de recursos del ServerSocket.
 */
public class Servidor implements AutoCloseable {

	public static final int PUERTO_SERVIDOR = 5555;
	private final ServerSocket socketServidor;
	private static final String MENSAJE_ERROR = "Error en el procesamiento de la expresión.";

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
			System.out.println("SERVIDOR: Operacion recibida: " + expresionDesdeCliente);

			String resultado = evaluarFragmentoConExp4j(expresionDesdeCliente);

			System.out.println("SERVIDOR: Operacion procesada: " + expresionDesdeCliente + " -> Resultado: " + resultado);

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
     * Evalúa la expresión matemática recibida utilizando la librería exp4j.
     * @param expresion La cadena de expresión a evaluar (ej: "5+5", "sen(45)*2").
     * @return El resultado de la evaluación como String, o un mensaje de error.
     */
    private String evaluarFragmentoConExp4j(String expresion) {
        System.out.println("SERVIDOR exp4j: Evaluando expresión: " + expresion);

        try {
            // 1. Crear el constructor de la expresión
            ExpressionBuilder builder = new ExpressionBuilder(expresion);

            // 2. Construir la expresión (no necesitamos variables, solo constantes/funciones)
            Expression e = builder.build();

            // 3. Evaluar la expresión
            double resultado = e.evaluate();

            // 4. Devolver el resultado formateado
            return String.valueOf(resultado);

        } catch (IllegalArgumentException e) {
            System.err.println("SERVIDOR exp4j: Error de sintaxis o función inválida: " + e.getMessage());
            return MENSAJE_ERROR;
        } catch (Exception e) {
            // Captura cialquier otro error durante la evaluación
            System.err.println("SERVIDOR exp4j: Error desconocido durante la evaluación: " + e.getMessage());
            return MENSAJE_ERROR;
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