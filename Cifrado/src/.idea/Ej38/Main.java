/*
Investiga en qué consistía el cifrado del César y realiza un programa que pida un texto para cifrar utilizando dicho método y que además solicite el número de desplazamiento del mismo. La ejecución del programa es la siguiente:

- El programa pide el texto a cifrar

- Pide el desplazamiento del mismo

- Muestra el texto cifrado
 */


void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    System.out.println("Ingrese su nombre: ");
    String nombre = sc.nextLine();

    System.out.println("Ingrese el texto a cifrar: ");
    String texto = sc.nextLine();

    try {
        usuario(nombre, texto);
    } catch (NoSuchAlgorithmException e) {
        System.out.println("Error: " + e.getMessage());
    }

    sc.close();

}
// Hay que agregar la excepcion NoSuchAlgorithmException para el getInstance
static void usuario(String nombre, String texto) throws NoSuchAlgorithmException {

    System.out.println("Hola " + nombre);

    String cifrado = texto;

    MessageDigest md = MessageDigest.getInstance("SHA-256");

    md.update(cifrado.getBytes());

    byte [] resumen = md.digest();

    String hex = HexFormat.of().formatHex(resumen);

    System.out.println("El texto cifrado es: " + hex);
}