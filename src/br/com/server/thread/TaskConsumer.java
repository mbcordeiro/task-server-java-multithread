package br.com.server.thread;

import java.util.concurrent.BlockingQueue;

public class TaskConsumer implements Runnable {
    private BlockingQueue<String> queue;

    public TaskConsumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            String command = null;
            while ((command = queue.take()) != null) {
                System.out.println("Consumer command c3 " + command + Thread.currentThread().getName());
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
