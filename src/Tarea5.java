import java.io.IOException;
import java.util.Scanner;

/*


Crea un programa en Java que esté en bucle realizando lo siguiente:

1. Pide por consola al usuario un comando y sus parámetros a ejecutar
(si fuese necesario) (Por ejemplo, ls, gnome-text-editor, open...)
2. Lanza el proceso y obtén el código de finalización del mismo.
3. Muestra el nombre del programa y el código de finalización del mismo.

El programa finaliza cuando un usuario introduce “salir” y devolverá un código de
salida = 0. Prueba a introducir comandos y/o parámetros inexistentes y observa el
código de finalización.

 */


public class Tarea5 {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        String comando = "";
        while (comando != "salir") {
            System.out.println("si quieres salir escribe salir");
            System.out.println("Escribe los comandos que deseas ejecutar: ");
            comando = sc.nextLine();
            comando = comando.trim();

            System.out.println("Ejecutando : " + comando);
            if (comando.equalsIgnoreCase("salir")) {
                break;
            }

            ProcessBuilder pb = new ProcessBuilder("sh", "-c", comando);
            pb.inheritIO();
            Process p = pb.start();
            int exitCode = p.waitFor();
            System.out.println( "El comando : "+ comando + " ha finalizado con código " + exitCode);
        }
    }
}
