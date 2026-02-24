package ru.urfu.labs.lab09.task05;

/**
 * Проект 2: создание однонаправленного списка с хвоста.
 */
public class Project02_ListFromTail {
    public static void main(String[] args) {
        Node head = new Node(0, null);
        Node tail = head;

        // Добавляем элементы в конец: поддерживаем ссылку на хвост.
        for (int i = 1; i < 4; i++) {
            Node newTail = new Node(i, null);
            tail.next = newTail;
            tail = newTail;
        }

        printList(head);
    }

    private static void printList(Node head) {
        Node ref = head;
        while (ref != null) {
            System.out.print(" " + ref.value);
            ref = ref.next;
        }
        System.out.println();
    }
}
