import java.awt.font.TextAttribute;

import static java.lang.Integer.parseInt;

public class Tarea11 extends Thread{


    private String n;

    public Tarea11 (String nombre ,String n) {
        super(nombre);
        this.n = n;
    }
    // Definimos lo que hace el hilo
    @Override
    public void run() {
        int it= 0;
        int valornumerico = Integer.parseInt(n);
        Thread hilohijo = new Tarea11(getName(), Integer.toString(parseInt(n)+1));

        if (valornumerico < 5) {
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

        new Tarea11("Hilo","1").start();

    }
}