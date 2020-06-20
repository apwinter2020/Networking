package UDP;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class ServerMain {

    public static final int serverPort = 18000;
    public static final int maxLength = 10;

    public static DatagramPacket readPacket(DatagramSocket datagramSocket) throws IOException {
        DatagramPacket datagramPacket = new DatagramPacket(new byte[maxLength], maxLength);
        datagramSocket.receive(datagramPacket);
        return datagramPacket;
    }

    public static void writePacket(DatagramSocket datagramSocket, String message, SocketAddress socketAddress) throws IOException {
        byte[] data = message.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, socketAddress);
        datagramSocket.send(packet);
    }

    public static void main(String[] args) throws IOException {

        DatagramSocket datagramSocket = new DatagramSocket(serverPort);

        while(true) {
            DatagramPacket packet = readPacket(datagramSocket);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(packet.getData());
            Scanner scanner = new Scanner(byteArrayInputStream);
            String message = scanner.nextLine();
            System.out.println("Server Received: " + message);

            String result = message.toUpperCase();
            writePacket(datagramSocket, result, packet.getSocketAddress());
            System.out.println("Server Sent: " + result);

            if(message.equals("exit")) {
                break;
            }
        }
    }
}
