package com.tom.me;

public class SinglyLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int length = 0;

    public void prepend(T value) {
        var newHead = new Node<>(value);
        if (this.length == 0) {
            head = newHead;
            tail = newHead;
            this.length++;
            return;
        }
        this.length++;
        newHead.next = this.head;
        this.head = newHead;
    }

    public void append(T value) {
        var newTail = new Node<>(value);
        if (this.length == 0) {
            head = newTail;
            tail = newTail;
            length++;
            return;
        }
        this.length++;
        this.tail.next = newTail;
        this.tail = newTail;
    }

    public T get(int index) {
        validateIndex(index);
        return seekIndex(index).value();
    }

    public void insert(int index, T value) {
        if (index < 0 || index > this.length) // Allow insert at end of list
            throw new IndexOutOfBoundsException(
                    String.format("Index %d out of bounds for length %d", index, this.length)
            );
        if (index == this.length) {
            append(value);
            return;
        }
        if (index == 0) {
            prepend(value);
            return;
        }
        var newNode = new Node<>(value);
        var prev = seekIndex(index - 1);
        newNode.next = prev.next;
        prev.next = newNode;
        this.length++;
    }

    public T remove(int index) {
        validateIndex(index);
        if (this.length == 1) {
            this.length--;
            var head = this.head;
            this.head = null;
            this.tail = null;
            return head.value();
        }
        if (index == 0) {
            var removed = this.head;
            this.head = this.head.next;
            this.length--;
            return removed.value();
        }
        var prev = seekIndex(index - 1);
        var toRemove = prev.next;
        if (index == this.length - 1) {
            this.tail = prev;
            prev.next = null;
            this.length--;
            return toRemove.value();
        }
        prev.next = toRemove.next;
        this.length--;
        return toRemove.value();
    }

    public int size() {
        return this.length;
    }

    private Node<T> seekIndex(int index) {
        var curr = this.head;
        for(int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    private void validateIndex(int index) {
        if(index < 0 || index >= this.length)
            throw new IndexOutOfBoundsException(
                    String.format("Index %d out of bounds for length %d", index, this.length)
            );
    }

    private static final class Node<T> {
        private final T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }

        T value() {
            return this.value;
        }
    }
}
