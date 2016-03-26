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
package org.chenchun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MinimumHeightTrees {
  public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    if (n == 1) {
      return Arrays.asList(0);
    }
    HashSet[] map = new HashSet[n];
    for (int i = 0; i < edges.length; i++) {
      int from = edges[i][0], to = edges[i][1];
      if (map[from] == null) {
        map[from] = new HashSet<Integer>();
      }
      if (map[to] == null) {
        map[to] = new HashSet<Integer>();
      }
      map[from].add(to);
      map[to].add(from);
    }
    LinkedList<Integer> queue = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      if (map[i].size() == 1) {
        queue.add(i);
      }
    }
    while (true) {
      LinkedList<Integer> next = new LinkedList<>();
      for (Integer i : queue) {
        for (Integer child : (HashSet<Integer>) map[i]) {
          map[child].remove(i);
          if (map[child].size() == 1) {
            next.add(child);
          }
        }
      }
      if (next.size() == 0) {
        return new ArrayList<>(queue);
      }
      queue = next;
    }
  }

  public static void main(String[] args) {
    MinimumHeightTrees m = new MinimumHeightTrees();
//    System.out.println(m.findMinHeightTrees(2, new int[][]{new int[]{0, 1}}));
//    System.out.println(m.findMinHeightTrees(6,
//      new int[][]{new int[]{0, 3}, new int[]{1, 3}, new int[]{2, 3}, new int[]{4, 3}, new int[]{5, 4}}));
    System.out.println(m.findMinHeightTrees(4, new int[][]{new int[]{0, 1}, new int[]{1, 3}, new int[]{2, 1}}));
//    System.out.println(m.findMinHeightTrees(7, new int[][]{new int[]{0, 1}, new int[]{1, 2}, new int[]{1, 3}, new int[]{4, 2}, new int[]{3, 5}, new int[]{4, 6}}));
  }
}
