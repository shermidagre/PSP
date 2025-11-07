package Tarea23;

import java.awt.*;

public class SuperMercado implements Runnable{

    private static int ncajas;


    public void realizarPagos(){

    }
    public void clienteshoy (Cliente cliente){
        List Clientes = new List();
        Clientes.add(String.valueOf(cliente));
        System.out.println("Total de clientes hoy");
        System.out.println(Clientes);
    }

    public static int getNcajas() {
        return ncajas;
    }

    public static void setNcajas(int ncajas) {
        SuperMercado.ncajas = ncajas;
    }

    @Override
    public void run() {

    }

    @Override
    public String toString() {
        return ("Ncajas : " + ncajas);
    }
}
