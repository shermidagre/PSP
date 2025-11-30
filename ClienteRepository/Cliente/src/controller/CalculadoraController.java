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
	private String primerOperando = "0";
	private String operandoActual = "0";
	private String tipoOperador; // Tipo de operación matemática (+, -, *, /)
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
		// Si ya hay un resultado o se acaba de presionar un operador,
		// reiniciamos el operandoActual para empezar el nuevo número.
		if (esperandoNuevoOperando) {
			operandoActual = "";
			esperandoNuevoOperando = false;
		}

		// Evita ceros múltiples al inicio (ej: 0005)
		if (operandoActual.equals("0") && digito.equals("0")) {
			return;
		}

		// Si el campo está '0' (inicio) y se presiona un dígito, se limpia.
		if (operandoActual.equals("0") && digito.matches("[1-9]")) {
			operandoActual = digito;
		} else {
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
	 */
	@FXML
	void manejarPuntoDecimal() {
		if (esperandoNuevoOperando) {
			operandoActual = "0";
			esperandoNuevoOperando = false;
		}
		if (!operandoActual.contains(".")) {
			operandoActual += ".";
		}
		actualizarCampoResultado();
	}

	// NOTA: Los métodos 'cambiarSigno' y 'borrarUltimoDigito' fueron eliminados
	// porque sus botones ya no están en el FXML para simplificar la interfaz.

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
		// --- LÓGICA DE OPERACIONES COMBINADAS ---
		// Si ya tenemos un primer operando Y el operando actual no está vacío,
		// significa que queremos encadenar operaciones.
		if (!primerOperando.equals("0") && !operandoActual.equals("0") && !esperandoNuevoOperando) {
			try {
				// 1. Realiza el cálculo parcial con el operador anterior.
				String resultadoParcial = enviarExpresionAServidor(primerOperando, tipoOperador, operandoActual, false);

				// 2. Este resultado se convierte en el nuevo 'primerOperando' para la siguiente operación.
				primerOperando = resultadoParcial;
				operandoActual = "0"; // Resetea el actual
			} catch (IOException e) {
				manejarErrorConexion();
				return; // Detiene la configuración si hay un error
			}
		} else if (!operandoActual.equals("0")) {
			// Si es la primera operación, simplemente mueve el número a primerOperando.
			primerOperando = operandoActual;
			operandoActual = "0";
		}

		this.tipoOperador = proximoOperador;
		esperandoNuevoOperando = true;
		campoHistorial.setText(primerOperando + " " + tipoOperador);
	}

	/* --- Lógica de Resultado y Borrado --- */

	@FXML
	void calcular() {
		if (primerOperando.equals("0") || operandoActual.equals("0") || tipoOperador == null) {
			return; // No hay suficientes operandos
		}

		try {
			String resultadoFinal = enviarExpresionAServidor(primerOperando, tipoOperador, operandoActual, true);

			// Muestra el resultado final
			operandoActual = resultadoFinal;
			campoResultado.setText(resultadoFinal);
			primerOperando = "0"; // Resetea el primer operando
			tipoOperador = null; // Resetea el operador
			esperandoNuevoOperando = true; // Permite que el siguiente número borre el resultado

		} catch (IOException e) {
			manejarErrorConexion();
		}
	}

	/**
	 * Envía la expresión al servidor y devuelve el resultado.
	 * @param op1 Primer operando.
	 * @param op Operador.
	 * @param op2 Segundo operando.
	 * @param esFinal Si es la operación final (presionando '=').
	 * @return El resultado de la operación.
	 * @throws IOException Si falla la conexión.
	 */
	private String enviarExpresionAServidor(String op1, String op, String op2, boolean esFinal) throws IOException {
		if (conexionCliente == null) {
			throw new IOException("Conexión al servidor no inicializada.");
		}

		String expresionAEnviar = op1 + op + op2;

		// Actualiza el historial solo en el cálculo final o al encadenar
		if (esFinal) {
			campoHistorial.setText(op1 + " " + op + " " + op2 + " = ");
		} else {
			// Mantener el historial de la operación actual al encadenar
			campoHistorial.setText(op1 + " " + op + " " + op2);
		}

		String resultadoCadena = conexionCliente.enviarExpresion(expresionAEnviar);

		// Verifica si el resultado es un número válido para evitar errores de JShell
		try {
			Double.parseDouble(resultadoCadena);
		} catch (NumberFormatException e) {
			return "ERROR"; // Devuelve un error si el servidor no pudo evaluar
		}

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
		primerOperando = "0";
		operandoActual = "0";
		tipoOperador = null;
		esperandoNuevoOperando = false;
	}
}