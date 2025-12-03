package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text; // Se mantiene por si necesitas el tipo Text en otro lugar, pero ya no se usa para el historial

/**
 * Controlador de la ventana principal de la calculadora.
 * Gestiona la lógica de entrada de datos, las operaciones combinadas y
 * la comunicación con el servidor.
 */
public class CalculadoraController {

	// Necesitas una clase 'ConexionCliente' para que esto funcione.
	private ConexionCliente conexionCliente;

	// Nombres de variables FXML
	@FXML
	private TextField campoResultado; // Ventana donde se muestra el número actual o resultado (el box grande)
	@FXML
	private TextField campoHistorial; // NUEVO: Ventana donde se muestra la expresión parcial (el box blanco)
	// Variables de estado de la calculadora
	private String operandoActual = "0";
	private boolean esperandoNuevoOperando = false; // Indicador para manejar el inicio de un nuevo número después de un cálculo

	/**
	 * Inyecta la conexión de red (ConexionCliente) desde la clase Principal.
	 * @param conexionCliente La instancia de conexión al servidor.
	 */
	public void setConexionCliente(ConexionCliente conexionCliente) {
		this.conexionCliente = conexionCliente;
	}

	/* --- Acciones de Botones Numéricos --- */

	@FXML void boton0Clicado() { manejarEntradaDigito("0"); }
	@FXML void boton1Clicado() { manejarEntradaDigito("1"); }
	@FXML void boton2Clicado() { manejarEntradaDigito("2"); }
	@FXML void boton3Clicado() { manejarEntradaDigito("3"); }
	@FXML void boton4Clicado() { manejarEntradaDigito("4"); }
	@FXML void boton5Clicado() { manejarEntradaDigito("5"); }
	@FXML void boton6Clicado() { manejarEntradaDigito("6"); }
	@FXML void boton7Clicado() { manejarEntradaDigito("7"); }
	@FXML void boton8Clicado() { manejarEntradaDigito("8"); }
	@FXML void boton9Clicado() { manejarEntradaDigito("9"); }

	/**
	 * Maneja la entrada de dígitos, reseteando el campoResultado si es necesario
	 * (e.g., después de un resultado).
	 * @param digito El dígito (0-9) a añadir.
	 */
	private void manejarEntradaDigito(String digito) {
		if (operandoActual.equals("0")) {
		    operandoActual = digito;
		}
        else {
            operandoActual += digito;
        }
		actualizarCampoResultado();
	}

	/**
	 * Actualiza el TextField de la interfaz con el valor de operandoActual.
	 */
	private void actualizarCampoResultado() {
		campoResultado.setText(operandoActual);
	}

	/* --- Lógica de Punto Decimal --- */

	/**
	 * Añade un punto decimal al operando actual si aún no lo tiene.
     * Hago esta comprobacion porque si no se buguea con el 0.5
	 */
	@FXML
    void manejarPuntoDecimal() {
        if (!operandoActual.contains(".")) {
            operandoActual += ".";
            actualizarCampoResultado();
        }
    }
	/* --- Acciones de Botones de Operación --- */

	@FXML void accionSumar() { configurarCalculo("+"); }
	@FXML void accionRestar() { configurarCalculo("-"); }
	@FXML void accionDividir() { configurarCalculo("/"); }
	@FXML void accionMultiplicar() { configurarCalculo("*"); }

	/**
	 * Configura la operación matemática.
	 * Implementa la LÓGICA COMBINADA: Si ya hay un primer operando y se presiona
	 * un nuevo operador, primero calcula el resultado parcial antes de continuar.
	 * @param proximoOperador El operador a usar (+, -, *, /).
	 */
	public void configurarCalculo(String proximoOperador) {
        // Si el operando actual es "0" (vacío), no agregamos un operador al inicio.
        if (!operandoActual.equals("0")) {
            // Simplemente concatenamos el operador (exp4j maneja la precedencia).
            operandoActual += proximoOperador;
            // La expresión completa se muestra en campoResultado
            actualizarCampoResultado();
        }
        // El historial ya no se usa para encadenar operaciones parciales.
        campoHistorial.setText(""); // limpiar historial
	}

    // Logica de calculo
	@FXML
	void calcular() {
        // Solo enviamos si hay algo que no sea solo "0"
        if (operandoActual.length() > 0 && !operandoActual.equals("0")) {
            try {
                String expresionAEnviar = operandoActual;

                // Llama al nuevo método simplificado de envío
                String resultadoCadena = enviarExpresionAServidor(expresionAEnviar);

                campoHistorial.setText(expresionAEnviar + " = ");

                // Muestra el resultado final y lo guarda como la nueva base
                operandoActual = resultadoCadena;
                campoResultado.setText(resultadoCadena);

            } catch (IOException e) {
                manejarErrorConexion();
            }
        }
	}

    /**
     * Envía la expresión completa al servidor y devuelve el resultado.
     * @param expresionAEnviar La cadena completa a evaluar (ej: "5*5+2").
     * @return El resultado de la operación.
     * @throws IOException Si falla la conexión.
     */
    private String enviarExpresionAServidor(String expresionAEnviar) throws IOException {
        if (conexionCliente == null) {
            throw new IOException("Conexión al servidor no inicializada.");
        }
        // Envío y recepción de la expresión completa
        String resultadoCadena = conexionCliente.enviarExpresion(expresionAEnviar);

        return resultadoCadena;
    }

	/**
	 * Muestra un mensaje de error de conexión en la pantalla.
	 */
	private void manejarErrorConexion() {
		String mensajeError = "ERROR DE CONEXIÓN";
		campoResultado.setText(mensajeError);
		limpiarEstado();
		System.err.println("Error al comunicarse con el servidor. Verifique que el servidor esté activo.");
	}

    // Logica de borrado

	@FXML
	void limpiarPantalla() {
		limpiarEstado();
		campoResultado.setText("0"); // Establece 0 como valor inicial
		campoHistorial.setText(""); // Limpia el historial
	}

	/**
	 * Resetea todas las variables de estado de la calculadora.
	 */
	private void limpiarEstado() {
		operandoActual = "0";
		esperandoNuevoOperando = false;
	}
}