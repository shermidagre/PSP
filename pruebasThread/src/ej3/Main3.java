
import ej3.ContadorInseguro;
import ej3.TareaIncrementadora;


void main(String[] args) {

    ContadorInseguro contador = new ContadorInseguro();

    TareaIncrementadora tarea1 = new TareaIncrementadora(contador);
    TareaIncrementadora tarea2 = new TareaIncrementadora(contador);

    Thread hilo1 = new Thread(tarea1,"h1");
    Thread hilo2 = new Thread(tarea2,"h2");

    hilo1.start();
    hilo2.start();
    try {
        hilo1.join();
        hilo2.join();

    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
    System.out.println("Terminado");
    System.out.println(contador.getValor());
}
