package br.com.server;

import br.com.server.thread.DistributeTasks;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TaskServer {
    public static void main(String[] args) throws IOException {
        int port = 8282;
        System.out.println("--- Server staring... ---");

        ServerSocket server = new ServerSocket(port);

        while (true) {
            Socket socket = server.accept();
            System.out.println("Accepting new client in port" + socket.getPort());

            DistributeTasks distributeTasks = new DistributeTasks(socket);
            Thread thread = new Thread(distributeTasks);
            thread.start();
        }
    }
}
