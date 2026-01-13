import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Scanner;

/*
Acabas de ser contratado para desarrollar el sistema de Login de una nueva App. Tu jefe te recuerda la regla de oro de la ciberseguridad: "Nunca se guardan las contraseñas en texto plano". Debes implementar un sistema seguro basado en Hashing.
 */

public class Main {
    public static void main(String[] args) {
        // Se crea un objeto Scanner para leer la entrada del usuario.
        Scanner sc = new Scanner(System.in);

        // Se solicita al usuario que ingrese su nombre.
        System.out.println("Ingrese su nombre: ");
        String nombre = sc.nextLine();

        // Se solicita al usuario que ingrese el texto a cifrar.
        System.out.println("Ingrese el texto a cifrar: ");
        String texto = sc.nextLine();

        try {
            // Se llama al método usuario para procesar el texto.
            cifrado(nombre, texto);
        } catch (NoSuchAlgorithmException e) {
            // Se maneja la excepción en caso de que el algoritmo de hashing no esté disponible.
            System.out.println("Error: " + e.getMessage());
        }

        // Se cierra el objeto Scanner.
        sc.close();
    }

    /**
     * Fase 1
     * Este método toma el nombre del usuario y un texto, luego cifra el texto usando el algoritmo SHA-256.
     * @param nombre El nombre del usuario.
     * @param texto El texto a cifrar.
     * @throws NoSuchAlgorithmException Si el algoritmo de hashing no está disponible.
     */
    static void cifrado(String nombre, String texto) throws NoSuchAlgorithmException {
        // Se saluda al usuario.
        System.out.println("Hola " + nombre);

        // Se asigna el texto a una nueva variable.
        String cifrado = texto;

        // Se crea una instancia de MessageDigest con el algoritmo SHA-256.
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Se actualiza el MessageDigest con los bytes del texto a cifrar.
        md.update(cifrado.getBytes());

        // Se calcula el hash.
        byte[] resumen = md.digest();

        // Se convierte el hash a formato hexadecimal.
        String hex = HexFormat.of().formatHex(resumen);

        // Se muestra el texto cifrado en formato hexadecimal.
        System.out.println("El texto cifrado es: " + hex);

        System.out.println("--- Fase 2 ---");
        System.out.println("Usuario registrado, inicie sesión para comprobar");
        inicioSesion(nombre, texto);
    }
    static void inicioSesion(String nombre, String texto) throws NoSuchAlgorithmException {

        Scanner sc = new Scanner(System.in);

        System.out.println("Introduzca su nombre: ");
        String nombreLogin = sc.nextLine();
        System.out.println("Ingrese su contraseña: ");
        String contraseñaLogin = sc.nextLine();
        if (nombreLogin.equals(nombre)) {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(contraseñaLogin.getBytes());
            byte[] resumenLogin = md.digest();
            String hexLogin = HexFormat.of().formatHex(resumenLogin);

            MessageDigest mdOriginal = MessageDigest.getInstance("SHA-256");
            mdOriginal.update(texto.getBytes());
            byte[] resumenOriginal = mdOriginal.digest();
            String hexOriginal = HexFormat.of().formatHex(resumenOriginal);

            if (hexLogin.equals(hexOriginal)) {
                System.out.println("ACCESO CONCEDIDO " + nombre );
            } else {
                System.out.println("ERROR: Credenciales inválidas." + nombre );
            }
        } else {
            System.out.println("Nombre de usuario incorrecto. Acceso denegado.");
        }
    }
}