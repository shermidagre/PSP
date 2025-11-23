package Ej21;

public class Main {
    public static void main(){
        final int numeroPlazas = 5;
        final int numeroCoches = 7;

        Parking parkingCentral = new Parking(numeroPlazas);

        System.out.println(Colores.PURPURA + "Parking del gran via refactorizado" + Colores.RESET);
        System.out.println("Parking con " + Colores.CIAN + numeroPlazas + Colores.RESET + " plazas disponibles.");
        System.out.println("Lanzando " + Colores.AMARILLO + numeroCoches + Colores.RESET + " coches (hilos).");

        for (int i = 1; i <= numeroCoches; i++) {
            new Coche(parkingCentral, i).start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }

        parkingCentral.mostrarEstadisticas();
    }

}

