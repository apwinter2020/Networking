package TCP.Client;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ClientTransmitter extends Thread {

    InputStream inputStream;
    PrintStream printStream;

    ClientTransmitter(InputStream inputStream, PrintStream printStream) {
        this.inputStream = inputStream;
        this.printStream = printStream;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(inputStream);

        while(!isInterrupted()) {
            String message = scanner.nextLine();
            System.out.println("Received: " + message);

            if(message.equals("exit")) {
                break;
            }

            printStream.println(message);
            System.out.println("Sent: " + message);
        }
    }
}
