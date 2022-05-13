package br.com.server.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class TaskClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8882);

        System.out.println("Connection on...");

        PrintStream printStream = new PrintStream(socket.getOutputStream());
        printStream.println("Commnad 1");

        Scanner scanner = new Scanner(System.in);

        scanner.nextLine();

        socket.close();
        printStream.close();
        scanner.close();
    }
}
