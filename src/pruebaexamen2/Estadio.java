package pruebaexamen2;

public class Estadio {
    void main() {
            Usuario u1 = new Usuario();
            Usuario u2 = new Usuario();
            Usuario u3 = new Usuario();
            Usuario u4 = new Usuario();
            Usuario u5 = new Usuario();

            Thread usuario1 = new Thread(u1, "Usuario 1");
            Thread usuario2 = new Thread(u2, "Usuario 2");
            Thread usuario3 = new Thread(u3, "Usuario 3");
            Thread usuario4 = new Thread(u4, "Usuario 4");
            Thread usuario5 = new Thread(u5, "Usuario 5");

            usuario1.start();
            usuario2.start();
            usuario3.start();
            usuario4.start();
            usuario5.start();

            try {
                usuario1.join();
                usuario2.join();
                usuario3.join();
                usuario4.join();
                usuario5.join();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

