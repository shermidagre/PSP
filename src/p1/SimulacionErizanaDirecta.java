package p1;

import java.util.Random;

public class SimulacionErizanaDirecta {

    // RECURSO COMPARTIDO Y CANDADO
    private static volatile int totalEspectadores = 0;
    private static final Object lock = new Object();

    // TAREA (RUNNABLE)
    private static class TornoRunnable implements Runnable {

        private final int espectadoresASumar = 1000;

        @Override
        public void run() {
            String nombreTorno = Thread.currentThread().getName();
            System.out.println(nombreTorno + " abierto. Preparando la entrada de 1000 personas.");

            for (int i = 0; i < espectadoresASumar; i++) {
                try {
                    int demora = new Random().nextInt(41) + 10;
                    Thread.sleep(demora);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            // Bloque sincronizado
            synchronized (lock) {
                totalEspectadores += espectadoresASumar;
            }

            System.out.println(nombreTorno + " cerrado. Finalizó la entrada de 1000 personas.");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        // 1. Crear la Tarea UNA SOLA VEZ
        TornoRunnable tareaTorno = new TornoRunnable();

        // 2. Crear los 4 Hilos (Directamente sin For)
        Thread torno1 = new Thread(tareaTorno, "Torno-1");
        Thread torno2 = new Thread(tareaTorno, "Torno-2");
        Thread torno3 = new Thread(tareaTorno, "Torno-3");
        Thread torno4 = new Thread(tareaTorno, "Torno-4");

        System.out.println("--- Se abren las puertas del estadio del Erizana (4 tornos activos) ---\n");

        // 3. Lanzar los 4 Hilos (Start)
        torno1.start();
        torno2.start();
        torno3.start();
        torno4.start();

        // 4. Esperar a que TODOS terminen (Join)
        // El hilo principal espera secuencialmente por cada uno de los 4.
        torno1.join();
        torno2.join();
        torno3.join();
        torno4.join();

        // 5. Imprimir el resultado final
        System.out.println("\n--- TORNOS CERRADOS ---");
        System.out.println("TOTAL ASISTENCIA REGISTRADA: " + totalEspectadores);
    }
}