import java.io.*;
import java.net.*;
import java.util.Scanner;

public class clienteTCP {

    public static void main(String[] args) {

        String host = "192.168.1.23";
        int port = 8080;

        try (Socket socket = new Socket(host, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner scanner = new Scanner(System.in)) {
            System.out.println("Conectando al servidor TCP " + host + ":" + port);

            System.out.println(in.readLine());

            System.out.println(in.readLine());
            System.out.println(in.readLine());
            pendienteDeLosTableros h1 = new pendienteDeLosTableros(in);
            h1.start();

            while (true) {
                System.out.print("Coordenadas: ");
                String coordenadas = scanner.nextLine();
                out.println(coordenadas);

            }

        } catch (IOException e) {
            System.out.println(
                    "Error al conectar al servidor TCP "
                            + host + ":" + port);
        }
    }

    private static void recibirTablero(BufferedReader in) throws IOException {
        for (int i = 0; i < 10; i++) {
            System.out.println(in.readLine());
        }
    }
}

class pendienteDeLosTableros extends Thread {
    private BufferedReader in;

    public pendienteDeLosTableros(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            String linea;
            while ((linea = in.readLine()) != null) {
                if (linea.equals("TABLERO")) {
                    System.out.println("\n--- TABLERO ---");
                    for (int i = 0; i < 10; i++) {
                        System.out.println(in.readLine());
                    }
                    System.out.println("---------------");
                } else if (linea.equalsIgnoreCase("BOMBA") || linea.equalsIgnoreCase("FIN")) {
                    System.out.println("¡BOMBA! FIN");
                    System.exit(0);
                } else {
                    System.out.println("Servidor: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Conexión cerrada.");
        }
    }
}