package main.java.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class clienteTCP {

    public static void main(String[] args) {

        String host = "192.168.1.23";
        int port = 8080;

        try (Socket socket = new Socket(host, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner scanner = new Scanner(System.in)) {
            System.out.println("Conectando al servidor_cliente_TCPYUDP.servidor TCP " + host + ":" + port);

            System.out.println(in.readLine());
            System.out.println(in.readLine());
            System.out.println(in.readLine());
            System.out.println(in.readLine());
            pendienteDeLosTableros h1 = new pendienteDeLosTableros(in);
            h1.start();

            while (true) {
                System.out.print("Coordenadas (separadas por un espacio): ");
                String coordenadas = scanner.nextLine();
                out.println(coordenadas);

            }

        } catch (IOException e) {
            System.out.println(
                    "Error al conectar al servidor_cliente_TCPYUDP.servidor TCP "
                            + host + ":" + port);
        }
    }

    private static void recibirTablero(BufferedReader in) throws IOException {
        for (int i = 0; i < 10; i++) {
            System.out.println(in.readLine());
        }
    }
}
