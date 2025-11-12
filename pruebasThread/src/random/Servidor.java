package random;

public class Servidor extends Thread{
    public Object candado;
    public int contadorServidor;
    public String nombre;
    public Servidor(String nombre,Object candado){
        super(nombre);
        this.candado = candado;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        System.out.println(nombre + "Acaba de comenzar");
        synchronized (candado){
            contadorServidor ++;
        }
        for (int i =  0; i < 5; i++){
            System.out.println(nombre + ": " + i);
        }

        System.out.println(nombre + "Acaba de terminar");
        System.out.println("Contador del servidor: " + contadorServidor);
        try{
            currentThread().sleep(1500);
        }catch (InterruptedException e){
            System.out.println(nombre + " ha sido interrumpido");
        }
    }
}
