package br.com.server.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class TaskClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 8882);

        System.out.println("Connection on...");
        Thread threadSendCommandServer = new Thread(() -> {
            try {
                PrintStream printStream = new PrintStream(socket.getOutputStream());
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.trim().equals("")) break;
                    printStream.println(line);
                }
                printStream.close();
                scanner.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Thread threadResponseServer = new Thread(() -> {
            System.out.println("Getting data from the server...");
            try {
                Scanner inputStream = new Scanner(socket.getInputStream());
                while (inputStream.hasNextLine()) {
                    String line = inputStream.nextLine();
                    System.out.println(line);
                }
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        threadSendCommandServer.start();
        threadResponseServer.start();

        threadSendCommandServer.join();

        System.out.println("Closing client socket...");
        socket.close();
    }
}
