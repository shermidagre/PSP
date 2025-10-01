import java.util.Scanner;

public class Tarea11 extends Thread{

     private int nhilos;
     private int n;
     private int itusuario;
    public Tarea11 (String nombre ,int n,int nhilos, int itusuario) {
        super(nombre);
        this.n = n;
        this.nhilos = nhilos;
        this.itusuario = itusuario;
    }

    @Override
    public void run() {
        final long intervalomensaje = 1000;
        int it= 0;
        int v = n;
        int siguientehilo = v + 1;
        Thread hilohijo = new Tarea11(getName(), siguientehilo, nhilos,itusuario);

        long inicio = System.currentTimeMillis();
        long tiempoUltimoMensaje = System.currentTimeMillis();

        if (this.n == 1) {
            System.out.println("[" + "Control central" + "]" + " Vigilando a : "+ getName() + " " + n );
        }

        if (v < nhilos) {
            hilohijo.start();
        }

        while (it < itusuario) {

            long tiempoAleatorio = (long) (Math.random() * 500 + 100); // Rango de 100 a 600 ms
            try {
                Thread.sleep(tiempoAleatorio);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            it++;
            System.out.println("[" + getName() + " "+ n +"]" + " Iteracion " + it);

            if (System.currentTimeMillis() > tiempoUltimoMensaje + intervalomensaje) {

                if (this.n == 1) {
                    long segundos = intervalomensaje /1000;
                    System.out.println("[Control central] VIGILANCIA. Ha pasado " + segundos + " segundo.");
                }

                tiempoUltimoMensaje = System.currentTimeMillis();
            }

            }
        try {
            hilohijo.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long fin = System.currentTimeMillis();
            long duracion = fin - inicio;

        if (this.n == 1 && it == itusuario) {
            System.out.println("---------------------------------");
            System.out.println("[" + getName() + " " + n + "] " + "Ha terminado " + "\nTiempo total de la caida: "+  duracion + " ms");
            System.out.println("---------------------------------");
        }

        if (this.n != 1 ) {
            System.out.println("[" + getName() + " " + n + "] " + "Ha terminado " );

        }

        }



    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numHilos ;
        int itusuario ;
        boolean entradaValida ;

        do {
            System.out.print("Elige número de hilos (debe ser un número entero): ");
            if (s.hasNextInt()) {
                numHilos = s.nextInt();
                if (numHilos == 1){
                    System.out.println("Has elegido " + numHilos + " hilo");
                }else {
                    System.out.println("Has elegido " + numHilos + " hilos ");
                }

                System.out.println("Elige número de iteraciones por hilo (debe ser un número entero): ");
                itusuario = s.nextInt();
                if (itusuario == 1){
                    System.out.println("Has elegido " + itusuario + " iteracion por hilo");
                }else {
                    System.out.println("Has elegido " + itusuario + " iteraciones por hilo");
                }

                entradaValida = true;

            } else {
                System.out.println("Entrada no válida. Se asignará el valor por defecto de 5 hilos, y 5 iteraciones por hilo.");
                numHilos = 5;
                itusuario = 5;
                break;
            }

        } while (!entradaValida);

        if (numHilos >= 1){
            new Tarea11("Hilo", 1, numHilos,itusuario).start();
        }

        s.close();

    }
}