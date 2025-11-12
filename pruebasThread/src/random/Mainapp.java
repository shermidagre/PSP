package random;


public class Mainapp {
    public static void main() {

        Object objecto = new Object();

        usuario usuario = new usuario("p1", objecto);
        Servidor servidor = new Servidor("s1", objecto);


        servidor.start();
        usuario.start();

        try {
            servidor.join();
            usuario.join();
        }catch (InterruptedException e){
            System.out.println(" interrumpido main app "+ e);
        }

    }
}


