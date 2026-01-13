import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

/**
 * Main class for Ejercicio 39.
 * This program attempts to find a password from a dictionary file
 * by comparing the SHA-256 hash of each word with a target hash.
 */
public class Main {
    /**
     * Main method - entry point of the program.
     * Initializes the path to the dictionary file and the target SHA-256 hash.
     * Calls the lecturaFichero method to search for the matching password.
     * Handles potential IOException and NoSuchAlgorithmException.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Path to the dictionary file and the target hash to find
        String ruta = "C:\\Users\\samue\\Documents\\dam2\\PSP\\Cifrado\\src\\Ej39\\diccionario.txt";
        String texto = "4a630b8e79a0cd2fbae3f58e751abb28d0f4918f76af188d8996f13fabe08af8"; // This is the hash for "contraseña"

        try {
            // Attempt to find the password by reading the file and comparing hashes
            String encontrado = lecturaFichero(ruta, texto);
            if (encontrado != null) {
                System.out.println("Contraseña encontrada: " + encontrado);
            } else {
                System.out.println("No se encontró ninguna coincidencia en " + ruta);
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            // Print error message if an exception occurs
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Reads a dictionary file line by line, computes the SHA-256 hash of each word,
     * and compares it with a given target hash.
     *
     * @param ruta The path to the dictionary file.
     * @param texto The target SHA-256 hash (in hexadecimal format) to find.
     * @return The word from the dictionary that matches the target hash, or null if no match is found.
     * @throws IOException If an I/O error occurs while reading the file.
     * @throws NoSuchAlgorithmException If the SHA-256 algorithm is not available.
     */
    static String lecturaFichero(String ruta, String texto) throws IOException, NoSuchAlgorithmException {
        // Get an instance of the SHA-256 message digest algorithm
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        // Use try-with-resources to ensure the BufferedReader is closed
        try (BufferedReader br = Files.newBufferedReader(Paths.get(ruta))) {
            String linea;
            // Read the file line by line
            while ((linea = br.readLine()) != null) {
                String candidato = linea.trim(); // Get the word and remove leading/trailing whitespace
                if (candidato.isEmpty()) continue; // Skip empty lines

                // Compute the SHA-256 hash of the current word
                byte[] resumen = md.digest(candidato.getBytes());
                // Convert the byte array hash to its hexadecimal string representation
                String hex = HexFormat.of().formatHex(resumen);

                // Compare the computed hash with the target hash (case-insensitive)
                if (hex.equalsIgnoreCase(texto)) {
                    return candidato; // Return the word if hashes match
                }
                md.reset(); // Reset the MessageDigest for the next hash calculation
            }
        }
        return null; // Return null if no matching word is found after checking all lines
    }
}