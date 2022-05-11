package br.com.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TaskServer {
    public static void main(String[] args) throws IOException {
        int port = 8282;
        System.out.println("--- Server staring... ---");

        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Accepting new client in port" + socket.getPort());
        }
    }
}
