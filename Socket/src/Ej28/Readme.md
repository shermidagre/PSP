## Diagrama mermaid de como interactua por
detras el programa

````mermaid

classDiagram
    class Socket {
        +getInputStream()
        +getOutputStream()
        +close()
    }

    class ServerSocket {
        +bind(InetSocketAddress)
        +accept() Socket
    }

    class BufferedReader {
        +readLine() String
    }

    class PrintWriter {
        +println(String)
    }

    class Scanner {
        +nextLine() String
    }

    class InetSocketAddress {
        +InetSocketAddress(String, int)
    }

    class Servidor {
        -ServerSocket servidor
        -InetSocketAddress dir
        +main()
    }

    class Cliente {
        -Socket socket
        -InetSocketAddress dir
        -Scanner sc
        -PrintWriter escritor
        -BufferedReader lector
        +main()
    }

    class GestorClientes {
        -Socket socket
        +GestorClientes(Socket)
        +run()
    }

    %% Relaciones
    Servidor --> ServerSocket : usa
    Servidor --> InetSocketAddress : crea
    Servidor --> Socket : recibe de accept()
    Servidor --> GestorClientes : crea y lanza

    Cliente --> Socket : crea y usa
    Cliente --> InetSocketAddress : crea
    Cliente --> Scanner : usa
    Cliente --> PrintWriter : crea
    Cliente --> BufferedReader : crea

    GestorClientes --> Socket : usa
    GestorClientes --> BufferedReader : crea
    GestorClientes --> PrintWriter : crea
    GestorClientes --|> Thread : extiende

    Socket --> BufferedReader : interactúa con InputStream
    Socket --> PrintWriter : interactúa con OutputStream

````
[DiagramaInterno.mmd](DiagramaInterno.mmd)
##  Diagrama de flujo
[Diagramaconexiones.mmd](Diagramaconexiones.mmd)
````mermaid
sequenceDiagram
    participant Cliente
    participant Servidor

    Cliente->>Servidor: Conectar (puerto 6666)
    Servidor->>Cliente: Aceptar conexión y crear GestorClientes

    loop Intercambio de mensajes
        Cliente->>Servidor: Enviar mensaje (ej: "hola")
        Servidor->>Cliente: Responder con eco ("ECO ->> hola")
        Cliente->>Servidor: Enviar siguiente mensaje
        Servidor->>Cliente: Responder con eco
    end

    Cliente->>Servidor: Enviar "adios"
    Servidor->>Cliente: Cerrar conexión
    Cliente->>Servidor: Cerrar socket local
````

[Diagramaconexiones.mmd](Diagramaconexiones.mmd)