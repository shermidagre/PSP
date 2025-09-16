import java.io.IOException;

public class Tarea2 {
    public static void main(String[] args) {

       Runtime r = Runtime.getRuntime();
        int memorialibre = Math.toIntExact(r.freeMemory()); // metodo para conseguir la memoria libre en la virtual de java
        int memoriatotal = Math.toIntExact(r.totalMemory()); // metodo para conseguir la memoria total en la virtual de java
        System.out.println("Memoria libre "+memorialibre);
        System.out.println("Memoria total "+memoriatotal);

    }
}
