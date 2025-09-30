
public class Tarea08 extends Thread{



    private final int limiteProf;
    private final String cabreoProf;
    public Tarea08(String nombre, int limite,String cabreo) {
        super(nombre);
        this.limiteProf = limite;
        this.cabreoProf = cabreo;
    }

    // Definimos lo que hace el hilo
    @Override
    public void run() {
        int nivelCabreo = 0;

        while (nivelCabreo < limiteProf) {
            nivelCabreo++;

            String mensaje = "";
            if (nivelCabreo == limiteProf) {
                mensaje =  " [Frase iconica] "+ cabreoProf;
            }

            System.out.println(getName() + " Nivel de cabreo: " + nivelCabreo + mensaje);

        }
        System.out.println("programa terminado");
    }


    public static void main(String[] args) {

        new Tarea08("[Manuel]",5,"Me voy a cagar en el metodo a cadea").start();
        new Tarea08("[Diego]",4,"multiprocesos enjoyer").start();
        new Tarea08("[Damian]",3,"esto con un for se soluciona").start();
        new Tarea08("[Araujo]",5,"te pasa por no mover los archivos").start();


    }
}