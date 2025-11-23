package Random.pruebaexamen2.pruebaEx;

public class Tortuga extends Thread{
    private static final int META = 100;
    private int posicion = 0;
    private Object obj;

    public Tortuga (String nombre,Object obj) {
        super(nombre);
        this.obj = obj;
    }

    private void avanzar() {
        while (posicion < META && !Carrera.carreraTerminada) {
            try {
                Thread.sleep(50);

                int movimiento = determinarMovimiento();
                posicion += movimiento;

                if (posicion > META) {
                    posicion = META;
                }

                System.out.println(getName() + " ha avanzado a la posición " + posicion);

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
        String hiloactual = Thread.currentThread().getName() + " ";
        if (numerorandom <= 20) { // 20% Dormir
            System.out.println(  hiloactual + "se está durmiendo en la posición " + posicion);
            Thread.sleep(150);
            return 0; // No avanza
        } else if (numerorandom <= 50) { // 30% Brincar (Avance rápido)
            System.out.println(hiloactual + " está brincando en la posición " + posicion);
            Thread.sleep(20);
            return 5;
        } else if (numerorandom <= 70) { // 20% Comer (Avance lento)
            System.out.println(hiloactual + " está comiendo en la posición " + posicion);
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
