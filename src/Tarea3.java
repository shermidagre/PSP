import java.io.IOException;
import java.util.Scanner;

public class Tarea3 {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        boolean esWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

        System.out.println("En que so estas trabajando? (Windows/Linux)");
        String so = scanner.next();

        if (!so.equalsIgnoreCase("windows") &&!so.equalsIgnoreCase("linux")) {
            System.out.println("El sistema operativo introducido no es valido. Se utilizara Windows por defecto.");
            so = "cmd /C";
        }else {so = "sh -c";  }

        System.out.println("Introduce la ruta donde quieres crear o abrir el archivo");
        String ruta = scanner.next(); // la ruta donde se encuentra el editor de texto gnome-text-editor

        System.out.println("Introduce el nombre del archivo que quieres crear o abrir");
        String archivo = scanner.next(); // lo denominamos como un escaner para que la persona pueda escribir el archivo al qu equiera entrar+

        String comando = so + " " + "\"" + ruta + "\\" + archivo + ".txt\""; // el comando para abrir el archivo

        System.out.println("El comando para abrir el archivo es: " + comando);
        Runtime.getRuntime().exec(comando); // ejecutamos el comando
        scanner.close(); // cerramos el scanner para que no siga escaneando durante la ejecución del programa

    }
}
