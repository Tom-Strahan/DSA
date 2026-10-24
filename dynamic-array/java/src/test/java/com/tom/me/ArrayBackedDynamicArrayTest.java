package com.tom.me;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayBackedDynamicArrayTest {

    @Test
    void size() {
        var underTest = new ArrayBackedDynamicArray<Integer>(5);
        for(int i = 0; i < 25; i++) {
            underTest.append(i);
        }
        assertEquals(25, underTest.size());
        for(int i = 0; i < 10; i++) {
            underTest.remove(0);
        }
        assertEquals(15, underTest.size());
    }

    @Test
    void get() {
        var underTest = new ArrayBackedDynamicArray<Integer>(5);
        for(int i = 0; i < 25; i++) {
            underTest.append(i);
        }
        var actual = underTest.get(7);
        assertEquals(7, actual);
    }

    @Test
    void set() {
        var underTest = new ArrayBackedDynamicArray<Integer>(5);
        for(int i = 0; i < 25; i++) {
            underTest.append(i);
        }
        underTest.set(3, 99);
        var actual = underTest.get(3);
        assertEquals(99, actual);
        assertEquals(25, underTest.size());
        assertEquals(4, underTest.get(4));
        assertEquals(2, underTest.get(2));
    }

    @Test
    void append() {
        var underTest = new ArrayBackedDynamicArray<Integer>(5);
        assertEquals(0, underTest.size());
        underTest.append(77);
        assertEquals(77, underTest.get(0));
        assertEquals(1, underTest.size());
        underTest.append(88);
        assertEquals(88, underTest.get(1));
        assertEquals(2, underTest.size());

    }

    @Test
    void insert() {
        var underTest = new ArrayBackedDynamicArray<Integer>(5);
        for(int i = 0; i < 25; i++) {
            underTest.append(i);
        }
        underTest.insert(4, 456);
        assertEquals(26, underTest.size());
        assertEquals(456, underTest.get(4));
        assertEquals(4, underTest.get(5));
        assertEquals(3, underTest.get(3));
    }

    @Test
    void remove() {
        var underTest = new ArrayBackedDynamicArray<Integer>(5);
        for(int i = 0; i < 25; i++) {
            underTest.append(i);
        }
        assertEquals(25, underTest.size());
        assertEquals(4, underTest.get(4));
        var actual = underTest.remove(4);
        assertEquals(24, underTest.size());
        assertEquals(5, underTest.get(4));
        assertEquals(4, actual);
    }

    @Test
    void capacityZeroHandledCorrectly() {
        var underTest = new ArrayBackedDynamicArray<Integer>(0);
        assertEquals(0, underTest.size());
        assertThrows(IndexOutOfBoundsException.class, () -> underTest.get(0));
        underTest.append(7);
        assertEquals(7, underTest.get(0));
        assertEquals(1, underTest.size());
    }

    @Test
    void appendThenInsertPreservesOrder() {
        var underTest = new ArrayBackedDynamicArray<Integer>(5);
        underTest.append(5);
        underTest.insert(0, 8);
        assertEquals(8, underTest.get(0));
        assertEquals(5, underTest.get(1));
    }

    @Test
    void insertAndRemoveAtBoundariesWork() {
        var underTest = new ArrayBackedDynamicArray<Integer>(8);
        for (int i = 0; i < 8; i++) {
            underTest.append(i);
        }
        assertEquals(8, underTest.size());
        var actual = underTest.remove(0);
        assertEquals(0, actual);
        assertEquals(7, underTest.size());
        var next = underTest.remove(6);
        assertEquals(7, next);
        assertEquals(6, underTest.size());
    }

    @Test
    void cantRemoveFromEmptyArray() {
        var underTest = new ArrayBackedDynamicArray<Integer>(0);
        assertEquals(0, underTest.size());
        assertThrows(IndexOutOfBoundsException.class, () -> underTest.remove(0));
        assertEquals(0, underTest.size());
    }

    @Test
    void allowInsertOnEmptyArray() {
        var underTest = new ArrayBackedDynamicArray<Integer>(0);
        underTest.insert(0, 7);
        assertEquals(1, underTest.size());
        assertEquals(7, underTest.get(0));
    }
}