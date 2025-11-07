

void main() {
    Scanner sc = new Scanner(System.in);

    try {
        InetAddress local = InetAddress.getLocalHost();
        System.out.println("Tu ip local es : " + local.getHostAddress());
    } catch (UnknownHostException e) {
        System.out.println("Error: " + e.getMessage());

    }

    String ipUsuario;
    System.out.println("Escribe tu ip");
    ipUsuario = sc.nextLine();
    int puerto;
    String salir = "";
    while (salir != "salir") {
        System.out.println("Elige a el puerto que te quieras conectar" +
                "\n puertos conocidos: " +
                "\n 21 ftp " +
                "\n 22 ssh" +
                "\n 80 http" +
                "\n 443 https" +
                "\n 5357 puerto abierto de windows" +
                "\n 8080 puerto abierto con " +
                "\n # Comando de PowerShell:" +
                "[System.Net.Sockets.TcpListener]::new(8080).Start()");
        puerto = sc.nextInt();
        switch (puerto) {
            case 21:
                System.out.println("Conectando a ftp");
                break;
            case 22:
                System.out.println("Conectando a ssh");
                break;
            case 80:
                System.out.println("Conectando a http");
                break;
            case 443:
                System.out.println("Conectando a https");
                break;

                case 5357:
                System.out.println("Conectando a puerto abierto de windows");
                break;
            case 8080:
                System.out.println("Conectando a puerto abierto con # Comando de PowerShell:" +
                        "[System.Net.Sockets.TcpListener]::new(8080).Start()");
                break;
            default:
                System.out.println("Puerto no valido");
                return;
        }
        try {
            Socket socket = new Socket(ipUsuario, puerto);
            System.out.println("Conectado a: " + socket.getInetAddress());
            socket.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Quieres conectarte a otro puerto? (salir para terminar)");
        salir  = sc.next();
    }
}

