package Ej35;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Random;
import java.util.Scanner;

public class Main35 {

    public static double[] hacerPeticion(String urlString) {

        // Inicialización para resultados en caso de error
        double[] resultados = {-1, -1, -1, -1, -1};


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
            // Envío síncrono de la petición y manejo del cuerpo como String
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
                double precio = 0.0;
                int ranking = 0;
                double percent_change_24h = 0.0;
                if (listaMonedas.length > 0) {
                    Moneda miMoneda = listaMonedas[0]; // Cogemos la moneda

                    // Convertimos el precio de String a double
                    precio = Double.parseDouble(miMoneda.price_usd);
                    ranking = Integer.parseInt(miMoneda.rank);
                    percent_change_24h = Double.parseDouble(miMoneda.percent_change_24h);


                    System.out.println("Moneda: " + miMoneda.name);
                    System.out.println("Símbolo: " + miMoneda.symbol);
                    System.out.println("Precio USD: " + precio);
                    System.out.println("Ranking: " + ranking);
                    System.out.println("Variacion 24h: " + percent_change_24h);
                }

                // Guardar JSON
                try (FileWriter fw = new FileWriter("inspector.json")) {
                    fw.write(cuerpo);
                }

                resultados[0] = tiempoRespuesta;
                resultados[1] = bytes;
                resultados[2] = precio;
                resultados[3] = ranking;
                resultados[4] = percent_change_24h;
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return resultados;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Recuerda usar el ID (90 = bitcoin)
        System.out.println("Escribe el ID de la moneda (ej: 90): ");
        String id = sc.nextLine();
        sc.close();

        String url = "https://api.coinlore.net/api/ticker/?id=" + id;

        hacerPeticion(url);
    }
}