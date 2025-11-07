package Tarea23;

public class Tarea23 extends Thread{

    private double pagoCliente = Math.random()*100 +1 ;
    private int pagos;
    private int cajasupermercado;
    private int resultadosSupermercado;
    public Tarea23(){
        super();
    }

    public synchronized void caja(){
        int cajasupermercado = SuperMercado.getNcajas();
        cajasupermercado += pagos;
    }

    public void run(){

        System.out.println(Thread.currentThread().getName() + "Ha entrado en el supermercado.");
        try {
            wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        notifyAll();
        System.out.println(Thread.currentThread().getName()+ "Pagando");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        pagos += pagoCliente;
        System.out.println("Pagado y sumado" + cajasupermercado );



    }
    public static void main(String[] args) {


        Cliente cliente = new Cliente("Random",10);
    Thread cliente1 = new Thread(cliente);

    cliente1.start();

    try {
        cliente1.join();
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
        System.out.println(SuperMercado.c);
        System.out.println("Programa terminado");

    }
}



