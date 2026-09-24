package servidor;

import model.Tablero;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.concurrent.*;

public class ConcurrentTCPServer {
    private static final int PORT = 8080;
    private static final ExecutorService pool = Executors.newFixedThreadPool(50);
    private static final List<PrintWriter> jugadores = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {

        Tablero tablero = new Tablero();
        tablero.iniciadorTablero();
        tablero.colocarBombas(30);

        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Hilos hilos en  " + PORT);
            while (true) {
                Socket clientSocket = server.accept();
                pool.execute(new ClientHandler(clientSocket, tablero, jugadores));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private final Socket socket;
    private final Tablero tablero;
    private final List<PrintWriter> jugadores;

    public ClientHandler(Socket socket, Tablero tablero, List<PrintWriter> jugadores) {
        this.socket = socket;
        this.tablero = tablero;
        this.jugadores = jugadores;
    }

    private void enviarTableroATodos() {
        for (PrintWriter jugador : jugadores) {
            tablero.mostrarTablero();
        }
    }

    private void enviarTableroATodosLOL() {
        for (PrintWriter jugador : jugadores) {
            jugador.println("TABLERO");
            for (int i = 0; i < 10; i++) {
                StringBuilder fila = new StringBuilder();
                for (int j = 0; j < 10; j++) {
                    fila.append(tablero.getTablero()[i][j]).append(" ");
                }
                jugador.println(fila);
            }
        }
    }

    @Override
    public void run() {
        try (Socket s = this.socket;
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter out = new PrintWriter(s.getOutputStream(), true)) {

            jugadores.add(out);
            System.out.println("Atendiendo cliente en hilo: " + Thread.currentThread().getName());
            String input;
            out.println("Bienvenido al juego!");
            out.println("Ingrese coordenadas: fila columna");

            // tablero.mostrarTablero();
            out.println("     ");
            tablero.mostrarTableroREAL();
            int fila, columna;
            boolean fin;
            while ((input = in.readLine()) != null) {
                if ("FIN".equalsIgnoreCase(input.trim()) || "BOMBA".equalsIgnoreCase(input.trim()))
                    break;

                // dd
                System.out.println(" ");
                System.out.println("Jugador " + Thread.currentThread().getName() + ": " + input);
                String[] coordenadas = input.split(" ");
                columna = Integer.parseInt(coordenadas[0]);
                fila = Integer.parseInt(coordenadas[1]);
                fin = tablero.verificarImpacto(fila, columna);
                enviarTableroATodosLOL();
                tablero.mostrarTableroREAL();
                if (!fin) {
                    out.println("SALVO");
                }
                if (fin) {
                    out.println("FIN");
                    input = "BOMBA";
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
