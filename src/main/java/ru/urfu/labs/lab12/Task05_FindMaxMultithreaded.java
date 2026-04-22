package ru.urfu.labs.lab12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task05_FindMaxMultithreaded {

    public static int findMax(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }

        int threadsCount = Runtime.getRuntime().availableProcessors();
        int actualThreads = Math.min(threadsCount, numbers.length);
        int chunkSize = (int) Math.ceil((double) numbers.length / actualThreads);

        List<Integer> localMaximums = new ArrayList<>();
        List<Thread> workers = new ArrayList<>();

        for (int index = 0; index < actualThreads; index++) {
            int start = index * chunkSize;
            int end = Math.min(start + chunkSize, numbers.length);
            if (start >= end) {
                break;
            }

            Thread worker = new Thread(() -> {
                int localMax = numbers[start];
                for (int i = start + 1; i < end; i++) {
                    if (numbers[i] > localMax) {
                        localMax = numbers[i];
                    }
                }
                synchronized (localMaximums) {
                    localMaximums.add(localMax);
                }
            }, "MaxWorker-" + index);

            workers.add(worker);
            worker.start();
        }

        waitWorkers(workers);

        int max = localMaximums.getFirst();
        for (int value : localMaximums) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] source = {12, -5, 101, 7, 88, 42, 99, 3, 0, 17};
        int max = findMax(source);

        System.out.println("Source array: " + Arrays.toString(source));
        System.out.println("Available CPU cores: " + Runtime.getRuntime().availableProcessors());
        System.out.println("Max element: " + max);
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
