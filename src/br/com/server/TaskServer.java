package br.com.server;

import br.com.server.thread.DistributeTasks;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class TaskServer {
    private final ServerSocket server;
    private final ExecutorService threadPool;
    private AtomicBoolean isRunning;

    public TaskServer() throws IOException {
        System.out.println("--- Server staring... ---");
        this.server = new ServerSocket(8282);
        this.threadPool = Executors.newFixedThreadPool(4, new ThreadFactoryServer());
        this.isRunning = new AtomicBoolean(true);
    }

    public void run() throws IOException {
        while (isRunning.get()) {
            try {
                Socket socket = server.accept();
                System.out.println("Accepting new client in port" + socket.getPort());
                DistributeTasks distributeTasks = new DistributeTasks(threadPool, socket, this);
                threadPool.execute(distributeTasks);
            } catch (SocketException e) {
                System.out.println(e.getMessage() + " isRunning=" + isRunning);
            }
        }
    }

    public void shutdown() throws IOException {
        System.out.println("Shutting down the server");
        isRunning = new AtomicBoolean(false);
        server.close();
        threadPool.shutdown();
    }

    public static void main(String[] args) throws IOException {
        TaskServer server = new TaskServer();
        server.run();
        server.shutdown();
    }
}
