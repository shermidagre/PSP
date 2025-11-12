package random;

public class usuario extends Thread{

    public Object candado;
    public int contadorUsuario;
    public usuario(String nombre, Object candado){
        super(nombre);
        this.candado  =candado;

    }
    String hiloactual = Thread.currentThread().getName();

    @Override
    public void run() {
        System.out.println(hiloactual + " acaba de empezar ");

        try{
            synchronized (candado){
                System.out.println(hiloactual + " ha entrado en el candado");
                currentThread().sleep(1500);
                for (int i= 0; i < 5 ; i++){
                    contadorUsuario++;
                    System.out.println("Iteracion " + i );
                }
                System.out.println("Ha hecho un total de " + contadorUsuario);
                System.out.println(hiloactual + " ha salido del candado");
            }
        }catch (InterruptedException e){
            System.out.println(hiloactual + " ha sido interrumpido");
        }
        }

}
