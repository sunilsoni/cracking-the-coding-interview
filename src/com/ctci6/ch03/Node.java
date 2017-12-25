package com.ctci6.ch03;

import java.util.Objects;

public class Node {
    public Node above;
    public Node below;
    public int value;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return value == node.value &&
                Objects.equals(above, node.above) &&
                Objects.equals(below, node.below);
    }

    @Override
    public int hashCode() {

        return Objects.hash(above, below, value);
    }

    @Override
    public String toString() {
        return "Node{" +
                "above=" + above +
                ", below=" + below +
                ", value=" + value +
                '}';
    }
}
