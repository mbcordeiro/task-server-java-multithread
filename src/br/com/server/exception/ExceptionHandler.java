package br.com.server.exception;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        System.out.println("Exception in thread " + thread.getName() + ", " + ex.getMessage());
    }
}
