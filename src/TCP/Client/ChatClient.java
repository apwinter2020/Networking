package TCP.Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;

public class ChatClient extends Thread {

    Socket socket;
    ClientTransmitter receiver;
    ClientTransmitter sender;

    ChatClient(String serverIP, int serverPort) throws IOException {
        socket = new Socket(serverIP, serverPort);
        System.out.println("Connected to Server at: " + serverIP + ":" + serverPort);
    }

    @Override
    public void run() {
        try {
            InputStream socketInputStream = socket.getInputStream();
            PrintStream socketPrinter = new PrintStream(socket.getOutputStream());

            receiver = new ClientTransmitter(socketInputStream, System.out);
            sender = new ClientTransmitter(System.in, socketPrinter);

            receiver.start();
            sender.start();

            while (isStillAlive()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignore) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        receiver.interrupt();
    }

    private boolean isStillAlive() {
        return (socket.isConnected() && sender.isAlive());
    }
}
