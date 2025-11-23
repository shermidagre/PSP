package Ej21;

public class Coche extends Thread{
    private final Parking parking;
    private final int idCoche;

    public Coche(Parking parking, int identificador){
        this.parking = parking;
        this.idCoche = identificador;
    }

    private long tiempoAleatorio(){
        return (long) (Math.random()*400+100);
    }

    @Override
    public void run(){
        while (true) {

            parking.aparcar(idCoche);

            try {
                Thread.sleep(tiempoAleatorio());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }

            parking.abandonarParking(idCoche);

            try {
                Thread.sleep(tiempoAleatorio());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
