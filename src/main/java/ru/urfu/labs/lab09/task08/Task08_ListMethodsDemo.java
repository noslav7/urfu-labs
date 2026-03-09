package ru.urfu.labs.lab09.task08;

import java.util.Scanner;

public class Task08_ListMethodsDemo {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите количество элементов: ");
            int n = scanner.nextInt();
            if (n < 0) {
                System.out.println("Количество не может быть отрицательным.");
                return;
            }

            int[] values = new int[n];
            for (int i = 0; i < n; i++) {
                System.out.print("values[" + i + "] = ");
                values[i] = scanner.nextInt();
            }

            SinglyLinkedListLoop listLoop = new SinglyLinkedListLoop();
            SinglyLinkedListRecursion listRecursion = new SinglyLinkedListRecursion();

            listLoop.createHead(values);
            System.out.println("createHead():      " + listLoop);

            listLoop.createTail(values);
            System.out.println("createTail():      " + listLoop);

            listLoop.AddFirst(100);
            System.out.println("AddFirst(100):     " + listLoop);

            listLoop.AddLast(200);
            System.out.println("AddLast(200):      " + listLoop);

            listLoop.Insert(1, 300);
            System.out.println("Insert(1, 300):    " + listLoop);

            int removedFirst = listLoop.RemoveFirst();
            System.out.println("RemoveFirst()=" + removedFirst + " -> " + listLoop);

            int removedLast = listLoop.RemoveLast();
            System.out.println("RemoveLast()=" + removedLast + "  -> " + listLoop);

            if (n > 0) {
                int removed = listLoop.Remove(0);
                System.out.println("Remove(0)=" + removed + "      -> " + listLoop);
            }

            listRecursion.createHeadRecursion(values);
            System.out.println("createHeadRecursion(): " + listRecursion.toStringRecursion());

            listRecursion.createTailRecursion(values);
            System.out.println("createTailRecursion(): " + listRecursion.toStringRecursion());
        }
    }
}
