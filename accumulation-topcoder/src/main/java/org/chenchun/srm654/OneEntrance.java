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
package org.chenchun.srm654;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class OneEntrance {
  private static class Node {
    Set<Integer> nexts = new HashSet<>();
    Integer index;
    int lineage;
    int level;
//           1
//         /   \
//       10     11
//      /  \    |  \
//    100 101  110 111
//     /
//   1000
//    11 != (100 >> 1)
//    11 != (101 >> 1)
//    11 != (1000 >> 2)
//    10 == (1000 >> 2)

  }

  int count(int[] a, int[] b, int s) {
    if (a.length == 0) {
      return 1;
    }
    int n = a.length + 1, total = 1;
    Node[] nodes = new Node[n];
    for (int i = 0; i < n; i++) {
      nodes[i] = new Node();
      nodes[i].index = i;
    }
    for (int i = 0; i < n-1; i++) {
      total *= (i+1);
      nodes[a[i]].nexts.add(b[i]);
      nodes[b[i]].nexts.add(a[i]);
    }
    Queue<Integer> queue = new LinkedList<>();
    queue.add(s);
    nodes[s].lineage = 1;
    nodes[s].level = 1;
    while (queue.size() > 0) {
      int parent = queue.poll();
      Set<Integer> children = nodes[parent].nexts;
      int count = 0;
      for (Integer i : children) {
        nodes[i].nexts.remove(parent);
        nodes[i].level = nodes[parent].level + 1;
        nodes[i].lineage = (nodes[parent].lineage << 1) + count;
        count++;
        queue.add(i);
      }
    }
    int[] permutation = new int[n-1];
    int ret = 0;
    while (total > 0) {
      init(permutation, s);
      getPermutation(permutation, total--);
      int ok = 1;
      for (int i = 0; i < n-1; i++) {
        for (int j = 0; j < i; j++) {
          Node nodeI = nodes[permutation[i]], nodeJ = nodes[permutation[j]];
          if (nodeJ.level < nodeI.level && nodeJ.lineage == (nodeI.lineage >> (nodeI.level-nodeJ.level))) {
            ok = 0;
            break;
          }
        }
        if (ok == 0) {
          break;
        }
      }
      if (ok == 1) {
        for (Integer i : permutation) {
          System.out.print(i + " ");
        }
        System.out.println(s);
      }
      ret += ok;
    }
    return ret;
  }

  public void init(int[] num, int k) {
    int j = 0;
    for (int i = 0; i < num.length+1; i++) {
      if (i != k) {
        num[j++] = i;
      }
    }
  }

  public void getPermutation(int[] num, int k) {
    int n = num.length, m = 1, j = n, count = 0;
    List<Integer> list = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      list.add(num[i]);
    }
    while (--j > 0) {
      m*=j;
    }
    k--;
    for (int i = 0; i < n-1; i++) {
      num[count++] = list.remove(k/m);
      k = k%m;
      m/=(n-i-1);
    }
    num[count] = list.get(0);
  }

  public static void main(String[] args) {
    OneEntrance o = new OneEntrance();
    System.out.println(
        o.count(new int[]{0, 1, 2}, new int[]{1, 2, 3}, 0));
    System.out.println(o.count(new int[]{7, 4, 1, 0, 1, 1, 6, 0}, new int[]{6, 6, 2, 5, 0, 3, 8, 4}, 4));
  }

}
