package ru.urfu.labs.lab09.task07;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Task07_JosephusComparison {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите N для сравнения: ");
            int n = scanner.nextInt();
            if (n <= 0) {
                System.out.println("N должно быть больше 0.");
                return;
            }

            long startArray = System.nanoTime();
            int a = solveArrayList(n);
            long timeArray = System.nanoTime() - startArray;

            long startLinked = System.nanoTime();
            int b = solveLinkedList(n);
            long timeLinked = System.nanoTime() - startLinked;

            System.out.println("Победитель (ArrayList): " + a + ", время: " + timeArray + " нс");
            System.out.println("Победитель (LinkedList): " + b + ", время: " + timeLinked + " нс");
            System.out.println(timeArray < timeLinked
                    ? "Быстрее ArrayList (в этом запуске)."
                    : "Быстрее LinkedList (в этом запуске).");
        }
    }

    private static int solveArrayList(int n) {
        List<Integer> people = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            people.add(i);
        }

        int index = 0;
        while (people.size() > 1) {
            index = (index + 1) % people.size();
            people.remove(index);
        }
        return people.get(0);
    }

    private static int solveLinkedList(int n) {
        LinkedList<Integer> people = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            people.add(i);
        }

        ListIterator<Integer> iterator = people.listIterator();
        while (people.size() > 1) {
            if (!iterator.hasNext()) {
                iterator = people.listIterator();
            }
            iterator.next();

            if (!iterator.hasNext()) {
                iterator = people.listIterator();
            }
            iterator.next();
            iterator.remove();
        }
        return people.getFirst();
    }
}
