package com.tom.me;

public interface DynamicArray<T> {
    int size();
    T get(int index);
    void set(int index, T value);
    void append(T value);
    void insert(int index, T value);
    T remove(int index);
}
