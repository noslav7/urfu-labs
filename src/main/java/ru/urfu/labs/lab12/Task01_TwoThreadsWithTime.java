package ru.urfu.labs.lab12;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task01_TwoThreadsWithTime {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final long DURATION_MS = 10_000L;

    public static void main(String[] args) {
        Runnable printer = () -> {
            long endTime = System.currentTimeMillis() + DURATION_MS;
            while (System.currentTimeMillis() < endTime) {
                String currentTime = LocalTime.now().format(FORMATTER);
                System.out.println(Thread.currentThread().getName() + " -> " + currentTime);
                try {
                    Thread.sleep(1_000L);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        };

        Thread firstThread = new Thread(printer, "Thread-1");
        Thread secondThread = new Thread(printer, "Thread-2");

        firstThread.start();
        secondThread.start();

        joinThreads(firstThread, secondThread);
    }

    private static void joinThreads(Thread... threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
