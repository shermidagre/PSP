
#  Tareas de Java: Sistema y Runtime + Editor de Archivos

> ✨ Explora las capacidades del entorno de ejecución de Java y crea una herramienta útil para manipular archivos desde la terminal.

---

##  TAREA 2: Información del Sistema y JVM

### 📋 Objetivo

Revisa la documentación de las clases `System` y `Runtime` para desarrollar un programa que, al ejecutarse, muestre:

- 💾 **Memoria total, libre y en uso** — expresada en **MiB** (Mebibytes).
- ⚙️ **Número de procesadores** disponibles para la JVM.
- 🏷️ **Todas las propiedades del sistema** (`System.getProperties()`) junto con sus valores.

### ¿Qué aprenderás?

- Cómo obtener información del entorno de ejecución con `Runtime.getRuntime()`.
- Cómo acceder a las propiedades del sistema con `System.getProperties()`.
- Manejo de unidades de memoria y formato legible para el usuario.

---

## 🖊️ TAREA 3: Editor de Archivos desde Consola

### 📋 Objetivo

Crea un programa en Java que permita al usuario:

- 📂 **Abrir un archivo existente** o **crear uno nuevo** desde la línea de comandos.
- 🖥️ Utilizar un **editor de texto sencillo del sistema** (como `gedit`, `gnome-text-editor`, `notepad`, etc.).
- 📍 Ingresar la **ruta completa o nombre del archivo** (incluyendo extensión).
- ✅ Si el archivo no existe, el sistema lo creará automáticamente al abrirlo con el editor.

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


