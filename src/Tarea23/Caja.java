package Tarea23;

import java.util.concurrent.atomic.AtomicLong;
// docu https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicLong.html

public class Caja {
    private static final AtomicLong importeTotal = new AtomicLong(0);
    // AtomicLong para manejar el importe total de manera segura en un entorno multihilo

    public static void agregarPago(int pago) {
       // info de la docu : Atomically adds the given value to the current value.
        importeTotal.addAndGet(pago);
    }

    public static long obtenerImporteTotal() {
        return importeTotal.get();
    }
}
