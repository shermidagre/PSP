package Tarea23;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int numeroClientesM;
        int numeroCajasN;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Introduce el número de clientes (M) y el número de cajas (N): ");
            numeroClientesM = sc.nextInt();
            numeroCajasN = sc.nextInt();

            } catch (NumberFormatException e) {
                System.out.println("Introduce numeros entreos.");
                return;
            }

        SuperMercado supermercado = new SuperMercado(numeroCajasN);

        Cliente[] clientes = new Cliente[numeroClientesM];

        // Crear y lanzar los threads de los clientes (primera vez que lo uso asi)
        for (int i = 0; i < numeroClientesM; i++) {
            clientes[i] = new Cliente(i + 1, supermercado);
            clientes[i].start();
        }// Esperar a que todos los threads terminen
        try {
            for (Cliente cliente : clientes) {
                cliente.join();
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
            System.out.println("Importe de todos los clientes : " + Caja.obtenerImporteTotal());
        }
    }
