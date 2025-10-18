import java.util.Scanner;

public class Tarea19 extends Thread {

    public static int contadora = 0;
    public static int contadore = 0;
    public static int contadori = 0;
    public static int contadoro = 0;
    public static int contadoru = 0;
    public static int contador  = 0 ;
    public static String texto;

    static final Object contadorletras = new Object();

    char vocal;

    public Tarea19(String nombre, char vocal) {
        super(nombre);
        this.vocal = vocal;
    }
    public void run() {
        for (int i = 0; i < texto.length(); i++) {
            char c = Character.toLowerCase(texto.charAt(i));

            if (c == vocal) {

                synchronized (contadorletras) {
                    switch (vocal) {
                        case 'a':
                            contador++;
                            contadora++;
                            break;
                        case 'e':
                            contador++;
                            contadore++;
                            break;
                        case 'i':
                            contador++;
                            contadori++;
                            break;
                        case 'o':
                            contador++;
                            contadoro++;
                            break;
                        case 'u':
                            contador++;
                            contadoru++;
                            break;
                    }
                }
            }
        }
        System.out.println(getName() + " ha terminado.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce un texto:");
        texto = sc.nextLine();

        Tarea19 Hiloa = new Tarea19("Hilo A", 'a');
        Tarea19 Hiloe = new Tarea19("Hilo E", 'e');
        Tarea19 Hiloi = new Tarea19("Hilo I", 'i');
        Tarea19 Hiloo = new Tarea19("Hilo O", 'o');
        Tarea19 Hilou = new Tarea19("Hilo U", 'u');

        Hiloa.start();
        Hiloe.start();
        Hiloi.start();
        Hiloo.start();
        Hilou.start();

        try {
            Hiloa.join();
            Hiloe.join();
            Hiloi.join();
            Hiloo.join();
            Hilou.join();
        } catch (InterruptedException e) {
            System.err.println("Un hilo fue interrumpido: " + e.getMessage());
        }


        System.out.println("Texto: " + texto);
        System.out.println("Vocales totales "+ contador);
        System.out.println("Vocal 'a': " + contadora);
        System.out.println("Vocal 'e': " + contadore);
        System.out.println("Vocal 'i': " + contadori);
        System.out.println("Vocal 'o': " + contadoro);
        System.out.println("Vocal 'u': " + contadoru);
    }
}