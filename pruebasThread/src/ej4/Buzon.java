package ej4;

public class Buzon {
     String mensaje;
     boolean lleno = false;

    public synchronized void depositar (String nuevomensaje) throws InterruptedException {

        while (lleno){
            System.out.println("Buzon lleno");
            wait();
        }

        this.mensaje = nuevomensaje;
        this.lleno = true;
        System.out.println("Nuevo mensaje : " + nuevomensaje);

        notify();
    }

    public synchronized String recoger() throws InterruptedException {

        while (!lleno){
            System.out.println("Consumidor esperando: Buzón vacío.");
            wait();
        }

        String mensajeRecogido = this.mensaje;
        this.mensaje = null;
        this.lleno = false;
        System.out.println("recogido: " + mensajeRecogido);

        return mensajeRecogido;
    }

}
