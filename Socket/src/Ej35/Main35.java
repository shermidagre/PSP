package Ej35;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Random;
import java.util.Scanner;

public class Main35 {


    /**
     * Hace una petición a la API y busca la moneda indicada por el usuario.
     *
     * @param respuestaUsuario texto introducido por el usuario: nombre o símbolo de la criptomoneda.
     *
     * Proceso importante (uso de Gson / JSON):
     * 1) Se obtiene la respuesta HTTP como cadena JSON.
     * 2) Gson convierte la cadena JSON entera a un `JsonObject` genérico con `gson.fromJson(response.body(), JsonObject.class)`.
     *    - Esto nos permite acceder de forma segura a campos concretos sin mapear todo a clases.
     * 3) Se extrae el `JsonArray` llamado "data" con `jsonCompleto.getAsJsonArray("data")`.
     *    - `data` es el array que contiene objetos con la información de cada criptomoneda.
     * 4) Se convierte ese `JsonArray` directamente a un array de la clase `Moneda`:
     *    `Moneda[] listaMonedas = gson.fromJson(arrayData, Moneda[].class);`
     *    - Aquí Gson hace el mapeo automático: los nombres de los atributos de `Moneda`
     *      deben coincidir con las claves del JSON (por ejemplo: `name`, `symbol`, `price_usd`, `percent_change_24h`, `rank`).
     *
     * Nota: se maneja el timeout del cliente HTTP y posibles excepciones de I/O o interrupción.
     */
    public static void hacerPeticion(String respuestaUsuario) {
        // Limpiamos espacios
        String busquedaUsuario = respuestaUsuario.trim();

        // Códigos ANSI para color en consola (solo efecto visual en consolas compatibles)
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_RESET = "\u001B[0m";

        // Endpoint público que devuelve un JSON con un array "data"
        String urlApi = "https://api.coinlore.net/api/tickers/" ;


        // Construcción del cliente HTTP con timeout de conexión de 5 segundos
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();


        // Construcción de la petición GET
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlApi))
                .header("Content-Type", "application/json")
                .GET()
                .build();


        try {
            // Envío sincrónico de la petición y lectura del cuerpo como String
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                Gson gson = new Gson();


                // 1. Leemos el JSON completo como un Objeto genérico
                JsonObject jsonCompleto = gson.fromJson(response.body(), JsonObject.class);

                // 2. Extraemos manualmente el array llamado "data"
                JsonArray arrayData = jsonCompleto.getAsJsonArray("data");

                // 3. Convertimos ese array JSON a tu array de objetos Moneda
                //    Gson mapeará cada objeto JSON a una instancia de Moneda según los nombres de campo.
                Moneda[] listaMonedas = gson.fromJson(arrayData, Moneda[].class);

                boolean encontrada = false;

                // Lógica de búsqueda
                for (Moneda moneda : listaMonedas) {
                    // Comprobamos nombre o símbolo ignorando mayúsculas
                    if (moneda.name.equalsIgnoreCase(busquedaUsuario) || moneda.symbol.equalsIgnoreCase(busquedaUsuario)) {

                        encontrada = true;

                        // Convertir a número solo para calcular el color
                        double variacion = Double.parseDouble(moneda.percent_change_24h);

                        System.out.println("\n--- Información Encontrada ---");
                        System.out.println("Nombre:  " + moneda.name);
                        System.out.println("Símbolo: " + moneda.symbol);
                        System.out.println("Ranking: " + moneda.rank);
                        System.out.println("Precio:  " + moneda.price_usd);

                        // Color según sube o baja
                        if (variacion < 0) {
                            System.out.println(ANSI_RED + "Variación 24h: " + moneda.percent_change_24h + ANSI_RESET); // Ansi reset porque si no luego se queda rojo todo
                        } else {
                            System.out.println(ANSI_GREEN + "Variación 24h: +" + moneda.percent_change_24h + ANSI_RESET); // Ansi reset porque si no luego se queda verde todo
                        }

                        break;
                    }
                }

                if (!encontrada) {
                    System.out.println("Moneda no encontrada.");
                }

            } else {
                System.out.println("Error en la API. Código: " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }

    /**
     * Método principal que pide al usuario el nombre o símbolo y llama a hacerPeticion.
     *
     * @param args argumentos de línea de comandos (no usados)
     */
     static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
         while (true){
             System.out.println("Introduce el nomnre o símbolo de la criptomoneda (o 'salir' para terminar): ");
             String respuesta = scanner.nextLine();
             if (respuesta.equalsIgnoreCase("salir")) {
                 System.out.println("Saliendo del programa.");
                 break;
             }
             hacerPeticion(respuesta);
         }
         scanner.close();
     }
}