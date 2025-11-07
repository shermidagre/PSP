package EJ1;

public class HiloContador extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Hola desde: " + this.getName() + " - Contador: " + i);
        }
    }

}

