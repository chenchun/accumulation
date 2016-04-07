/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.chenchun.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Radix<T> {
  private Node<T> root;
  private int size;

  public Radix() {
    this.root = new Node<T>();
    this.size = 0;
  }

  private static class Node<T> {
    String prefix;
    Map<Character, Node<T>> children;

    LeafData<T> leafData;

    private Node() {
      this("", null);
    }

    /**
     * Create a new Node
     *
     * @param key
     * @param t null if node is not a leaf node, otherwise the value
     */
    public Node(String key, T t) {
      this.prefix = key;
      this.children = new HashMap<>();
      this.leafData = new LeafData<T>();
      if (t != null) {
        this.leafData.add(t);
      }
    }

    private Node<T> getChild(Character i) {
      return this.children.get(i);
    }

    private void addChild(Node<T> child) {
      this.children.put(child.prefix.charAt(0), child);
    }

    private void replaceChild(Node<T> child) {
      this.children.put(child.prefix.charAt(0), child);
    }
  }

  private static class LeafData<T> {
    int num;
    List<T> data;

    public LeafData() {
      this.data = new ArrayList<>();
    }

    public LeafData<T> add(T t) {
      this.data.add(t);
      this.num++;
      return this;
    }
  }

  public void insert(String key, T t) {
    Node<T> node = root;
    String k = key;
    this.size++;
    while (true) {
      if (k.length() == 0) {
        node.leafData.add(t);
        return;
      }
      Node<T> parent = node;
      node = node.getChild(k.charAt(0));
      if (node == null) {
        parent.addChild(new Node<T>(k, t));
        return;
      }
      int commonLen = longestCommonPrefix(node.prefix, k);
      if (commonLen == node.prefix.length()) {
        if (k.length() == node.prefix.length()) {
          node.leafData.add(t);
          return;
        } else {
          k = k.substring(node.prefix.length());
          continue;
        }
      }
      // split node           parent
      //   parent               |
      //     |        ->      child
      //    node                |
      //                       node
      //
      // insert child node between parent and node
      Node<T> child = new Node<T>(k.substring(0, commonLen), null);
      parent.replaceChild(child);
      node.prefix = node.prefix.substring(commonLen);
      child.addChild(node);
      k = k.substring(commonLen);
      if (k.length() == 0) {
        // if k is empty now, add a leaf at this node
        child.leafData.add(t);
        return;
      }
      // if k is not empty, add a node
      child.addChild(new Node<T>(k, t));
      return;
    }
  }

  public boolean contains(String key) {
    return search(key) != null;
  }

  public List<T> search(String key) {
    Node<T> node = root;
    String k = key;
    while (true) {
      if (k.length() == 0) {
        if (node.leafData.data.size() != 0) {
          return node.leafData.data;
        }
        return null;
      }
      Node<T> child = node.getChild(k.charAt(0));
      if (child == null) {
        return null;
      }
      node = child;
      if (k.startsWith(node.prefix)) {
        k = k.substring(node.prefix.length());
        continue;
      }
      return null;
    }
  }


  /**
   * @param str1
   * @param str2
   * @return the length of the longest common prefix
   */
  public int longestCommonPrefix(String str1, String str2) {
    int m = Math.min(str1.length(), str2.length());
    for (int i = 0; i < m; i++) {
      if (str1.charAt(i) != str2.charAt(i)) {
        return i;
      }
    }
    return m;
  }

  public int size() {
    return this.size;
  }
}
