import java.awt.font.TextAttribute;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Tarea11 extends Thread{

     private int nhilos;
     private String n;

    public Tarea11 (String nombre ,String n,int nhilos) {
        super(nombre);
        this.n = n;
        this.nhilos = nhilos;
    }

    @Override
    public void run() {

        int it= 0;
        int valornumerico = Integer.parseInt(n);
        String siguientehilo = Integer.toString(valornumerico + 1);
        Thread hilohijo = new Tarea11(getName(), siguientehilo, nhilos);

        if (valornumerico < nhilos) {
            hilohijo.start();
        }

        while (it < 5) {

            long tiempoAleatorio = (long) (Math.random() * 600 + 100); // Rango de 100 a 600 ms
            try {
                Thread.sleep(tiempoAleatorio);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            it++;
            System.out.println("[" + getName() + " "+ n +"]" + "Iteracion " + it);

            }

            System.out.println("[" + getName() + " " + n + "]" + " terminado.");

        }



    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numHilos ;
        boolean entradaValida = false;

        do {
            System.out.print("Elige número de hilos (debe ser un número entero): ");
            if (s.hasNextInt()) {
                numHilos = s.nextInt();
                entradaValida = true;

            } else {
                System.out.println("⚠️");
                System.out.println("Entrada no válida. Se asignará el valor por defecto de 5 hilos.");
                numHilos = 5;
                break;
            }

        } while (!entradaValida);

        if (numHilos >= 1){
            new Tarea11("Hilo", "1", numHilos).start();
        }

        s.close();

    }
}