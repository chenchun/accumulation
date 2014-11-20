package org.chenchun;

class MinStack {
  private static class Node {
    Node next;
    int val;
    public Node(int val) {
      this.val = val;
    }
  }

  private Node head = new Node(-1);
  private Node min = new Node(-1);

  public void push(int x) {
    Node n = new Node(x);
    n.next = head.next;
    head.next = n;
    if (min.next == null) {
      min.next = new Node(x);
    } else {
      if (x <= min.next.val) {
        Node minNode = new Node(x);
        minNode.next = min.next;
        min.next = minNode;
      }
    }
  }

  public void pop() {
    Node n = head.next;
    if (n != null) {
      head.next = n.next;
      if (n.val == min.next.val) {
        min.next = min.next.next;
      }
    }
  }

  public int top() {
    return head.next == null ? 0 : head.next.val;
  }

  public int getMin() {
    return min.next != null ? min.next.val : 0;
  }
}