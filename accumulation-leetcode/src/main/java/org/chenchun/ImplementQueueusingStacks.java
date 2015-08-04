package org.chenchun;

import java.util.Stack;

public class ImplementQueueusingStacks {
    private Stack<Integer> stack = new Stack<>(), top = new Stack<>();
    // Push element x to the back of queue.
    public void push(int x) {
        stack.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        if (top.isEmpty()) {
            while (!stack.isEmpty()) {
                top.push(stack.pop());
            }
        }
        top.pop();
    }

    // Get the front element.
    public int peek() {
        if (top.isEmpty()) {
            while (!stack.isEmpty()) {
                top.push(stack.pop());
            }
        }
        return top.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return stack.isEmpty() && top.isEmpty();
    }
}
