import ej4.Buzon;
import ej4.Consumidor;
import ej4.Productor;

void main() {
  Buzon buzon = new Buzon();
  Productor productor = new Productor(buzon);
 Consumidor consumidor = new Consumidor(buzon);


    Thread Productor = new Thread(productor,"Productor");
    Thread Consumidor = new Thread(consumidor, "Consumidor");

    Productor.start();
    Consumidor.start();
    try {
        Productor.join();
        Consumidor.join();
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
}
