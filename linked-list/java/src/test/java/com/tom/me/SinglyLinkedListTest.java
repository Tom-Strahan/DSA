package com.tom.me;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {

    @Test
    void prepend() {
        var underTest = new SinglyLinkedList<Integer>();
        for (int i = 0; i < 5; i++) {
            underTest.prepend(i);
        }
        assertEquals(4, underTest.get(0));
        assertEquals(3, underTest.get(1));
        assertEquals(2, underTest.get(2));
        assertEquals(1, underTest.get(3));
        assertEquals(0, underTest.get(4));
    }

    @Test
    void append() {
        var underTest = new SinglyLinkedList<Integer>();
        for (int i = 0; i < 5; i++) {
            underTest.append(i);
        }
        assertEquals(0, underTest.get(0));
        assertEquals(1, underTest.get(1));
        assertEquals(2, underTest.get(2));
        assertEquals(3, underTest.get(3));
        assertEquals(4, underTest.get(4));
    }

    @Test
    void get() {
        var underTest = new SinglyLinkedList<Integer>();
        for (int i = 0; i < 5; i++) {
            underTest.append(i);
        }
        assertEquals(2, underTest.get(2));
    }

    @Test
    void insert() {
        var underTest = new SinglyLinkedList<Integer>();
        for (int i = 0; i < 5; i++) {
            underTest.insert(i, 5-i);
        }
        assertEquals(5, underTest.get(0));
        assertEquals(4, underTest.get(1));
        assertEquals(3, underTest.get(2));
        assertEquals(2, underTest.get(3));
        assertEquals(1, underTest.get(4));
    }

    @Test
    void remove() {
        var underTest = new SinglyLinkedList<Integer>();
        for (int i = 0; i < 5; i++) {
            underTest.append(i);
        }
        var actual = underTest.remove(2);
        assertEquals(2, actual);

        assertEquals(0, underTest.get(0));
        assertEquals(1, underTest.get(1));
        assertEquals(3, underTest.get(2));
        assertEquals(4, underTest.get(3));
    }

    @Test
    void size() {
        var underTest = new SinglyLinkedList<Integer>();
        for (int i = 0; i < 5; i++) {
            assertEquals(i, underTest.size());
            underTest.append(i);
            assertEquals(i + 1, underTest.size());
        }
        for (int i = 0; i < 5; i++) {
            assertEquals(5-i, underTest.size());
            underTest.remove(0);
            assertEquals(5-i-1, underTest.size());
        }
    }
}