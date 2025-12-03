package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 * Hilo dedicado a atender a un único cliente.
 * Mantiene el bucle de servicio hasta que el cliente se desconecta.
 */
public class HiloCliente extends Thread {

    private final Socket socketCliente;
    private static final String MENSAJE_ERROR = "Error en el procesamiento de la expresión.";

    public HiloCliente(Socket socketCliente) {
        this.socketCliente = socketCliente;
    }

    /**
     * Lógica principal del hilo. Lee expresiones continuamente.
     */
    @Override
    public void run() {
        boolean autoFlush = true;

        try (BufferedReader lector = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
             PrintWriter respuestaACliente = new PrintWriter(socketCliente.getOutputStream(), autoFlush)) {

            String expresionDesdeCliente;

            // Bucle de lectura continua (Requisito Nivel 1: atender peticiones hasta desconexión)
            while ((expresionDesdeCliente = lector.readLine()) != null) {

                System.out.println("HILO SERVIDOR: Operacion recibida: " + expresionDesdeCliente);

                String resultado = evaluarFragmentoConExp4j(expresionDesdeCliente);

                System.out.println("HILO SERVIDOR: Operacion procesada: " + expresionDesdeCliente + " -> Resultado: " + resultado);

                if (resultado.equals(MENSAJE_ERROR)) {
                    System.err.println("HILO SERVIDOR: Error fatal. El cálculo falló para la expresión: " + expresionDesdeCliente);
                }

                // Envía la respuesta (resultado o mensaje de error) al cliente
                respuestaACliente.println(resultado);

                Servidor.escribirLog(socketCliente.getInetAddress().toString(), expresionDesdeCliente, resultado);
            }

        } catch (IOException e) {
            System.out.println("HILO SERVIDOR: El cliente se ha desconectado o hubo error de I/O.");
        } finally {
            try {
                socketCliente.close();
            } catch (IOException e) { e.printStackTrace(); }
        }
    }

    /**
     * Evalúa la expresión matemática recibida utilizando la librería exp4j.
     * (Método movido desde la clase Servidor original, conservando comentarios).
     * @param expresion La cadena de expresión a evaluar (ej: "5+5", "sen(45)*2").
     * @return El resultado de la evaluación como String, o un mensaje de error.
     */
    private String evaluarFragmentoConExp4j(String expresion) {
        // System.out.println("SERVIDOR exp4j: Evaluando expresión: " + expresion);

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
            // Captura cualquier otro error durante la evaluación
            System.err.println("SERVIDOR exp4j: Error desconocido durante la evaluación: " + e.getMessage());
            return MENSAJE_ERROR;
        }
    }
}