/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.chenchun;

import java.util.HashMap;

public class LRUCache {
  private class Node {
    Node pre;
    Node next;
    int key;
    int val;

    public Node(int k, int v) {
      this.key = k;
      this.val = v;
    }
//    public int hashcode() {
//      return key + val*31;
//    }
//    public boolean equals(Node n) {
//      return this == n || (this.val == n.val && this.key == n.key);
//    }

    private void insertChild(Node node) {
      if (this.next != null) {
        this.next.pre = node;
      }
      node.next = this.next;
      node.pre = this;
      this.next = node;
    }

    private void deleteFromTree() {
      if (this.pre != null) {
        this.pre.next = this.next;
      }
      if (this.next != null) {
        this.next.pre = this.pre;
      }
      this.next = null;
      this.pre = null;
    }

    private Node deleteLast() {
      Node n = this;
      while (n.next != null) {
        n = n.next;
      }
      if (n != this) {
        n.deleteFromTree();
        return n;
      }
      return null;
    }
  }

  private Node root = new Node(0, 0);
  private HashMap<Integer, Node> map = new HashMap<>();
  private int capacity;

  public LRUCache(int capacity) {
    this.capacity = capacity;
  }

  public int get(int key) {
    if (!map.containsKey(key)) {
      return -1;
    } else {
      Node node = map.get(key);
      node.deleteFromTree();
      root.insertChild(node);
      return node.val;
    }
  }

  public void set(int key, int value) {
    if (map.containsKey(key)) {
      Node n = map.get(key);
      n.deleteFromTree();
      n.val = value;
      this.root.insertChild(n);
    } else {
      if (map.size() == this.capacity) {
        Node last = this.root.deleteLast();
        if (last != null) {
          map.remove(last.key);
        }
      }
      Node n = new Node(key, value);
      map.put(key, n);
      this.root.insertChild(n);
    }
  }
}
