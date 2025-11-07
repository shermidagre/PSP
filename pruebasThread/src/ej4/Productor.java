package ej4;

public class Productor implements Runnable{

    private Buzon buzon;
    private final int limite = 100;

    public Productor(Buzon buzon){
        this.buzon = buzon;
    }
    @Override
    public void run() {
        String hiloactual = Thread.currentThread().getName();
        String mensajeenviado = "no se ";
        for (int i = 0 ; i<limite ; i++){
            try {
                buzon.depositar(mensajeenviado);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
