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
package org.chenchun.collection;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

public class SkipList<K, V> {

  private final static int MAX_LEVEL = 10;
  private int maxLevel;

  private LinkedList<Level<K>> levels;

  private HashMap<K, V> hashMap;

  private static class Level<K> {
    private Cell<K> head, tail;
    private int size;

    public void addLast(Cell<K> c) {
      Cell.connectLevel(tail, c);
      tail = c;
      size++;
    }

    public Level(Cell<K> c) {
      this.head = c;
      this.tail = c;
    }

    public String toString() {
      StringBuilder sb = new StringBuilder();
      Cell<K> c = this.head.next;
      while (c != null) {
        sb.append(c.k).append(" ");
        c = c.next;
      }
      return sb.toString();
    }
  }

  private static class Cell<K> {
    private Cell<K> next;
    private Cell<K> below;
    private K k;

    private Cell() {
    }

    private Cell(K k) {
      this.k = k;
    }

    private static void connectNode(Cell parent, Cell child) {
      parent.below = child;
    }

    private static void connectLevel(Cell parent, Cell child) {
      parent.next = child;
    }

    public boolean biggerThan(K k) {
      return compareTo(k) > 0;
    }

    @SuppressWarnings("unchecked")
    public int compareTo(K k) {
      return ((Comparable<K>) this.k).compareTo(k);
    }
  }

  public SkipList(HashMap<K, V> map) {
    this(new TreeMap<>(map));
  }

  public SkipList(SortedMap<K, V> map) {
    this.hashMap = new HashMap<>(map);
    this.levels = new LinkedList<>();
    int i = 0;
    Cell<K> belowLevelHead = null;
    for (Map.Entry<K, V> entry : map.entrySet()) {
      int level = randomLevel();
      Cell<K> child = null;
      for (int j = 0; j <= level; j++) {
        //prev is the last element in the same level
        Level<K> l;
        if (levels.size() <= j) {
          Cell<K> levelHead = new Cell<>();
          l = new Level<>(levelHead);
          Cell.connectNode(levelHead, belowLevelHead);
          belowLevelHead = levelHead;
          levels.add(l);
        } else {
          l = levels.get(j);
        }
        Cell<K> cell = new Cell<>(entry.getKey());
        l.addLast(cell);
        Cell.connectNode(cell, child);
        child = cell;
      }
      System.out.println(this);
      i++;
    }
    maxLevel = levels.size();
  }

  public V get(K k) {
    Cell<K> head = levels.get(levels.size()-1).head;
    for (int i = 0; i < levels.size(); i++) {
      while (head.next != null && !head.next.biggerThan(k)) {
        head = head.next;
      }
      if (head.below != null) {
        head = head.below;
      } else {
        break;
      }
    }
    if (head.k != null && head.k.equals(k)) {
      return hashMap.get(k);
    }
    return null;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    int i = 0;
    while (i < levels.size()) {
      Cell<K> c = levels.get(i).head.next;
      while (c != null) {
        sb.append(c.k).append(" ");
        c = c.next;
      }
      sb.append("\n");
      i+=1;
    }
    return sb.toString();
  }

  private int randomLevel() {
    return ThreadLocalRandom.current().nextInt(0, MAX_LEVEL);
  }

  public static void main(String[] args) {
    HashMap<Integer, Integer> map = new HashMap<>();
    int i = 0;
    while (i < 20) {
      map.put(i, i);
      i++;
    }
    SkipList<Integer, Integer> list = new SkipList<>(map);
    System.out.println(list);
    i = 0;
    while (i < 21) {
      System.out.println(i + " " + list.get(i));
      i++;
    }
  }
}
