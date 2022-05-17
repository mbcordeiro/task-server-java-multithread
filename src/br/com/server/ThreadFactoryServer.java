package br.com.server;

import br.com.server.exception.ExceptionHandler;

public class ThreadFactoryServer implements java.util.concurrent.ThreadFactory {
    private static int number = 1;

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, "Thread task server " + number);
        number++;
        System.out.println("Call factory thread");
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        return thread;
    }
}
