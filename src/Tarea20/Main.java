package Tarea20;

public class Main {

    public static void main(String[] args) {
        correo correo = new correo();

        Thread Lector1 = new Thread(new Lector(correo), "Lector 1");
        Thread Lector2 = new Thread(new Lector(correo), "Lector 2");
        Thread Escritor1 = new Thread(new Escritor(correo, "Mensaje Escritor 1"), "Escritor 1");
        Thread Escritor2 = new Thread(new Escritor(correo, "Mensaje Escritor 2"), "Escritor 2");
        Thread Escritor3 = new Thread(new Escritor(correo, "Mensaje Escritor 3"), "Escritor 3");



        Lector1.start();
        Lector2.start();


        Escritor1.start();
        Escritor2.start();
        Escritor3.start();

        try {
            Lector1.join();
            Lector2.join();
            Escritor1.join();
            Escritor2.join();
            Escritor3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Depuracion, fin de el programa");

    }
}