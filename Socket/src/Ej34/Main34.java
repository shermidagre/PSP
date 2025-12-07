package Ej34;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Scanner;

public class Main34 {

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

        long tiempoInicio = System.currentTimeMillis();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Medicion de tiempo
            long fin = System.currentTimeMillis();
            long tiempoRespuesta = fin - tiempoInicio;

            int statusCode = response.statusCode();
            System.out.println("Petición a: " + urlString);
            System.out.println("Código de estado: " + statusCode);

            System.out.println("Tiempo de respuesta: " + tiempoRespuesta + " ms");
            String contentType = response.headers().firstValue("Content-Type").orElse("No disponible");
            System.out.println("Tipo de contenido: " + contentType);

            String cuerpo = response.body();
            int bytes = cuerpo.length();
            System.out.println("Tamaño del cuerpo: " + bytes);

            // Si es correcta (200) se guarda en el html
            if (statusCode == 200) {
                try (FileWriter fw = new FileWriter("inspector.html")) {
                    fw.write(cuerpo);
                    System.out.println("guardado");
                }

                // Almacenar el tiempo y el tamaño si conectase
                resultados[0] = tiempoRespuesta;
                resultados[1] = bytes;

            } else {
                System.out.println("la peticion no fue correcta");
            }

        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
        return resultados;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe la primera URL (ej: https://www.google.com):");
        String url1 = sc.nextLine();
        System.out.println("Escribe la segunda URL (ej: https://www.bing.com):");
        String url2 = sc.nextLine();

        long[] res1 = hacerPeticion(url1);
        long[] res2 = hacerPeticion(url2);

        boolean exito1 = res1[0] != -1;
        boolean exito2 = res2[0] != -1;

        if (exito1 && exito2) {

            long tiempo1 = res1[0];
            long tiempo2 = res2[0];
            long tamaño1 = res1[1];
            long tamaño2 = res2[1];

            // La web mas rapida
            if (tiempo1 < tiempo2) {
                System.out.println("La web más rápida ha sido: " + url1 + "con" + tiempo1 + "ms");
            } else if (tiempo2 < tiempo1) {
                System.out.println("La web más rápida ha sido: " + url2 + "con" + tiempo2 + " ms.");
            }

            // La web con mas contenido
            if (tamaño1 > tamaño2) {
                System.out.println("La web con más contenido ha sido: " + url1 + "con" + tamaño1 + " caracteres.");
            } else if(tamaño2 > tamaño1) {
                System.out.println("La web con más contenido ha sido: " + url2 + "con" + tamaño2 + " caracteres.");
            }
        } else {
            System.out.println("No se pudo realizar la comparación completa. Una o ambas peticiones fallaron (código no 200).");
            if (!exito1) System.out.println("Fallo en: " + url1);
            if (!exito2) System.out.println("Fallo en: " + url2);
        }
        sc.close();
    }
}

