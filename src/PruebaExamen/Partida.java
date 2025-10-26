package PruebaExamen;

public class Partida extends Thread {

    private static int contador = 0;
    private static int random = 0;
    public Partida(String nombre) {
        super(nombre);
    }

    @Override
    public void run() {
        System.out.println("La partida ha comenzado.");
        try {
            Thread.sleep((long) (Math.random() * 5000) + 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        while (contador < 70) {
            avanzar();
            obstaculos();

        }
        try {
            ganador();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void obstaculos () {
        random = (int) (Math.random() * 100) + 1;
        if (random <= 3) {
            try {
                System.out.println(getName() + " ha encontrado un obstáculo y se detiene por 3 segundos.");
                System.out.println(contador);
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        else if (random > 3 && random <= 6) {
            try {
                System.out.println(getName() + " ha encontrado un obstáculo y se detiene por 2 segundos, y retrocede 3 casillas"  );
                contador -= 3;
                System.out.println(contador);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        else if (random > 6 && random <= 8) {
            try {
                System.out.println(getName() + " ha encontrado un obstáculo y se detiene por 1 segundo.");
                System.out.println(contador);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        else {
            System.out.println(getName() + " no ha encontrado ningún obstáculo y continúa.");
        }
    }

    public static synchronized void ganador() throws InterruptedException {
        System.out.println("La partida ha alcanzado el contador de 70.");
        System.out.println("Ganador : " + currentThread());
        Thread.sleep(1000);
        System.exit(0);
    }

    public static synchronized void avanzar() {
        contador++;

    }
    public static void main(String[] args) {
        Partida tortuga = new Partida("Tortuga");
        Partida liebre = new Partida("Liebre");

        tortuga.start();
        liebre.start();

        try {
            tortuga.join();
            liebre.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
