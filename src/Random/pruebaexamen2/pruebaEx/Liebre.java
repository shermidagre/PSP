// Archivo: Liebre.java
package Random.pruebaexamen2.pruebaEx;

public class Liebre extends Thread {
    private static final int META = 100;
    private int posicion = 0;

    public Liebre(String nombre) {
        super(nombre);
    }

    // 2. Elimina la palabra 'synchronized' del método avanzar
    private void avanzar() {
        while (posicion < META && !Carrera.carreraTerminada) {
            try {
                // Pequeña pausa para simular el tiempo
                Thread.sleep(50);

                int movimiento = determinarMovimiento();
                posicion += movimiento;

                // Asegurar que la posición no exceda la meta, solo que llegue
                if (posicion > META) {
                    posicion = META;
                }

                System.out.println(getName() + " ha avanzado a la posición " + posicion);

                // *** Lógica de Victoria ***
                if (posicion == META) {
                    // Sincronizar para verificar y declarar al ganador de forma atómica
                    synchronized (Carrera.class) {
                        if (!Carrera.carreraTerminada) {
                            Carrera.carreraTerminada = true;
                            System.out.println("\n ¡EL GANADOR ES: " + getName() + "!");
                        }
                    }
                    // Una vez que llega, sale del bucle
                    break;
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private int determinarMovimiento() throws InterruptedException {
        int numerorandom = (int) (Math.random() * 100) + 1;

        if (numerorandom <= 20) { // 20% Dormir
            System.out.println("La liebre se está durmiendo en la posición " + posicion);
            Thread.sleep(150); // Simula el tiempo que pierde
            return 0; // No avanza
        } else if (numerorandom <= 50) { // 30% Brincar (Avance rápido)
            System.out.println("La liebre está brincando en la posición " + posicion);
            Thread.sleep(20);
            return 5;
        } else if (numerorandom <= 70) { // 20% Comer (Avance lento)
            System.out.println("La liebre está comiendo en la posición " + posicion);
            Thread.sleep(100);
            return 1;
        } else { // 30% Caminar/Correr
            return 2;
        }
    }

    public void run() {
        System.out.println("Hilo actual: " + getName());
        avanzar();
    }
}