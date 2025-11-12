import EJ1.HiloContador;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public static void main(String[] args) {
    HiloContador hilo1 = new HiloContador();
    HiloContador hilo2 = new HiloContador();
    HiloContador hilo3 = new HiloContador();

    hilo1.start();
    hilo2.start();
    hilo3.start();
}
