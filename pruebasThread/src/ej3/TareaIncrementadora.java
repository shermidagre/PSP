package ej3;

public class TareaIncrementadora implements Runnable{

    private final ContadorInseguro contadorInseguro;

public TareaIncrementadora (ContadorInseguro contadorCompartido){
    this.contadorInseguro = contadorCompartido;
}
    @Override
    public void run() {
        String hiloactual = Thread.currentThread().getName();
        System.out.println("Ha empezado : " + hiloactual);
        for (int i = 0; i<1000;i++){
            contadorInseguro.incrementar();
        }
        System.out.println("Hilo terminado" + hiloactual);
    }

}
