package br.com.server;

import br.com.server.thread.DistributeTasks;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskServer {
    public static void main(String[] args) throws IOException {
        int port = 8282;
        System.out.println("--- Server staring... ---");

        ServerSocket server = new ServerSocket(port);

        ExecutorService threadPool = Executors.newCachedThreadPool();

        while (true) {
            Socket socket = server.accept();
            System.out.println("Accepting new client in port" + socket.getPort());

            DistributeTasks distributeTasks = new DistributeTasks(socket);
            threadPool.execute(distributeTasks);
        }
    }
}
