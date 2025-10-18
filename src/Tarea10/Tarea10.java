package Tarea10;

public class Tarea10 extends Thread{


    // Definimos lo que hace el hilo
    @Override
    public void run() {
int n= 0;
        while (n < 10) {
            long tiempoAleatorio = (long) (Math.random() * 10000 + 1000); // Rango de 500 a 5000 ms            } catch (InterruptedException e) {
            try {
                Thread.sleep(tiempoAleatorio);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            n++;
            System.out.println("Hilo " + getName() + " valor de nveces: " + n);

        }

    }
    public static void main(String[] args) {

        new Tarea10().start();
        new Tarea10().start();
        new Tarea10().start();
        new Tarea10().start();

    }
}
