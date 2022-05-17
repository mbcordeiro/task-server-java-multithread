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
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }

        throw new RuntimeException("Exception in command 2");
    }
}
