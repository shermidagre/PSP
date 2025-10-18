/*
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;


public class Tarea7 {
    public static void main(String[] args) throws IOException, InterruptedException {

        /* El prrimer proceso lo hice para ver en que directorio estaba trabajando y ver los archivos que tenia
           y porque pensaba que lo podia utiliza para ejecutar el segundo proceso, pero no fue asi. F so queda echo



ProcessBuilder pb = new ProcessBuilder("cmd.exe","/c","cd src && dir");
        pb.inheritIO();
Process p = pb.start();

int exitCode = p.waitFor();
        System.out.println("el comando ha finalizado con codigo: " + exitCode);

/* El segundo proceso es el que ejecuta el script de python, y lo hace perfectamente, pero no he conseguido
   redirigir la salida del script a un fichero de texto, lo he intentado de varias formas pero no he conseguido
   que funcione, por lo que al final lo que hace es imprimir en pantalla el resultado del script.

ProcessBuilder pbw = new ProcessBuilder("python", "src/Tarea7.1.py");
        pbw.inheritIO();
Process p2 = pbw.start();
InputStream is = p2.getInputStream();

String contenidopy = new String(p2.getInputStream().readAllBytes());

        System.out.println("Contenido de Tarea7.1.py "+contenidopy);

int exitCodew = p2.waitFor();
        System.out.println("el comando ha finalizado con código: " + exitCodew);

        is.close();

    }
            }

 */
