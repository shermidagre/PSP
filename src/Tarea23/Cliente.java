package Tarea23;

public class Cliente extends Thread{
    private final int idCliente;
    private final SuperMercado supermercado;

    public Cliente(int idCliente, SuperMercado supermercado) {
        this.idCliente = idCliente;
        this.supermercado = supermercado;
    }

    @Override
    public void run() {
        simularTiempoCompra();

        try {
            int importePago = (int) (Math.random() * 1590) + 10;
            // por poner algo de rango entre 10 y 1600
            supermercado.atenderCliente(idCliente, importePago);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void simularTiempoCompra() {
        int tiempoEsperaMs = (int) (Math.random() * 100) + 1000;
        System.out.println("Tiempo de compra del cliente " + idCliente + ": " + tiempoEsperaMs + " ms.");
        try {
            Thread.sleep(tiempoEsperaMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
