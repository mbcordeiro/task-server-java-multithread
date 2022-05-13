package br.com.server.thread;

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
            while (scanner.hasNextLine()) {
                String command = scanner.nextLine();
                System.out.println(command);
            }
            scanner.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
