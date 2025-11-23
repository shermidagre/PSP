package Tarea23;

import java.util.concurrent.Semaphore;
// docu https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Semaphore.html
// esta bomba pero se me esta haciendo un poco lioso entenderlo del todo
public class SuperMercado {
    private final Semaphore cajasDisponibles;
    private final int numeroCajas;

    public SuperMercado(int numeroCajas) {
        this.numeroCajas = numeroCajas;
        this.cajasDisponibles = new Semaphore(numeroCajas);
    }

    public void atenderCliente(int idCliente, int importePago) throws InterruptedException {

        // El cliente espera a que haya una caja disponible (Aun estoy entendiendo como se usa, cogi refe de dani)
        cajasDisponibles.acquire();

        int cajaElegida = (int) (Math.random() * numeroCajas) + 1;

        try {
            System.out.println("Cliente " + idCliente + "en la caja: " + cajaElegida + ".");
            simularTiempoAtencion();
            Caja.agregarPago(importePago);
            System.out.println("Cliente " + idCliente + "ha pagado : " + importePago + " y sale de la Caja " + cajaElegida + ".");

            if (importePago >= 1000) {
                System.out.println("Cliente " + idCliente + " ha realizado un pago superior a 1000. Recibe un cupón de descuento para su próxima compra y una play 5.");
            }
            else if (importePago >= 500) {
                System.out.println("Cliente " + idCliente + " ha realizado un pago superior a 500. Recibe un cupón de descuento para su próxima compra y un jamon gratis.");
            }
            else if (importePago >= 100) {
                System.out.println("Cliente " + idCliente + " ha realizado un pago superior a 100. Recibe un cupón de descuento para su próxima compra.");
            }
            else if (importePago < 100) {
                System.out.println("Cliente " + idCliente + " no ha realizado un pago superior a 100. No recibe ningún cupón de descuento.");
                System.out.println("Eres un tieso de cuidado");
            }

        } finally {
            // La caja se libera
            cajasDisponibles.release();
        }
    }

    // Simula el tiempo de atención en la caja con un simple random de entre 500 y 2000 ms
    private void simularTiempoAtencion() {
        int tiempoAtencionMs = (int) (Math.random() * 1501) + 500;
        try {
            Thread.sleep(tiempoAtencionMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
