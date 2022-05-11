package br.com.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TaskServer {
    public static void main(String[] args) throws IOException {
        System.out.println("--- Server staring... ---");
        ServerSocket serverSocket = new ServerSocket(8882);

        Socket socket = serverSocket.accept();
    }
}
