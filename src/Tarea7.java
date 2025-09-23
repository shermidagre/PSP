import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Tarea7 {
    public static void main(String[] args) throws IOException, InterruptedException {

            ProcessBuilder pb = new ProcessBuilder("sh", "-c","cd src", "cd Tarea7.1");
            pb.inheritIO();
            Process p = pb.start();

            int exitCode = p.waitFor();
            System.out.println("el comando ls ha finalizado con código: " + exitCode);
            OutputStream os = pb.start().getOutputStream();
            OutputStreamWriter wr = new OutputStreamWriter(os);
            wr.write("Este es el contenido del archivo src/Tarea7.1");
            System.out.println(wr);
            wr.close();
        }
    }

