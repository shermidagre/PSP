public class Tarea13 extends Thread{

    public Tarea13 (String nombre ) {
        super(nombre);

    }

    public void run() {

        for (int i = 0 ; i<11 ; i++){
            System.out.println(" Hilo --> " + getName() +" valor de i : " + i + " Prioridad : "  + getPriority());
            long tiempoAleatorio = (long) (Math.random() * 900 + 100); // Rango de 100 a 1000 ms
            try {
                Thread.sleep(tiempoAleatorio);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main (String[]args){

        Tarea13 Prioridadmax  = new Tarea13("Hilo 1");

        Prioridadmax.setPriority(Thread.MAX_PRIORITY);

        Tarea13 Prioridadmin = new Tarea13("Hilo 2");

        Prioridadmin.setPriority(Thread.MIN_PRIORITY);

        Tarea13 Prioridadmedia  = new Tarea13("Hilo 3");

        Prioridadmedia.setPriority(Thread.NORM_PRIORITY);

        Tarea13 Hilorandom  = new Tarea13("Hilo 4");

        Hilorandom.setPriority(Thread.NORM_PRIORITY);


        Prioridadmax.start();
        Prioridadmin.start();
        Prioridadmedia.start();
        Hilorandom.start();
    }
}
