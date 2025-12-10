package Ej35;

import com.google.gson.Gson;

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

    public static void hacerPeticion(String urlString) {

        // Inicialización para resultados en caso de error
        double[] resultados = {-1, -1, -1, -1, -1};
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";



        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        // 3. Inicio de la medición del tiempo
        long tiempoInicio = System.currentTimeMillis();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // 4. Fin de la medición de tiempo
            long fin = System.currentTimeMillis();
            long tiempoRespuesta = fin - tiempoInicio;

            String cuerpo = response.body();
            int bytes = cuerpo.length();

            System.out.println("Status: " + response.statusCode());

            // 5. Guardar HTML y almacenar resultados si la petición es correcta (200)
            if (response.statusCode() == 200) {
                Gson gson = new Gson();

                // Como el JSON empieza por corchete [ {..} ], es un Array
                Moneda[] listaMonedas = gson.fromJson(cuerpo, Moneda[].class);

                // Verificamos que la lista no esté vacía
                double precio;
                int ranking;
                double percent_change_24h;
                if (listaMonedas.length > 0) {
                    Moneda miMoneda = listaMonedas[0]; // Cogemos la moneda

                    // Convertimos el precio de String a double
                    precio = Double.parseDouble(miMoneda.price_usd);
                    ranking = Integer.parseInt(miMoneda.rank);


                    System.out.println("Moneda: " + miMoneda.name);
                    System.out.println("Símbolo: " + miMoneda.symbol);
                    System.out.println("Precio USD: " + precio);
                    System.out.println("Ranking: " + ranking);

                    if (miMoneda.percent_change_24h.contains("-")) {
                        percent_change_24h = Double.parseDouble(miMoneda.percent_change_24h);
                        System.out.println(ANSI_RED + "Variacion 24h: " + percent_change_24h);
                    } else {
                        percent_change_24h = Double.parseDouble(miMoneda.percent_change_24h);
                        System.out.println(ANSI_GREEN + "Variacion 24h: +" + percent_change_24h);
                    }

                    // Guardar JSON
                    try (FileWriter fw = new FileWriter("inspector.json")) {
                        fw.write(cuerpo);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    resultados[0] = tiempoRespuesta;
                    resultados[1] = bytes;
                    resultados[2] = precio;
                    resultados[3] = ranking;
                    resultados[4] = percent_change_24h;
                }

            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

     static void main(String[] args) {
         do {
             System.out.println("Bienvenido al inspector de monedas bitcoineras.");
             System.out.println("¿Desea consultar una moneda? (s/n): ");
             Scanner scanner = new Scanner(System.in);
             String respuesta = scanner.nextLine();
             if (respuesta.equalsIgnoreCase("s")) {
                 break;
             } else if (respuesta.equalsIgnoreCase("n")) {
                 System.out.println("Saliendo del programa.");
                 return;
             } else {
                 System.out.println("Entrada no válida. Por favor, responda con 's' o 'n'.");
             }
         } while (true);{
             Scanner sc = new Scanner(System.in);
             // Recuerda usar el ID (90 = bitcoin)
             System.out.println("Listado de IDs de monedas:");
             System.out.println("Bitcoin: 90");
             System.out.println("Ethereum: 80");
             System.out.println("Tether: 48543");
             System.out.println("BNB: 2710");
             System.out.println("USD Coin: 3408");
             System.out.println("Escribe el ID de la moneda: ");
             String id = sc.nextLine();
             if (id.isEmpty()) {
                 // Si no se introduce nada, elegimos un ID aleatorio
                 String[] ids = {"90", "80", "48543", "2710", "3408"};
                 Random rand = new Random();
                 id = ids[rand.nextInt(ids.length)];
                 System.out.println("No se introdujo ningún ID. Se ha seleccionado aleatoriamente el ID: " + id);
             } else if (id.equalsIgnoreCase("Bitcoin")) {
                 id = "90";
             } else if (id.equalsIgnoreCase("Ethereum")) {
                 id = "80";
             } else if (id.equalsIgnoreCase("Tether")) {
                 id = "48543";
             } else if (id.equalsIgnoreCase("BNB")) {
                 id = "2710";
             } else if (id.equalsIgnoreCase("USD Coin")) {
                 id = "3408";
             }
             sc.close();

             String url = "https://api.coinlore.net/api/ticker/?id=" + id;

             hacerPeticion(url);
         }
     }
}