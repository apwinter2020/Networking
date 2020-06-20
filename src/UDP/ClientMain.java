package UDP;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Scanner;

public class ClientMain {
    public static final int maxLength = 10;
    public static String serverIP = "localhost";
    public static int serverPort = 18000;
    public static int clientPort = 19000;

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
        SocketAddress serverAddress = new InetSocketAddress(serverIP,serverPort);
        DatagramSocket datagramSocket = new DatagramSocket(clientPort);

        Scanner scanner = new Scanner(System.in);

        while(true) {
            String message = scanner.nextLine();
            writePacket(datagramSocket, message, serverAddress);
            System.out.println("Client Sent: " + message);

            DatagramPacket packet = readPacket(datagramSocket);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(packet.getData());
            Scanner socketScanner = new Scanner(byteArrayInputStream);
            String response = socketScanner.nextLine();

            System.out.println("Client Received: " + response);

            if(message.equals("exit")) {
                break;
            }
        }
    }
}
