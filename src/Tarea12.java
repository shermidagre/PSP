public class Tarea12 extends Thread{


    public Tarea12 (String nombre ) {
        super(nombre);

    }

    public void run() {

        if (getName().equalsIgnoreCase("Hilo1")) {
            for (int i = 0; i < 1924; i++) {
                if (i % 2 == 0) {
                    System.out.println("Hilo 1 : " + i + "(Numeros pares)");
                }
            }
        }
        if(getName().equalsIgnoreCase("Hilo2")){
            for (int i = 0; i < 1924; i++){
                if (i % 2 != 0) {
                        System.out.println("Hilo 2 : " + i + "(Numeros impares)");
                }
            }
        }
        if (getName().equalsIgnoreCase("Hilo3")){
            for (int i = 0; i < 1924; i++){
                int n = i %10;
                if (n== 3 || n == 4){
                    System.out.println("Hilo 3 : " +  i + "(Numeros divisibles entre 3 o 4)");
                }
            }
        }
    }
    public static void main (String[]args){

        new Tarea12("Hilo1").start();
        new Tarea12("Hilo2").start();
        new Tarea12("Hilo3").start();

    }
}
