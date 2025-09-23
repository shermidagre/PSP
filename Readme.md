
> Explora las capacidades del entorno de ejecución de Java y crea una herramienta útil para manipular archivos desde la terminal.

---

## Indice

- [TAREA 2: Información del Sistema y JVM](#tarea-2-información-del-sistema-y-jvm)
- [TAREA 3: Editor de Archivos desde Consola](#tarea-3-editor-de-archivos-desde-consola)
- [TAREA 4: Directorios y rutas](#tarea-4-directorios-y-rutas)

---

##  Tarea 2: Información del Sistema y JVM

### 📋 Objetivo

Revisa la documentación de las clases `System` y `Runtime` para desarrollar un programa que, al ejecutarse, muestre:

-  **Memoria total, libre y en uso** — expresada en **MiB** (Mebibytes).
-  **Número de procesadores** disponibles para la JVM.
-  **Las propiedades de la clase System** junto con sus valores.

---

##  Tarea 3: Editor de Archivos desde Consola

### 📋 Objetivo

Crea un programa en Java que permita al usuario:

-  **Abrir un archivo existente** o **crear uno nuevo** desde la línea de comandos.
-  Utilizar un **editor de texto sencillo del sistema** (como `gedit`, `gnome-text-editor`, `notepad`, etc.).
-  Ingresar la **ruta completa o nombre del archivo** (incluyendo extensión).
-  Si el archivo no existe, el sistema lo creará automáticamente al abrirlo con el editor.


### Compatibilidad sugerida:

- Linux:  `gnome-text-editor`
- Windows: `notepad`

> ⚠️ El programa debe detectar el sistema operativo y elegir un editor predeterminado adecuado, o permitir configurarlo.

---

## Tarea 4: Directorios y rutas

### 📋 Objetivo

Crea un programa en Java que ejecute `dir` o `ls` en función del sistema operativo. Utiliza la clase `System`. Luego, que haga lo siguiente:

1. Muestra el directorio de trabajo y la propiedad `user.dir` por defecto.
2. Muestra el directorio de trabajo y la propiedad `user.dir` después de cambiar el valor de la propiedad `user.dir` a la carpeta home del usuario.
3. Muestra el directorio de trabajo y la propiedad `user.dir` después de cambiar el directorio de trabajo al directorio temporal (`c:/temp` o `/tmp`).

> **OJO**: El proceso aunque se ejecute no mostrará el listado de directorios.

---
Claro, aquí tienes el contenido adaptado al formato `README.md` siguiendo la estructura que indicaste:

---

## Tarea 5: Directorios y rutas

### 📋 Objetivo

Crea un programa en Java que esté en bucle realizando lo siguiente:

1. Pide por consola al usuario un comando y sus parámetros a ejecutar (si fuese necesario) (Por ejemplo, `ls`, `gnome-text-editor`, `open`, etc.).
2. Lanza el proceso y obtén el código de finalización del mismo.
3. Muestra el nombre del programa y el código de finalización del mismo.

El programa finaliza cuando el usuario introduce **“salir”** y devolverá un código de salida = `0`.

---

### 🧪 Ejemplo de ejecución

```
Introduce un comando (o 'salir' para terminar): ls -la
Ejecutando: ls -la
Código de finalización: 0

Introduce un comando (o 'salir' para terminar): comandoInexistente
Ejecutando: comandoInexistente
Código de finalización: 127

Introduce un comando (o 'salir' para terminar): salir
¡Hasta luego!
```

---

¡Claro! Aquí tienes el texto adaptado al formato `README.md`, siguiendo un estilo claro, estructurado y listo para incluir en tu repositorio:

---

## Tarea 7: Ejecución de un script Python desde Java

### 📋 Objetivo

Desde un programa escrito en **Java**, debes:

1. **Ejecutar** el siguiente script de **Python** `Tarea7.1.py` .

---

### Script Python a ejecutar

 `Tarea7.1.py`:

```python
import json
import sys

pelicula = {
    "The Big Lebowski": {
        "Director": "Joel Coen y Ethan Coen",
        "Anyo": 1998,
        "Reparto": [
            {"Nombre": "Jeff Bridges"},
            {"Nombre": "John Goodman"},
            {"Nombre": "Julianne Moore"},
            {"Nombre": "Steve Buscemi"}
        ]
    }
}

print(json.dumps(pelicula))
sys.exit(0)
```

### 🖥️ Ejemplo de salida esperada en Java

```
Ejecutando script de Python...
Salida recibida:
{"The Big Lebowski": {"Director": "Joel Coen y Ethan Coen", "Anyo": 1998, "Reparto": [{"Nombre": "Jeff Bridges"}, {"Nombre": "John Goodman"}, {"Nombre": "Julianne Moore"}, {"Nombre": "Steve Buscemi"}]}}
Proceso finalizado con código: 0
```

---

