package Random.pruebaexamen2.pruebaEx;

public class Carrera {
    public static boolean carreraTerminada;

    void main() {
        Object obj = new Object();
        Tortuga t1 = new Tortuga("Tortuga ", obj);
        Liebre l1 = new Liebre("Liebre ");

        l1.start();
        t1.start();

        try {
            l1.join();
            t1.join();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
