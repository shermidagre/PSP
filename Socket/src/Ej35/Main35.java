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

    public static void hacerPeticion(String respuestaUsuario) {
        // Limpiamos espacios
        String busquedaUsuario = respuestaUsuario.trim();

        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_RESET = "\u001B[0m";
        String urlApi = "https://api.coinlore.net/api/tickers/" ;


        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlApi))
                .header("Content-Type", "application/json")
                .GET()
                .build();


        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                Gson gson = new Gson();


                // 1. Leemos el JSON completo como un Objeto genérico
                JsonObject jsonCompleto = gson.fromJson(response.body(), JsonObject.class);

                // 2. Extraemos manualmente el array llamado "data"
                JsonArray arrayData = jsonCompleto.getAsJsonArray("data");

                // 3. Convertimos ese array JSON a tu array de objetos Moneda
                Moneda[] listaMonedas = gson.fromJson(arrayData, Moneda[].class);

                // ------------------------------------------------

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