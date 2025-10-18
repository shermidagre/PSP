package Tarea20;

public class Escritor implements Runnable {
    private final correo correo;
    private final String mensajeAEnviar;

    public Escritor(correo correo, String mensaje) {
        this.correo = correo;
        this.mensajeAEnviar = mensaje;
    }

    @Override
    public void run() {
        correo.escribir(mensajeAEnviar);
    }
}