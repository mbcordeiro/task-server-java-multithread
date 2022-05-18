package br.com.server.thread;

import br.com.server.TaskServer;
import br.com.server.command.CommandC1;
import br.com.server.command.CommandC2CallWS;
import br.com.server.command.CommandC2DBAccess;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class DistributeTasks implements Runnable {
    private final ExecutorService threadPool;
    private final Socket socket;
    private final TaskServer taskServer;

    public DistributeTasks(ExecutorService threadPool, Socket socket, TaskServer taskServer) {
        this.threadPool = threadPool;
        this.socket = socket;
        this.taskServer = taskServer;
    }

    @Override
    public void run() {
        try {
            System.out.println("Distributing tasks to " + socket);
            Scanner scanner = new Scanner(socket.getInputStream());
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            while (scanner.hasNextLine()) {
                String command = scanner.nextLine();
                System.out.println(command);

                switch (command) {
                    case "c1" -> {
                        printStream.println("Confirmation command c1");
                        CommandC1 commandC1 = new CommandC1(printStream);
                        threadPool.execute(commandC1);
                        break;
                    }
                    case "c2" -> {
                        printStream.println("Confirmation command c2");
                        CommandC2CallWS commandC2CallWS = new CommandC2CallWS(printStream);
                        CommandC2DBAccess commandC2DBAccess = new CommandC2DBAccess(printStream);
                        Future<String> futureWS = threadPool.submit(commandC2CallWS);
                        Future<String> futureDB = threadPool.submit(commandC2DBAccess);
                        this.threadPool.submit(new ResultFuture(futureWS, futureDB, printStream));
                        break;
                    }
                    case "shutdown" -> {
                        printStream.println("Shutdown server");
                        taskServer.shutdown();
                        break;
                    }
                    default -> {
                        printStream.println("Command not found");
                        break;
                    }
                }
            }
            scanner.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
