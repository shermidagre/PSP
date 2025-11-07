package Tarea23;

public class Cliente implements Runnable{
    private  String nombre ;
    double numeroRandom = Math.random()*100 ;
    private  double saldo ;

    public Cliente(String nombre, double saldo){
        this.nombre = nombre;
        this.saldo = saldo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getNumeroRandom() {
        return numeroRandom;
    }

    public void setNumeroRandom(double numeroRandom) {
        this.numeroRandom = numeroRandom;
    }

    @Override
    public void run() {

    }
    public String toString(){
        return "Soy : " + nombre +
        "mi saldo es : " + saldo;
    }
}
