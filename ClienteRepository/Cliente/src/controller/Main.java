package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Clase principal de la aplicación cliente de la calculadora.
 * Responsable de inicializar la interfaz de usuario y la conexión al servidor.
 */
public class Main extends Application {

	// Nota: Las dimensiones deben coincidir con la última versión de FXML (400x500)
	private static final double ANCHO_MINIMO = 400.00;
	private static final double ALTO_MINIMO = 500.00;
	private static final String TITULO_APP = "Calculadora Cliente-Servidor";
	private static final String FXML_PATH = "/view/view.fxml";
	private static final String CSS_PATH = "/view/styles.css";
	private static final String IP_SERVIDOR = "127.0.0.1"; // localhost
	private static final int PUERTO_SERVIDOR = 5555;

	private Stage escenarioPrincipal;

	/**
	 * Punto de entrada principal para la aplicación JavaFX.
	 * @param escenarioPrincipal El objeto Stage (ventana) principal.
	 */
	@Override
	public void start(Stage escenarioPrincipal) {
		this.escenarioPrincipal = escenarioPrincipal;
		inicializarVentanaPrincipal();
	}

	/**
	 * Carga el archivo FXML y configura la ventana principal.
	 */
	public void inicializarVentanaPrincipal() {
		try {
			FXMLLoader cargadorFxml = new FXMLLoader(getClass().getResource(FXML_PATH));
			AnchorPane raiz = (AnchorPane) cargadorFxml.load();
			// Usando las dimensiones actualizadas para el diseño futurista
			Scene escena = new Scene(raiz, ANCHO_MINIMO, ALTO_MINIMO);

			// Aplica la hoja de estilos CSS
			escena.getStylesheets().add(getClass().getResource(CSS_PATH).toExternalForm());

			// Obtiene el controlador y le inyecta la conexión de red
			CalculadoraController calculadoraController = cargadorFxml.getController();
			ConexionCliente conexionCliente = new ConexionCliente(IP_SERVIDOR, PUERTO_SERVIDOR);
			calculadoraController.setConexionCliente(conexionCliente);

			// Configuración de la ventana (Stage)
			escenarioPrincipal.setMinWidth(ANCHO_MINIMO);
			escenarioPrincipal.setMinHeight(ALTO_MINIMO);
			escenarioPrincipal.setTitle(TITULO_APP);
			escenarioPrincipal.setResizable(true); // Se dejó redimensionable en la última versión
			escenarioPrincipal.setScene(escena);
			escenarioPrincipal.show();

		} catch (Exception e) {
			System.err.println("Error al cargar la ventana principal o iniciar la conexión:");
			e.printStackTrace();
		}
	}

	/**
	 * El método main estándar.
	 * @param args Argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}