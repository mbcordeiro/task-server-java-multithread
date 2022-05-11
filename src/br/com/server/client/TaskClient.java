package br.com.server.client;

import java.io.IOException;
import java.net.Socket;

public class TaskClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8882);

        System.out.println("Connection on...");

        socket.close();
    }
}
