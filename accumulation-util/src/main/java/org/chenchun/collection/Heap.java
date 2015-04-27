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

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/**
 * The value of all child node is bigger than or equal to that of the parent node.
 * Heap is a complete binary tree.
 *
 * @param <T>
 */
public class Heap<T> {
  private T[] heap;
  private Comparator<T> comparator;
  private int capacity;
  private int size;

  @SuppressWarnings("unchecked")
  public Heap(Comparator<T> comparator, int initialCapacity) {
    this.comparator = comparator;
    this.capacity = initialCapacity;
    this.heap = (T[]) new Object[capacity+1];
  }

  public Heap(Comparator<T> comparator) {
    this(comparator, 8);
  }

  public Heap() {
    this(null, 8);
  }

  public Heap(Collection<T> c) {
    this(null, 8);
    if (c != null) {
      for (T t : c) {
        add(t);
      }
    }
  }

  @SafeVarargs
  public Heap(T...t) {
    this(null, 8);
    if (t != null) {
      for (T e : t) {
        add(e);
      }
    }
  }

  private void upHeap() {
    int s = size, p = s >> 1;
    if (comparator != null) {
      while (p >= 1 && lessThanComparator(s, p)) {
        swap(s, p);
        s = p;
        p = s >> 1;
      }
    } else {
      while (p >= 1 && lessThanComparable(s, p)) {
        swap(s, p);
        s = p;
        p = s >> 1;
      }
    }
  }

  private void downHeap() {
    int i = 1, j = i;
    if (comparator != null) {
      while ((j <<= 1) <= size) {
        if (j+1 <= size && lessThanComparator(j + 1, j)) {
          j = j+1;
        }
        if (lessThanComparator(j, i)) {
          swap(i, j);
          i = j;
        } else {
          break;
        }
      }
    } else {
      while ((j <<= 1) <= size) {
        if (j+1 <= size && lessThanComparable(j + 1, j)) {
          j = j+1;
        }
        if (lessThanComparable(j, i)) {
          swap(i, j);
          i = j;
        } else {
          break;
        }
      }
    }
  }

  private void swap(int i, int j) {
    T t = heap[i];
    heap[i] = heap[j];
    heap[j] = t;
  }

  @SuppressWarnings("unchecked")
  private void grow() {
    //TODO capacity may exceed
    capacity <<= 1;
    this.heap = Arrays.copyOf(this.heap, capacity + 1);
  }

  private boolean lessThanComparator(int i1, int i2) {
    return comparator.compare(heap[i1], heap[i2]) < 0;
  }

  @SuppressWarnings("unchecked")
  private boolean lessThanComparable(int i1, int i2) {
    return ((Comparable)heap[i1]).compareTo(heap[i2]) < 0;
  }

  public void add(T t) {
    if (++size == capacity+1) {
      grow();
    }
    heap[size] = t;
    upHeap();
  }

  public T pop() {
    if (size == 0) {
      return null;
    }
    T t = heap[1];
    heap[1] = heap[size];
    heap[size] = null;
    size--;
    downHeap();
    return t;
  }

  public T head() {
    return size == 0? null : heap[1];
  }

  public int size() {
    return this.size;
  }

  public boolean isEmpty() {
    return this.size == 0;
  }

  @SuppressWarnings("unchecked")
  public boolean equal(T...t) {
    if (t == null || t.length != size) {
      return false;
    }
    if (comparator != null) {
      for (int i = 0; i < size; i++) {
        if (comparator.compare(t[i], heap[i+1]) != 0) {
          return false;
        }
      }
    } else {
      for (int i = 0; i < size; i++) {
        if (((Comparable<T>) heap[i+1]).compareTo(t[i]) != 0) {
          return false;
        }
      }
    }
    return true;
  }

  public int capacity() {
    return this.capacity;
  }

  @SafeVarargs
  public final void addElements(T... t) {
    if (t != null) {
      for (T e : t) {
        add(e);
      }
    }
  }
}
