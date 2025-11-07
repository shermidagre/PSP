package ej4;

public class Consumidor implements Runnable{
  private Buzon buzon;
  private final int limite = 100;

  public Consumidor(Buzon buzon){
      this.buzon = buzon;
  }
    @Override
    public void run() {
      String hiloactual = Thread.currentThread().getName();
      String mensajeRecogido;
        for (int i = 0 ; i < limite ; i++ ){
            try {
               mensajeRecogido = buzon.recoger();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println( "Soy " + hiloactual + " he recogido " +mensajeRecogido);
        }
    }
}
