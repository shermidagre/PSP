package Tarea5;

import java.io.IOException;
import java.util.Scanner;


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

            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", comando);
            pb.inheritIO();
            Process p = pb.start();
            int exitCode = p.waitFor();
            System.out.println( "El comando : "+ comando + " ha finalizado con código " + exitCode);
        }
    }
}