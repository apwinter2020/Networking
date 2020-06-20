package TCP.Client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientMain {
    static String serverIP = "localhost";
    static int serverPort = 9999;

    public static void main(String[] args) throws IOException {
        new ChatClient(serverIP, serverPort).start();
    }
}
