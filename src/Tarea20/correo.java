package Tarea20;

public class correo {
    private String mensaje = null; // El buzón está vacío inicialmente.


    public synchronized void escribir(String nuevoMensaje) {

        while (mensaje != null) {
            try {
                System.out.println(Thread.currentThread().getName() + " espera: Buzón lleno.");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        this.mensaje = nuevoMensaje;
        // LLamada a el hilo actual  para notificar a todos los hilos en espera (especialmente a los escritores).
        System.out.println(Thread.currentThread().getName() + " ha escrito: \"" + nuevoMensaje + "\"");
        notifyAll();
    }

    /**
     * Lee el mensaje del buzón. Solo puede leer si hay un mensaje.
     */
    public synchronized String leer() {
        // Si el buzón está vacío (mensaje == null), el lector espera.
        while (mensaje == null) {
            try {
                System.out.println(Thread.currentThread().getName() + " espera: Buzón vacío.");
                wait(); // Espera a que el escritor ponga un mensaje.
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        String mensajeLeido = this.mensaje;
        this.mensaje = null; // Vaciamos el buzón.
        System.out.println(Thread.currentThread().getName() + " ha leído: \"" + mensajeLeido + "\"");

        // Notifica a todos los hilos en espera (especialmente a los escritores).
        notifyAll();

        return mensajeLeido;
    }
}