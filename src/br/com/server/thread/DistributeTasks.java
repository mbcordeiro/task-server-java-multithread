package br.com.server.thread;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class DistributeTasks implements Runnable {
    private final Socket socket;

    public DistributeTasks(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Distributing tasks to " + socket);
            Scanner scanner = new Scanner(socket.getInputStream());
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            while (scanner.hasNextLine()) {
                String command = scanner.nextLine();
                System.out.println(command);

                switch (command) {
                    case "c1" -> printStream.println("Confirmation command c1");
                    case "c2" -> printStream.println("Confirmation command c2");
                    default -> printStream.println("Command not found");
                }
            }
            scanner.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
