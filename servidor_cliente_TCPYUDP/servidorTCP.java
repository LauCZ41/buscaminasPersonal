package servidor_cliente_TCPYUDP;

import java.io.*;
import java.net.*;

public class servidorTCP {
    public static void main(String[] args) {
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println(
                    "Servidor TCP listo en el puerto " + port + serverSocket.getInetAddress().getHostAddress());

            while (true) {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clienteSocket.getRemoteSocketAddress());

                try (
                        BufferedReader in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
                        PrintWriter out = new PrintWriter(clienteSocket.getOutputStream(), true)) {
                    String line;
                    while ((line = in.readLine()) != null) {
                        System.out.println("Peticion: " + line);
                        out.println("ECO TCP: " + line);
                        if ("FIN".equalsIgnoreCase(line.trim())) {
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error manejando al servidor_cliente_TCPYUDP.cliente: " + e.getMessage());
                } finally {
                    try {
                        clienteSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}