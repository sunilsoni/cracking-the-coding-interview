package com.ctci6.ch03;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * Stack of Plates: Imagine a (literal) stack of plates. If the stack gets too high, it might topple. Therefore, in real life, we would likely start a new stack when the previous stack exceeds some
 * threshold. Implement a data structure SetOfStacks that mimics this. SetOfStacks should be composed of several stacks and should create a new stack once the previous one exceeds capacity.
 * SetOfStacks.push() and SetOfStacks. pop() should behave identically to a single stack (that is, pop() should return the same values as it would if there were just a single stack).
 * <p>
 * <p>FOLLOW UP:</p>
 * <blockquote><pre>
 * Implement a function popAt(int index) which performs a pop operation on a specifc substack
 * </blockquote></pre>
 *
 * @author Sunil
 */
public class SetOfStacks {

    public int capacity;
    ArrayList<Stack> stacks = new ArrayList<Stack>();

    public SetOfStacks(int capacity) {
        this.capacity = capacity;
    }

    public static void main(String[] args) {
        int capacity_per_substack = 5;
        SetOfStacks set = new SetOfStacks(capacity_per_substack);
        for (int i = 0; i < 34; i++) {
            set.push(i);
        }
        for (int i = 0; i < 35; i++) {
            System.out.println("Popped " + set.pop());
        }
    }

    public Stack getLastStack() {
        if (stacks.size() == 0) {
            return null;
        }
        return stacks.get(stacks.size() - 1);
    }

    public void push(int v) {
        Stack last = getLastStack();
        if (last != null && !last.isFull()) { // add to last
            last.push(v);
        } else { // must create new stack
            Stack stack = new Stack(capacity);
            stack.push(v);
            stacks.add(stack);
        }
    }

    public int pop() {
        Stack last = getLastStack();
        if (last == null) throw new EmptyStackException();
        int v = last.pop();
        if (last.size == 0) {
            stacks.remove(stacks.size() - 1);
        }
        return v;
    }

    public int popAt(int index) {
        return leftShift(index, true);
    }

    public int leftShift(int index, boolean removeTop) {
        Stack stack = stacks.get(index);
        int removed_item;
        if (removeTop) removed_item = stack.pop();
        else removed_item = stack.removeBottom();
        if (stack.isEmpty()) {
            stacks.remove(index);
        } else if (stacks.size() > index + 1) {
            int v = leftShift(index + 1, false);
            stack.push(v);
        }
        return removed_item;
    }

    public boolean isEmpty() {
        Stack last = getLastStack();
        return last == null || last.isEmpty();
    }
}
