package TCP.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer extends Thread {
    ArrayList<ClientHandler> clients;
    ServerSocket serverSocket;

    ChatServer(int serverPort) throws IOException {
        serverSocket = new ServerSocket(serverPort);
        clients = new ArrayList<>();
        System.out.println("Server Started at: " + serverPort);
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(this, socket);
                clients.add(clientHandler);
                System.out.println("Client at: " + socket.getRemoteSocketAddress().toString() + " is connected.");

                clientHandler.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sentToAll(ClientHandler client, String message) {
        System.out.println("Sending " + message + " to all clients.");
        for(ClientHandler clientHandler: clients) {
            if(clientHandler.isAlive() && client != clientHandler)
                clientHandler.send(message);
        }
        System.out.println(message + " is sent to all clients.");
    }
}
