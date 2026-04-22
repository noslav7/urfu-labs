package ru.urfu.labs.lab12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Task06_SumMultithreaded {

    public static long sum(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0L;
        }

        int threadsCount = Runtime.getRuntime().availableProcessors();
        int actualThreads = Math.min(threadsCount, numbers.length);
        int chunkSize = (int) Math.ceil((double) numbers.length / actualThreads);

        AtomicLong total = new AtomicLong(0L);
        List<Thread> workers = new ArrayList<>();

        for (int index = 0; index < actualThreads; index++) {
            int start = index * chunkSize;
            int end = Math.min(start + chunkSize, numbers.length);
            if (start >= end) {
                break;
            }

            Thread worker = new Thread(() -> {
                long localSum = 0L;
                for (int i = start; i < end; i++) {
                    localSum += numbers[i];
                }
                total.addAndGet(localSum);
            }, "SumWorker-" + index);

            workers.add(worker);
            worker.start();
        }

        waitWorkers(workers);
        return total.get();
    }

    public static void main(String[] args) {
        int[] source = {10, 20, -5, 7, 33, 18, 2, -10, 5, 100};
        long result = sum(source);

        System.out.println("Source array: " + Arrays.toString(source));
        System.out.println("Available CPU cores: " + Runtime.getRuntime().availableProcessors());
        System.out.println("Sum of elements: " + result);
    }

    private static void waitWorkers(List<Thread> workers) {
        for (Thread worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
