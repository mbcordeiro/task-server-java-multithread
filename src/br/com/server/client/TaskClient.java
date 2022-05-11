package br.com.server.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TaskClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8882);

        System.out.println("Connection on...");

        Scanner scanner = new Scanner(System.in);

        scanner.nextLine();

        socket.close();
    }
}
