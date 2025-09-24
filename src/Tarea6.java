import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Tarea6 {
    public static void main(String[] args) throws IOException, InterruptedException {

      /*
       Interfaz nivel1 = new Interfaz();
              nivel1.iniciarInterfaz();
       */

       Interfaz nivel2 = new Interfaz();
       nivel2.iniciarInterfaz();

       }

       static class Interfaz {
        public void iniciarInterfaz() throws IOException, InterruptedException {
            Scanner scanner = new Scanner(System.in);
            //Lanzador lanzador = new Lanzador();
            Lanzador2 lanzador = new Lanzador2();
            while (true) {
                System.out.println("Introduce el host o IP (o salir para terminar): ");
                String ip = scanner.nextLine();
                //ip = www.google.com , utilizarla la ip que tengas para probar
                if (ip.equalsIgnoreCase("salir")) {
                    break;
                }
                System.out.println("ejecutando comando ping para el host o ip : " + ip);
                lanzador.lanzar2(ip);
                //lanzador.lanzar(ip);
                }
            scanner.close();
            }

        }

        static class Lanzador{
            public void lanzar( String ip) throws IOException, InterruptedException {
                ProcessBuilder pb = new ProcessBuilder("ping","-c","4",ip); // Hay que darle un numero de paquetes a enviar para que funcione bien
                pb.inheritIO();
                Process p = pb.start();

                int exitCode =p.waitFor();

                System.out.println("La ip : "+ ip +"se ha ejecutado: "+ exitCode);

            }

    }

    static class Lanzador2{
        public void lanzar2( String ip) throws IOException, InterruptedException {
            ProcessBuilder pb = new ProcessBuilder("ping","-c","4",ip); // Hay que darle un numero de paquetes a enviar para que funcione bien
            Process p = pb.start();
            int exitCode =p.waitFor();

            // Para leer la salida del comando y asi poder concatenar un string en fucnion del resultado que aslga
            String linea;
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader br2 = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            if (exitCode == 0) {

                System.out.println(br.readLine());

                while ((linea = br.readLine()) != null) {
                    System.out.println("[OK]" + linea);
                }
                System.out.println("Codigo de salida : " +exitCode);

            }else {
                    while ((linea = br2.readLine()) != null) {
                        System.out.println("[EROR]" + linea);
                    }
                System.out.println("Codigo de salida : " +exitCode);
            }
        }

    }
}
