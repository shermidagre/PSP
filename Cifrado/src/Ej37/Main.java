import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Scanner;

/*
Investiga en qué consistía el cifrado del César y realiza un programa que pida un texto para cifrar utilizando dicho método y que además solicite el número de desplazamiento del mismo. La ejecución del programa es la siguiente:

- El programa pide el texto a cifrar

- Pide el desplazamiento del mismo

- Muestra el texto cifrado
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
            usuario(nombre, texto);
        } catch (NoSuchAlgorithmException e) {
            // Se maneja la excepción en caso de que el algoritmo de hashing no esté disponible.
            System.out.println("Error: " + e.getMessage());
        }

        // Se cierra el objeto Scanner.
        sc.close();
    }

    /**
     * Este método toma el nombre del usuario y un texto, luego cifra el texto usando el algoritmo SHA-256.
     * @param nombre El nombre del usuario.
     * @param texto El texto a cifrar.
     * @throws NoSuchAlgorithmException Si el algoritmo de hashing no está disponible.
     */
    static void usuario(String nombre, String texto) throws NoSuchAlgorithmException {
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
    }
}