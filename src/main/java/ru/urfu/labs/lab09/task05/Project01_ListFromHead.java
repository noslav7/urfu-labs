package ru.urfu.labs.lab09.task05;

/**
 * Проект 1: создание однонаправленного списка с головы.
 */
public class Project01_ListFromHead {
    public static void main(String[] args) {
        Node head = null;

        // Добавляем элементы в начало: новый узел становится новой головой.
        for (int i = 0; i < 4; i++) {
            head = new Node(i, head);
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
