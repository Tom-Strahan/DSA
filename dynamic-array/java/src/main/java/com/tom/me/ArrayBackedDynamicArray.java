package com.tom.me;

public class ArrayBackedDynamicArray<T> implements DynamicArray<T> {

    private int size;
    private Object[] elements;

    public ArrayBackedDynamicArray(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Capacity must be >= 0");
        this.elements = new Object[initialCapacity];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        @SuppressWarnings("unchecked")
        T value = (T) this.elements[index];
        return value;
    }

    @Override
    public void set(int index, T value) {
        validateIndex(index);
        this.elements[index] = value;
    }

    @Override
    public void append(T value) {
        ensureCapacity();
        this.elements[this.size] = value;
        this.size++;
    }

    @Override
    public void insert(int index, T value) {
        if (index > this.size || index < 0) // Allow inserts at the end (index == size).
            throw new IndexOutOfBoundsException(
                    String.format("Cannot insert at index %d with length %d", index, this.size));
        ensureCapacity();
        // Shift up from last element (size - 1) to the insertion index inclusive.
        for (int i = this.size - 1; i >= index; i--) {
            this.elements[i+1] = this.elements[i];
        }
        this.elements[index] = value;
        this.size++;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        @SuppressWarnings("unchecked")
        T removed = (T) this.elements[index];
        // Shift all elements from removal index to the last logical element (size - 1) down one.
        for (int i = index; i < this.size - 1; i++) {
            this.elements[i] = this.elements[i+1];
        }
        this.size--;
        this.elements[this.size] = null; // Clears reference so object can be garbage collected.
        return removed;
    }

    private void validateIndex(int index) {
        if(index >= this.size || index < 0)
            throw new IndexOutOfBoundsException(
                    String.format("Index %d is out of bounds for length %d", index, this.size));
    }

    private void ensureCapacity() {
        if (this.size < elements.length) return;
        if (elements.length == 0) {
            this.elements = new Object[1];
            return;
        }
        int newCapacity = this.elements.length * 2;
        Object[] nextArray = new Object[newCapacity];
        System.arraycopy(this.elements, 0, nextArray, 0, this.elements.length);
        this.elements = nextArray;
    }
}
