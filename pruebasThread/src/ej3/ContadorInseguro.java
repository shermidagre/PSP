package ej3;

public class ContadorInseguro {
    int valor = 0 ;

    public synchronized void  incrementar(){
        valor++;
    }

    public int getValor(){
        return valor;
    }
}
