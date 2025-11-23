package Ej21;

import java.util.Arrays;

public class Parking {

    private final int[] estadoPlazas;
    private final int capacidadTotal;

    private int totalEntradas = 0;
    private int totalSalidas = 0;
    private int totalEsperas = 0;

    public Parking(int capacidad) {
        this.capacidadTotal = capacidad;
        this.estadoPlazas = new int[capacidad];
    }

    private int encontrarPlazaDisponible() {
        for (int i = 0; i < estadoPlazas.length; i++) {
            if (estadoPlazas[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    private int buscarCoche(int idCoche) {
        for (int i = 0; i < estadoPlazas.length; i++) {
            if (estadoPlazas[i] == idCoche) {
                return i;
            }
        }
        return -1;
    }

    private String mostrarEstado() {
        long cochesAparcados = Arrays.stream(estadoPlazas).filter(p -> p != 0).count();
        int plazasRestantes = capacidadTotal - (int)cochesAparcados;

        return String.format(
                "%sPlazas Libres: %d%s | %sParking: %s%s",
                Colores.CIAN, plazasRestantes, Colores.RESET,
                Colores.AMARILLO, Arrays.toString(estadoPlazas), Colores.RESET
        );
    }

    public void mostrarEstadisticas() {
        System.out.println(Colores.PURPURA + "\n--- ESTADÍSTICAS FINALES ---");
        System.out.println("Total de Entradas Exitosas: " + totalEntradas);
        System.out.println("Total de Salidas: " + totalSalidas);
        System.out.println("Total de Intentos de Espera: " + totalEsperas);
        System.out.println("--------------------------" + Colores.RESET);
    }

    public synchronized void aparcar(int idCoche) {
        long tiempoInicioEspera = System.currentTimeMillis();
        int indicePlaza = encontrarPlazaDisponible();

        while (indicePlaza == -1) {
            totalEsperas++; // Incrementa el contador de esperas
            System.out.println(Colores.ROJO + "-> ESPERA: Coche " + idCoche + " esperando. Parking lleno." + Colores.RESET);
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            indicePlaza = encontrarPlazaDisponible();
        }

        long tiempoFinEspera = System.currentTimeMillis();
        long tiempoTotalEspera = tiempoFinEspera - tiempoInicioEspera;

        estadoPlazas[indicePlaza] = idCoche;
        totalEntradas++; // Incrementa el contador de entradas

        System.out.println(Colores.VERDE + "\n[ENTRADA] Coche " + idCoche +
                " aparca en " + indicePlaza + "." + Colores.RESET);
        if (tiempoTotalEspera > 10) {
            System.out.println(Colores.AMARILLO + "   (Espera total: " + tiempoTotalEspera + "ms)" + Colores.RESET);
        }
        System.out.println(mostrarEstado());
    }

    public synchronized void abandonarParking(int idCoche) {
        int indicePlaza = buscarCoche(idCoche);

        if (indicePlaza != -1) {

            estadoPlazas[indicePlaza] = 0;
            totalSalidas++;

            System.out.println(Colores.AZUL + "\n[SALIDA] Coche " + idCoche +
                    " abandona plaza " + indicePlaza + "." + Colores.RESET);
            System.out.println(mostrarEstado());

            notifyAll();
        }
    }
}
