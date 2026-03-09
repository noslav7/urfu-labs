package ru.urfu.labs.lab09.task08;

public class SinglyLinkedListLoop {
    private Node head;

    public void createHead(int[] values) {
        clear();
        for (int value : values) {
            AddFirst(value);
        }
    }

    public void createTail(int[] values) {
        clear();
        for (int value : values) {
            AddLast(value);
        }
    }

    public void AddFirst(int value) {
        head = new Node(value, head);
    }

    public void AddLast(int value) {
        Node newNode = new Node(value, null);
        if (head == null) {
            head = newNode;
            return;
        }

        Node ref = head;
        while (ref.next != null) {
            ref = ref.next;
        }
        ref.next = newNode;
    }

    public void Insert(int index, int value) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("index=" + index);
        }
        if (index == 0) {
            AddFirst(value);
            return;
        }

        Node prev = nodeAt(index - 1);
        prev.next = new Node(value, prev.next);
    }

    public int RemoveFirst() {
        ensureNotEmpty();
        int removed = head.value;
        head = head.next;
        return removed;
    }

    public int RemoveLast() {
        ensureNotEmpty();
        if (head.next == null) {
            int removed = head.value;
            head = null;
            return removed;
        }

        Node prev = head;
        while (prev.next.next != null) {
            prev = prev.next;
        }

        int removed = prev.next.value;
        prev.next = null;
        return removed;
    }

    public int Remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("index=" + index);
        }
        if (index == 0) {
            return RemoveFirst();
        }

        Node prev = nodeAt(index - 1);
        int removed = prev.next.value;
        prev.next = prev.next.next;
        return removed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node ref = head;
        while (ref != null) {
            if (!sb.isEmpty()) {
                sb.append(" ");
            }
            sb.append(ref.value);
            ref = ref.next;
        }
        return sb.toString();
    }

    public void clear() {
        head = null;
    }

    private int size() {
        int count = 0;
        Node ref = head;
        while (ref != null) {
            count++;
            ref = ref.next;
        }
        return count;
    }

    private Node nodeAt(int index) {
        Node ref = head;
        int i = 0;
        while (i < index) {
            ref = ref.next;
            i++;
        }
        return ref;
    }

    private void ensureNotEmpty() {
        if (head == null) {
            throw new IllegalStateException("Список пуст");
        }
    }
}
