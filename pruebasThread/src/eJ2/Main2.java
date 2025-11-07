import eJ2.TareaContador;

public static void main(String[] args) {
    TareaContador tarea = new TareaContador();

    Thread hilo1 = new Thread(tarea,"h1");
    Thread hilo2 = new Thread(tarea,"h2");
    Thread hilo3 = new Thread(tarea,"h3");

    try {
        // Secuencia 1: Hilo A
        hilo1.start(); // El hilo A comienza a correr
        hilo1.join();  // El hilo 'main' se bloquea y ESPERA a que Hilo-A termine.

        // Secuencia 2: Hilo B (Solo inicia cuando Hilo-A ha liberado a 'main')
        hilo2.start(); // El hilo B comienza a correr
        hilo2.join();  // El hilo 'main' se bloquea y ESPERA a que Hilo-B termine.

        // Secuencia 3: Hilo C (Solo inicia cuando Hilo-B ha liberado a 'main')
        hilo3.start(); // El hilo C comienza a correr
        hilo3.join();  // El hilo 'main' se bloquea y ESPERA a que Hilo-C termine.

    } catch (InterruptedException e) {
        System.err.println("La secuencia fue interrumpida.");
        // Restaurar el estado de interrupción
        Thread.currentThread().interrupt();
    }

    System.out.println("\n*** El hilo principal finaliza después de que todos terminaron. ***");
}

