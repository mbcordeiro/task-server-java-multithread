package br.com.server;

import br.com.server.thread.DistributeTasks;
import br.com.server.thread.TaskConsumer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class TaskServer {
    private final ServerSocket server;
    private final ExecutorService threadPool;
    private AtomicBoolean isRunning;

    private BlockingQueue queue;

    public TaskServer() throws IOException {
        System.out.println("--- Server staring... ---");
        this.server = new ServerSocket(8282);
        this.threadPool = Executors.newCachedThreadPool(new ThreadFactoryServer());
        this.isRunning = new AtomicBoolean(true);
        this.queue = new ArrayBlockingQueue(2);
        startConsumer();
    }

    public void run() throws IOException {
        while (isRunning.get()) {
            try {
                Socket socket = server.accept();
                System.out.println("Accepting new client in port" + socket.getPort());
                DistributeTasks distributeTasks = new DistributeTasks(threadPool, socket, this, queue);
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

    private void startConsumer() {
        int consumersQuantity = 2;
        for (int i = 0; i < consumersQuantity; i++) {
            TaskConsumer taskConsumer = new TaskConsumer(queue);
            threadPool.execute(taskConsumer);
        }
    }

    public static void main(String[] args) throws IOException {
        TaskServer server = new TaskServer();
        server.run();
        server.shutdown();
    }
}
