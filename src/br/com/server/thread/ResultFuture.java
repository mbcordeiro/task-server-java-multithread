package br.com.server.thread;

import java.io.PrintStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ResultFuture implements Runnable {
    private Future<String> futureWS;
    private Future<String> futureDB;
    private PrintStream printStream;

    public ResultFuture(Future<String> futureWS, Future<String> futureDB, PrintStream printStream) {
        this.futureWS = futureWS;
        this.futureDB = futureDB;
        this.printStream = printStream;
    }

    @Override
    public void run() {
        System.out.println("Waitning result features...");

        try {
            String resultFutureWS = futureWS.get(20, TimeUnit.SECONDS);
            String resultFutureDB = futureDB.get(20, TimeUnit.SECONDS);
            printStream.println("Result command c2 : " + resultFutureWS + ", " + resultFutureDB);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            printStream.println("Timeout execution command c2");
            System.out.println("Cancel exectuion command c2");
            futureDB.cancel(true);
            futureWS.cancel(true);
        }

        System.out.println("Finish execution");
    }
}
