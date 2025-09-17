
> Explora las capacidades del entorno de ejecución de Java y crea una herramienta útil para manipular archivos desde la terminal.

---

##  TAREA 2: Información del Sistema y JVM

### 📋 Objetivo

Revisa la documentación de las clases `System` y `Runtime` para desarrollar un programa que, al ejecutarse, muestre:

-  **Memoria total, libre y en uso** — expresada en **MiB** (Mebibytes).
-  **Número de procesadores** disponibles para la JVM.
-  **Todas las propiedades del sistema** (`System.getProperties()`) junto con sus valores.

---

##  TAREA 3: Editor de Archivos desde Consola

### 📋 Objetivo

Crea un programa en Java que permita al usuario:

-  **Abrir un archivo existente** o **crear uno nuevo** desde la línea de comandos.
-  Utilizar un **editor de texto sencillo del sistema** (como `gedit`, `gnome-text-editor`, `notepad`, etc.).
-  Ingresar la **ruta completa o nombre del archivo** (incluyendo extensión).
-  Si el archivo no existe, el sistema lo creará automáticamente al abrirlo con el editor.

###  Ejemplo de uso esperado:

```bash
$ java FileEditor
Ingresa la ruta del archivo: mi_documento.txt
→ Abriendo "mi_documento.txt" con gedit...
```

### Compatibilidad sugerida:

- Linux:  `gnome-text-editor`
- Windows: `notepad`

> ⚠️ El programa debe detectar el sistema operativo y elegir un editor predeterminado adecuado, o permitir configurarlo.

Perfecto, aquí tienes el `README.md` **estrictamente basado en el texto que me enviaste**, sin añadidos, interpretaciones ni extensiones:

---

## TAREA 4: Información del Sistema y JVM

### 📋 Objetivo

Crea un programa en Java que ejecute `dir` o `ls` en función del sistema operativo. Utiliza la clase `System`. Luego, que haga lo siguiente:

1. Muestra el directorio de trabajo y la propiedad `user.dir` por defecto.
2. Muestra el directorio de trabajo y la propiedad `user.dir` después de cambiar el valor de la propiedad `user.dir` a la carpeta home del usuario.
3. Muestra el directorio de trabajo y la propiedad `user.dir` después de cambiar el directorio de trabajo al directorio temporal (`c:/temp` o `/tmp`).

> **OJO**: El proceso aunque se ejecute no mostrará el listado de directorios.

---
