import java.util.Scanner;

void main() {

    Scanner sc = new Scanner(System.in);

    String dominioUsuario;
    System.out.println("Escribe tu dominio");
    dominioUsuario = sc.nextLine();
    System.out.println("Tu dominio es: " + dominioUsuario);

    try {
        InetAddress ip = InetAddress.getByName(dominioUsuario);
        System.out.println( "Tu ip es :" + ip.getHostAddress());

        InetAddress local = InetAddress.getLocalHost();
        System.out.println( "Tu ip local es :"+ local.getHostAddress());
    }catch (UnknownHostException e){
        System.out.println("Error: " + e.getMessage());
    }

}
