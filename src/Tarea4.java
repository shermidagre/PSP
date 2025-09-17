import java.io.File;

public class Tarea4 {
    public static void main(String[] args) {

        boolean esLinux = (System.getProperty("os.name").toLowerCase().startsWith("linux"));
        System.out.println("Mensaje de depuracion funcionamiento so : "+esLinux );
        ProcessBuilder pb = new ProcessBuilder();
        if(esLinux){
            pb.command().add(0,"sh");
            pb.command().add(1,"-c");
            pb.command().add(2,"dir");
        }else {
            pb.command().add(0,"cmd");
            pb.command().add(1,"/c");
            pb.command().add(2,"ls");
        }
        System.out.println("Mensaje de depuracion comandos: "+pb.command());
        String ruta = System.getProperty("user.dir");
        System.out.println("Otra vez por aqui chavales \nEj 1.Quieres saber tu ruta por defecto?\nAqui la tienes my king: "+ruta );
        String directorio = new File(ruta).getName();
        System.out.println("El directorio actual es : "+directorio);
        String rutahome = System.setProperty("user.home", ruta);
        System.out.println("Ej 2. Tu ruta home es: "+rutahome);
        String rutaTemp = System.setProperty("java.io.tmpdir",ruta);
        System.out.println("Ej 3.Tu ruta para temp es: "+ rutaTemp);
    }
}

