package servidor_cliente_TCPYUDP;

import java.net.*;

public class servidor {
    public static void main(String[] args) {
        int port = 5000;
        try(DatagramSocket socket = new DatagramSocket(port)){
            System.out.println("Servidor UDP iniciado, escuchando puerto: " +port);
            byte[] buffer = new byte[1024];

            while(true){
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                String message = new String(request.getData(), 0, request.getLength());
                System.out.println("Recibido: "+message);

                byte[] responseBytes = ("ECO: "+message).getBytes();
                DatagramPacket response = new DatagramPacket(
                        responseBytes, responseBytes.length, request.getAddress(), request.getPort()
                );
                socket.send(response);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
