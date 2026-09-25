Hola profesor.

Lo principal del código se encuentra en la carpeta juego (dentro de src/main/java), donde separé la lógica del servidor y del cliente en sus respectivas carpetas. Los demás archivos son trabajos en clase o clases repetidas.

Sobre el funcionamiento:
*  Es un tablero fijo de 10x10. Las coordenadas se ingresan separadas por un espacio (ejemplo: 1 2).
*  Es una versión simplificada: el juego termina apenas se pisa una bomba y las casillas adyacentes no revelan información (no es como el buscaminas normal).
*  Para que el tablero se mantenga actualizado en tiempo real para todos los jugadores simultáneamente, utilicé un hilo secundario (por eso la clase pendienteDeLosTableros extiende de Thread).
