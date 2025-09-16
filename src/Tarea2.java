import java.io.IOException;

public class Tarea2 {
    public static void main(String[] args) {
        int miB = 1024;
       Runtime r = Runtime.getRuntime();
        int memorialibre = Math.toIntExact(r.freeMemory()/miB); // metodo para conseguir la memoria libre en la virtual de java
        int memoriatotal = Math.toIntExact(r.totalMemory()/miB); // metodo para conseguir la memoria total en la virtual de java
        int memoriaenuso = Math.toIntExact(r.maxMemory())/miB; // metodo para conseguir la memoria en uso
        System.out.println("Memoria libre "+memorialibre);
        System.out.println("Memoria total "+memoriatotal);
        System.out.println("Memoria en uso "+memoriaenuso);

    }
}
