package br.com.server.thread;

import java.net.Socket;

public class DistributeTasks implements Runnable {
    private Socket socket;

    public DistributeTasks(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Distributing tasks to " + socket);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
