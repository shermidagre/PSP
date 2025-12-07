package Ej34;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Scanner;

/**
 * ## Clase Main34: Inspector de Peticiones HTTP
 * * Esta clase realiza peticiones HTTP a dos URLs proporcionadas por el usuario,
 * mide el tiempo de respuesta y el tamaño del cuerpo de la respuesta.
 * * Finalmente, compara los resultados para determinar qué web fue la más rápida
 * y cuál tuvo más contenido. Si la petición es exitosa (código 200),
 * guarda el cuerpo de la respuesta en un archivo llamado "inspector.html".
 */
public class Main34 {

    /**
     * Realiza una petición GET síncrona a la URL especificada, mide el tiempo de respuesta
     * y el tamaño del cuerpo.
     * * Si el código de estado es 200, guarda el cuerpo de la respuesta en "inspector.html"
     * y devuelve los resultados. En caso de error o código de estado diferente de 200,
     * se devuelve un array de error.
     * * @param urlString La URL a la que se va a realizar la petición.
     * @return Un array de tipo long[2] donde:
     * <ul>
     * <li>[0] = Tiempo de respuesta en milisegundos (o -1 si falló).</li>
     * <li>[1] = Tamaño del cuerpo de la respuesta en caracteres (o -1 si falló).</li>
     * </ul>
     */
    public static long[] hacerPeticion(String urlString) {

        // Inicialización para resultados en caso de error: [-1, -1]
        long[] resultados = {-1, -1};


        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .GET()
                .build();

        // 3. Inicio de la medición del tiempo
        long tiempoInicio = System.currentTimeMillis();

        try {
            // Envío síncrono de la petición y manejo del cuerpo como String
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 4. Fin de la medición de tiempo
            long fin = System.currentTimeMillis();
            long tiempoRespuesta = fin - tiempoInicio;

            int statusCode = response.statusCode();
            System.out.println(" Petición a: " + urlString);
            System.out.println("Código de estado: " + statusCode);

            System.out.println("Tiempo de respuesta: " + tiempoRespuesta + " ms");
            String contentType = response.headers().firstValue("Content-Type").orElse("No disponible");
            System.out.println("Tipo de contenido: " + contentType);

            String cuerpo = response.body();
            int bytes = cuerpo.length();
            System.out.println("Tamaño del cuerpo: " + bytes);

            // 5. Guardar HTML y almacenar resultados si la petición es correcta (200)
            if (statusCode == 200) {
                try (FileWriter fw = new FileWriter("inspector.html")) {
                    fw.write(cuerpo);
                    System.out.println("guardado en inspector.html");
                }

                // Almacenar el tiempo y el tamaño en el array de resultados
                resultados[0] = tiempoRespuesta;
                resultados[1] = bytes;

            } else {
                System.out.println("La petición no fue correcta (código != 200).");
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Error de I/O o interrupción al hacer la petición: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("URL mal formada o inválida: " + e.getMessage());
        }
        return resultados;
    }

    /**
     * Punto de entrada principal de la aplicación.
     * Solicita dos URLs al usuario, realiza peticiones a ambas y compara los resultados
     * para imprimir la web más rápida y la de mayor contenido.
     * * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe la primera URL (ej: https://www.google.com):");
        String url1 = sc.nextLine();
        System.out.println("Escribe la segunda URL (ej: https://www.bing.com):");
        String url2 = sc.nextLine();
        sc.close();

        // Realiza las peticiones y obtiene los resultados tiempo, tamaño
        long[] res1 = hacerPeticion(url1);
        long[] res2 = hacerPeticion(url2);

        // Verifica si ambas peticiones fueron enviadas (tiempo != -1)
        boolean exito1 = res1[0] != -1;
        boolean exito2 = res2[0] != -1;

        System.out.println("\n resultados de la comparacion ");

        if (exito1 && exito2) {

            long tiempo1 = res1[0];
            long tiempo2 = res2[0];
            long tamaño1 = res1[1];
            long tamaño2 = res2[1];

            // Comparación de velocidad
            if (tiempo1 < tiempo2) {
                System.out.println("La web más rápida ha sido: " + url1 + " con " + tiempo1 + " ms.");
            } else if (tiempo2 < tiempo1) {
                System.out.println("La web más rápida ha sido: " + url2 + " con " + tiempo2 + " ms.");
            }

            // Comparación de contenido
            if (tamaño1 > tamaño2) {
                System.out.println("La web con más contenido ha sido: " + url1 + "** con " + tamaño1 + " caracteres.");
            } else if(tamaño2 > tamaño1) {
                System.out.println("La web con más contenido ha sido: " + url2 + " con " + tamaño2 + " caracteres.");
            }
        } else {
            System.out.println("No se pudo realizar la comparación completa porque una o ambas peticiones fallaron (código no 200).");
            if (!exito1) System.out.println("Fallo en: " + url1);
            if (!exito2) System.out.println("Fallo en: " + url2);
        }
    }
}