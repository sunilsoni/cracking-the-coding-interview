package com.ctci6.ch03;

import java.util.EmptyStackException;
import java.util.Objects;

public class Stack {
    public Node top;
    public Node bottom;
    public int size = 0;
    private int capacity;

    public Stack(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFull() {
        return capacity == size;
    }

    public void join(Node above, Node below) {
        if (below != null) below.above = above;
        if (above != null) above.below = below;
    }

    public boolean push(int v) {
        if (size >= capacity) return false;
        size++;
        Node n = new Node(v);
        if (size == 1) bottom = n;
        join(n, top);
        top = n;
        return true;
    }

    public int pop() {
        if (top == null) throw new EmptyStackException();
        Node t = top;
        top = top.below;
        size--;
        return t.value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int removeBottom() {
        Node b = bottom;
        bottom = bottom.above;
        if (bottom != null) bottom.below = null;
        size--;
        return b.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stack stack = (Stack) o;
        return size == stack.size &&
                capacity == stack.capacity &&
                Objects.equals(top, stack.top) &&
                Objects.equals(bottom, stack.bottom);
    }

    @Override
    public int hashCode() {

        return Objects.hash(top, bottom, size, capacity);
    }

    @Override
    public String toString() {
        return "Stack{" +
                "top=" + top +
                ", bottom=" + bottom +
                ", size=" + size +
                ", capacity=" + capacity +
                '}';
    }
}

