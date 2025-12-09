import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

void main(){

    Scanner sc = new Scanner(System.in);

    System.out.println("Escribe la url que quieres comprobar");
    //String url = sc.nextLine();


    HttpClient cliente = HttpClient.newHttpClient();

    HttpRequest peticion = HttpRequest.newBuilder()
            .uri(URI.create("https://www.google.com"))
            .GET()
            .build();

    try{
        HttpResponse<String> respuesta = cliente.send(peticion,HttpResponse.BodyHandlers.ofString());

        System.out.println("Codigo " + respuesta.headers());
        System.out.println("Cuerpo " + respuesta.body());

        if (respuesta.statusCode() = "200"){
            respuesta.body().s
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
}
