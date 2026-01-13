# Ejercicio 38: Cifrado de texto con SHA-256

Este programa en Java solicita al usuario su nombre y un texto, y luego muestra el texto cifrado utilizando el algoritmo de hash SHA-256.

## Nota

El comentario original en el código fuente hace referencia al "cifrado del César", pero la implementación actual utiliza el algoritmo de hash SHA-256, que es un algoritmo de hashing criptográfico y no un cifrado de sustitución como el cifrado del César. Este README documenta la funcionalidad existente en el código (SHA-256).

## Funcionalidades

- Pide al usuario que ingrese su nombre.
- Pide al usuario que ingrese un texto para cifrar.
- Cifra el texto utilizando el algoritmo de hash SHA-256.
- Muestra el texto cifrado en formato hexadecimal.

## Requisitos

- Java JDK 17 o superior.

## ¿Cómo ejecutar el programa?

1.  Compile el archivo `Main.java`:
    ```bash
    javac Main.java
    ```
2.  Ejecute el programa:
    ```bash
    java Main
    ```

## Flujo del programa

1.  El programa se inicia y solicita al usuario su nombre.
2.  A continuación, solicita el texto que se desea cifrar.
3.  El método `usuario` toma el nombre y el texto como entrada.
4.  Dentro del método `usuario`, se crea una instancia de `MessageDigest` con el algoritmo "SHA-256".
5.  El texto de entrada se convierte en bytes y se pasa al objeto `MessageDigest`.
6.  Se calcula el hash del texto.
7.  El hash resultante (un array de bytes) se convierte a formato hexadecimal.
8.  Finalmente, el programa saluda al usuario por su nombre y muestra el texto cifrado en formato hexadecimal.