package eJ2;

public class TareaContador implements Runnable{
    @Override
    public void run() {
        String hiloactual = Thread.currentThread().getName();
        System.out.println("Iniciando hilo: " + hiloactual);
        for (int i= 0; i < 5 ; i++){
            System.out.println(hiloactual + " - Contador: " + i);
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.err.println("La secuencia fue interrumpida.");
            }
        }
        System.out.println("Fin " + hiloactual);
    }
}
