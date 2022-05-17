package br.com.server.command;

import java.io.PrintStream;

public class CommandC1 implements Runnable {
    private final PrintStream printStream;

    public CommandC1(PrintStream printStream) {
        this.printStream = printStream;
    }


    @Override
    public void run() {
        System.out.println("Execute command c1");

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }

        printStream.println("Command c1 execute success!");
    }
}
