package servidor_cliente_TCPYUDP;

import java.net.*;

public class cliente {
    public static void main(String[] args) {
        String host = "Localhost";
        int port = 5000;

        try(DatagramSocket socket = new DatagramSocket()){
            socket.setSoTimeout(3000);
            InetAddress address = InetAddress.getByName(host);

            byte[] sendData = "Hola Servidor PENE UDP".getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            socket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            String reply = new String(receivePacket.getData() , 0 , receivePacket.getLength());
            System.out.println("Respuesta del servidor_cliente_TCPYUDP.servidor: "+ reply);
        }catch (SocketTimeoutException e){
            System.out.println("Socket Timeout");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
