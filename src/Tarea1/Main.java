package Tarea1;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String[] procesoAEjecutar = {"gnome-text-editor","prueba.txt"};
        for (int i=0;i< 200; i++){
            Runtime.getRuntime().exec(procesoAEjecutar);
        }
    }
}