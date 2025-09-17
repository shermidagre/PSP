import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class Tarea3 {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);


        System.out.println("En que so estas trabajando? (windows/linux)");
        String so = scanner.next().toLowerCase(); // ponga como lo ponga que lo pase todo a minusculas
        scanner.nextLine(); // Consumir el resto de la línea

        String[] comando;
        String ruta = "";
        String editadortextos = "";


        if (so.equalsIgnoreCase("windows")) {
            System.out.println("Introduce la ruta del archivo (ej: C:\\Users\\Usuario\\Escritorio)");
            System.out.println("Cuidado, no funciona con rutas relativas porfavor, siga el ejemplo y ponga su ruta absoluta");
            System.out.println("¿No sabes la ruta absoluta?(si es asi escribe un no)");
            String respuestaRuta = scanner.nextLine().toLowerCase(); //
            if (respuestaRuta.equalsIgnoreCase("no")) {
                String rutausuario = System.getProperty("user.dir");
                System.out.println("Tu ruta actual es: " + rutausuario);
                System.out.println("Ahora, introduce la ruta completa para asi poder guardar el archivo:");
            }
            else {
                ruta = respuestaRuta;
            }
            System.out.println("en caso de que desees utilizar un editor de texto diferente a notepad, introduce su nombre, en caso de que no escribe no");
            editadortextos = scanner.nextLine().toLowerCase();
            if (editadortextos.equalsIgnoreCase("no")) {
                System.out.println("Introduce el nombre del archivo que quieres crear o abrir");
                String archivo = scanner.next();
                String rutacompleta = ruta + "\\" + archivo + ".txt";
                comando = new String[]{"cmd", "/C", "start", editadortextos, rutacompleta};
            }else {
                System.out.println("Introduce el nombre del archivo que quieres crear o abrir");
                String archivo = scanner.next();
                String rutacompleta = ruta + "\\" + archivo + ".txt";
                comando = new String[]{"cmd", "/C", "start", "notepad", rutacompleta};
            }
        }

        else if (so.equalsIgnoreCase("linux")) {
            System.out.println("Introduce la ruta del archivo (ej: /home/Usuario/Documentos)");
            System.out.println("Cuidado, no funciona con rutas relativas porfavor, siga el ejemplo y ponga su ruta absoluta");
            System.out.println("¿No sabes la ruta absoluta?(si es asi escribe un no)");
            String respuestaRuta = scanner.nextLine();
            if (respuestaRuta.equalsIgnoreCase("no")) {
                String rutausuario = System.getProperty("user.dir");
                System.out.println("Tu ruta actual es: " + rutausuario);
                System.out.println("Ahora, introduce la ruta completa para guardar el archivo:");
                ruta = scanner.nextLine();
            } else {
                ruta = respuestaRuta;
            }

            System.out.println("en caso de que desees utilizar un editor de texto diferente a genome, introduce su nombre, en caso de que no escribe no");
            editadortextos = scanner.nextLine().toLowerCase();
            if (editadortextos.equalsIgnoreCase("no")) {
                System.out.println("Introduce el nombre del archivo que quieres crear o abrir");
                String archivo = scanner.next();
                String rutacompleta = ruta + "/" + archivo + ".txt";
                comando = new String[]{"sh", "-c", editadortextos + rutacompleta};
            } else {

            System.out.println("Introduce el nombre del archivo que quieres crear o abrir");
            String archivo = scanner.next();
            String rutacompleta = ruta + "/" + archivo + ".txt";
            comando = new String[]{"sh", "-c", "gnome-text-editor " + rutacompleta};
        }
    }
        else {
            System.out.println("El sistema operativo introducido no es valido.");
            scanner.close();
            return;
        }



        System.out.println("terminado");
        System.out.println("mensaje de depuracion" + Arrays.toString(comando));
        Runtime.getRuntime().exec(comando);
        scanner.close(); //

    }

}


