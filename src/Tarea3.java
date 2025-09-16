import java.io.IOException;
import java.util.Scanner;

public class Tarea3 {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        boolean esWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

        String ruta = ""; // la ruta donde se encuentra el editor de texto gnome-text-editor
        String archivo = scanner.nextline(); // lo denominamos como un escaner para que la persona pueda escribir el archivo al qu equiera entrar+
        String [] pbloc = {ruta,"gnome-text-editor",archivo};
        Runtime.getRuntime().exec(pbloc); // esto abre el editor de texto

        if (esWindows){
            ruta = "cmd /C";
            System.out.println("Escribe tu archivo aqui");
        }
        else {
            ruta = "sh -c";
            System.out.println("Escribe tu archivo aqui");
        }

    }
}
