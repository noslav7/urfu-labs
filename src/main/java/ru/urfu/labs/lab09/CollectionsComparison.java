package ru.urfu.labs.lab09;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.TreeSet;

public class CollectionsComparison {

    private static final int VARIANT = 15;
    private static final int ELEMENTS = VARIANT * 1_000_000;
    private static final long INDEX_ELEMENTS = (long) VARIANT * 1_000_000_000L;

    private static final int WARMUP_RUNS = 2;
    private static final int MEASURE_RUNS = 5;

    public static void main(String[] args) {
        System.out.println("Вариант: " + VARIANT);
        System.out.println("Количество элементов коллекции (по заданию): " + ELEMENTS);
        System.out.println("Количество элементов для получения по индексу (по заданию): " + INDEX_ELEMENTS);
        System.out.printf("Прогрев: %d, замеров: %d%n%n", WARMUP_RUNS, MEASURE_RUNS);

        BenchResult arrayDequeResult = benchmarkArrayDeque();
        BenchResult treeSetResult = benchmarkTreeSet();
        BenchResult arrayListResult = benchmarkArrayList();

        printResult("ArrayList", arrayListResult);
        printResult("ArrayDeque", arrayDequeResult);
        printResult("TreeSet", treeSetResult);

        System.out.println("Примечания:");
        System.out.println("- Для ArrayDeque и TreeSet получение по индексу эмулируется проходом итератора.");
        System.out.println("- Для TreeSet операции начала/конца соответствуют минимальному/максимальному элементу.");
        System.out.println("- Для операции получения используется индекс: INDEX_ELEMENTS % size().");
    }

    private static BenchResult benchmarkArrayDeque() {
        ArrayDeque<Integer> deque = prepareArrayDeque(ELEMENTS);
        int middleIndex = deque.size() / 2;
        int indexToGet = (int) (INDEX_ELEMENTS % deque.size());

        return measureAll(
                () -> { deque.addFirst(-1); deque.removeFirst(); },
                () -> { deque.addLast(-1); deque.removeLast(); },
                () -> { insertIntoMiddle(deque, middleIndex, -1); removeFromMiddle(deque, middleIndex); },
                () -> { Integer value = deque.removeFirst(); deque.addFirst(value); },
                () -> { Integer value = deque.removeLast(); deque.addLast(value); },
                () -> { int removed = removeFromMiddle(deque, middleIndex); insertIntoMiddle(deque, middleIndex, removed); },
                () -> getByIndex(deque, indexToGet)
        );
    }

    private static BenchResult benchmarkTreeSet() {
        TreeSet<Integer> set = prepareTreeSet(ELEMENTS);
        int middleValue = ELEMENTS;
        int indexToGet = (int) (INDEX_ELEMENTS % set.size());

        return measureAll(
                () -> { set.add(-1); set.remove(-1); },
                () -> { int value = ELEMENTS * 2 + 1; set.add(value); set.remove(value); },
                () -> { set.add(middleValue); set.remove(middleValue); },
                () -> { Integer value = set.pollFirst(); set.add(value); },
                () -> { Integer value = set.pollLast(); set.add(value); },
                () -> { set.remove(middleValue); set.add(middleValue); },
                () -> getByIndex(set, indexToGet)
        );
    }

    private static BenchResult benchmarkArrayList() {
        ArrayList<Integer> list = prepareArrayList(ELEMENTS);
        int indexToGet = (int) (INDEX_ELEMENTS % list.size());

        return measureAll(
                () -> { list.add(0, -1); list.remove(0); },
                () -> { list.add(-1); list.remove(list.size() - 1); },
                () -> { int middle = list.size() / 2; list.add(middle, -1); list.remove(middle); },
                () -> { Integer value = list.remove(0); list.add(0, value); },
                () -> { Integer value = list.remove(list.size() - 1); list.add(value); },
                () -> { int middle = list.size() / 2; Integer value = list.remove(middle); list.add(middle, value); },
                () -> list.get(indexToGet)
        );
    }

    private static BenchResult measureAll(Runnable... tasks) {
        if (tasks.length != 7) {
            throw new IllegalArgumentException("Expected 7 tasks, got " + tasks.length);
        }
        double[] t = new double[7];
        for (int i = 0; i < 7; i++) {
            t[i] = benchmark(tasks[i]);
        }
        return new BenchResult(t[0], t[1], t[2], t[3], t[4], t[5], t[6]);
    }

    private static double benchmark(Runnable operation) {
        long total = 0L;
        int allRuns = WARMUP_RUNS + MEASURE_RUNS;

        for (int i = 0; i < allRuns; i++) {
            long start = System.nanoTime();
            operation.run();
            long end = System.nanoTime();

            if (i >= WARMUP_RUNS) {
                total += (end - start);
            }
        }

        return total / (double) MEASURE_RUNS / 1_000_000.0;
    }

    private static ArrayDeque<Integer> prepareArrayDeque(int size) {
        ArrayDeque<Integer> deque = new ArrayDeque<>(size);
        for (int i = 0; i < size; i++) {
            deque.addLast(i * 2);
        }
        return deque;
    }

    private static TreeSet<Integer> prepareTreeSet(int size) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < size; i++) {
            set.add(i * 2);
        }
        return set;
    }

    private static ArrayList<Integer> prepareArrayList(int size) {
        ArrayList<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(i * 2);
        }
        return list;
    }

    private static void insertIntoMiddle(ArrayDeque<Integer> deque, int index, int value) {
        ArrayDeque<Integer> left = new ArrayDeque<>(index + 1);
        for (int i = 0; i < index; i++) {
            left.addLast(deque.removeFirst());
        }
        deque.addFirst(value);
        while (!left.isEmpty()) {
            deque.addFirst(left.removeLast());
        }
    }

    private static int removeFromMiddle(ArrayDeque<Integer> deque, int index) {
        ArrayDeque<Integer> left = new ArrayDeque<>(index + 1);
        for (int i = 0; i < index; i++) {
            left.addLast(deque.removeFirst());
        }
        int removed = deque.removeFirst();
        while (!left.isEmpty()) {
            deque.addFirst(left.removeLast());
        }
        return removed;
    }

    private static int getByIndex(Iterable<Integer> iterable, int index) {
        int i = 0;
        for (Integer value : iterable) {
            if (i == index) {
                return value;
            }
            i++;
        }
        throw new IndexOutOfBoundsException("Index out of range: " + index);
    }

    private static void printResult(String collectionName, BenchResult result) {
        System.out.println(collectionName + ":");
        System.out.printf("1) Добавление в начало: %.6f мс%n", result.addAtStartMs);
        System.out.printf("2) Добавление в конец: %.6f мс%n", result.addAtEndMs);
        System.out.printf("3) Добавление в середину: %.6f мс%n", result.addAtMiddleMs);
        System.out.printf("4) Удаление в начале: %.6f мс%n", result.removeAtStartMs);
        System.out.printf("5) Удаление в конце: %.6f мс%n", result.removeAtEndMs);
        System.out.printf("6) Удаление в середине: %.6f мс%n", result.removeAtMiddleMs);
        System.out.printf("7) Получение по индексу: %.6f мс%n%n", result.getByIndexMs);
    }

    private record BenchResult(
            double addAtStartMs,
            double addAtEndMs,
            double addAtMiddleMs,
            double removeAtStartMs,
            double removeAtEndMs,
            double removeAtMiddleMs,
            double getByIndexMs
    ) {
    }

}
