package br.com.server.thread;

import br.com.server.TaskServer;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class DistributeTasks implements Runnable {
    private final Socket socket;
    private final TaskServer taskServer;

    public DistributeTasks(Socket socket, TaskServer taskServer) {
        this.socket = socket;
        this.taskServer = taskServer;
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
                    case "shuttingdown" -> taskServer.shutdown();
                    default -> printStream.println("Command not found");
                }
            }
            scanner.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
