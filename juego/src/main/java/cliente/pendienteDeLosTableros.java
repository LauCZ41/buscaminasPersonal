package main.java.cliente;

import java.io.BufferedReader;
import java.io.IOException;

class  pendienteDeLosTableros extends Thread {
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
                } else if (linea.equalsIgnoreCase("VICTORIA")) {
                    System.out.println("¡VICTORIA! Han encontrado todas las casillas seguras.");
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