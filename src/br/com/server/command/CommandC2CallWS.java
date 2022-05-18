package br.com.server.command;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

public class CommandC2CallWS implements Callable<String> {
    private final PrintStream printStream;

    public CommandC2CallWS(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Server to receive command c2 - WS");
        printStream.println("Process command c2 - WS");
        Thread.sleep(15000);
        int number = new Random().nextInt(100) + 1;
        System.out.println("Server finish command c2 - WS");
        return Integer.toString(number);
    }
}
