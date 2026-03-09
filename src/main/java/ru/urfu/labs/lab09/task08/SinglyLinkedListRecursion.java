package ru.urfu.labs.lab09.task08;

public class SinglyLinkedListRecursion {
    private Node head;

    public void createHeadRecursion(int[] values) {
        clear();
        createHeadRecursion(values, 0);
    }

    private void createHeadRecursion(int[] values, int index) {
        if (index == values.length) {
            return;
        }
        head = new Node(values[index], head);
        createHeadRecursion(values, index + 1);
    }

    public void createTailRecursion(int[] values) {
        clear();
        head = createTailRecursion(values, 0);
    }

    private Node createTailRecursion(int[] values, int index) {
        if (index == values.length) {
            return null;
        }
        return new Node(values[index], createTailRecursion(values, index + 1));
    }

    public String toStringRecursion() {
        return toStringRecursion(head).trim();
    }

    private String toStringRecursion(Node node) {
        if (node == null) {
            return "";
        }
        return node.value + " " + toStringRecursion(node.next);
    }

    private void clear() {
        head = null;
    }
}
