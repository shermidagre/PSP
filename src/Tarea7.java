import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Tarea7 {
    public static void main(String[] args) throws IOException, InterruptedException {

            ProcessBuilder pb = new ProcessBuilder("cmd.exe","/c","cd src && dir");
            pb.inheritIO();
            Process p = pb.start();

            int exitCode = p.waitFor();
            System.out.println("el comando ha finalizado con codigo: " + exitCode);

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

