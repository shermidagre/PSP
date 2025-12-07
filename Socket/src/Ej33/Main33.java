package Ej33;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main33 {

    public static void hacerPeticion(String urlString) {

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            System.out.println("Código de estado: " + statusCode);

            String contentType = response.headers().firstValue("Content-Type").orElse("No disponible");
            System.out.println("Tipo de contenido: " + contentType);

            // Si es correcta (200), guardar el HTML
            if (statusCode == 200) {
                try (FileWriter fw = new FileWriter("inspector.html")) {
                    fw.write(response.body());
                    System.out.println("guardado");
                }
            } else {
                System.out.println("La petición no fue correcta (código de estado no es 200).");
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Error al realizar la petición o al escribir el archivo: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("URL inválida: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe una url (ej :https://www.google.com)");
        String url = sc.nextLine();

        hacerPeticion(url);
    }
}
