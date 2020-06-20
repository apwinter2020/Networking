package TCP.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    static int serverPort = 9999;

    public static void main(String[] args) throws IOException {
        new ChatServer(serverPort).start();
    }
}
