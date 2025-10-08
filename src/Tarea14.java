public class Tarea14 extends Thread{


    public static double capital = 1000;
    public Tarea14(String nombre){
        super(nombre);
    }

    public void run() {
        int contadorsuma = 0;
        int contadoresta = 0;

        if (getName().equalsIgnoreCase("Hilo de ingresos")) {

            for (int i = 1; i < 501; i++) {

                System.out.println("Saldo actual " + capital);

                synchronized (Tarea14.class) {
                    capital += 10;
                }
                contadorsuma++;

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(getName().equalsIgnoreCase("Hilo de ingresos") + " Hilo de ingresos ha tenido : " + i + " interaciones");
            }
            System.out.println(getName().equalsIgnoreCase("Hilo de ingresos") + " Hilo de ingresos ha tenido llegado a su limite de iteraciones" );
            System.out.println("Contador de veces : " + contadorsuma);


            if ( contadorsuma == contadorsuma ) {
                for (int i = 1; i < 301; i++) {

                    System.out.println("Saldo actual " + capital);

                    synchronized (Tarea14.class) {
                        capital -= 10;
                    }

                    contadoresta++;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(getName().equalsIgnoreCase("Hilo de extraciones") + "Hilo de extraciones ha tenido : " + i + " interaciones");
                }
                System.out.println(getName().equalsIgnoreCase("Hilo de extraciones") + " Hilo de extraciones ha tenido llegado a su limite de iteraciones" );
                System.out.println("Contador de veces " + contadoresta  );
                System.out.println("Saldo actual " + capital);
            }
        }
    }

    public static void main (String[]args){

        Tarea14 hiloIngresos = new Tarea14("Hilo de ingresos");
        Tarea14 hiloExtracciones = new Tarea14("Hilo de extraciones");

        hiloIngresos.start();
        hiloExtracciones.start();

        try {
            hiloIngresos.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
