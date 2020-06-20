package TCP.Server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler extends Thread {
    Socket socket;
    ChatServer chatServer;
    String clientName;
    PrintStream printer;

    ClientHandler(ChatServer chatServer, Socket socket) {
        this.chatServer = chatServer;
        this.socket = socket;
        clientName = socket.getRemoteSocketAddress().toString();
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(socket.getInputStream());
            printer = new PrintStream(socket.getOutputStream());

            while(true) {
                String message = clientName + ": " + scanner.nextLine();
                System.out.println("Client at " + socket.getRemoteSocketAddress().toString() + " sent " + message + ".");

                chatServer.sentToAll(this, message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String message) {
        printer.println(message);
    }
}
