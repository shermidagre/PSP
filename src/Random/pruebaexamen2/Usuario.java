package Random.pruebaexamen2;

public class Usuario extends Thread{

    private final int maxEntradas = 3;
    public static int entradasCompradas;

    public Usuario() {
        this.entradasCompradas = 0;
    }

    public void run() {
        while (entradasCompradas < maxEntradas) {
            comprarEntrada();
        }
    }
    public static void comprarEntrada() {
        String nombreUsuario = Thread.currentThread().getName();
        System.out.println("Entrada comprada por : " + nombreUsuario);
        entradasCompradas++;
        System.out.println("Total de entradas compradas por " + nombreUsuario + ": " + entradasCompradas);

    }

}
