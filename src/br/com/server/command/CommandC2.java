package br.com.server.command;

import java.io.PrintStream;

public class CommandC2 implements Runnable {
    private final PrintStream printStream;

    public CommandC2(PrintStream printStream) {
        this.printStream = printStream;
    }


    @Override
    public void run() {
        System.out.println("Execute command c2");

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }

        printStream.println("Command c2 execute success!");
    }
}
