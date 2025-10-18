package Tarea20;

public class Lector implements Runnable {
    private final correo correo;

    public Lector(correo correo) {
        this.correo = correo;
    }

    @Override
    public void run() {
        String mensajeLeido = correo.leer();
    }
}