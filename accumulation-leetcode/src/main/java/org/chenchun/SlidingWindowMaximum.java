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

import java.util.PriorityQueue;

public class SlidingWindowMaximum {
  private static class Node implements Comparable<Node> {
    int index;
    int n;
    public Node(int index, int n) {
      this.index = index;
      this.n = n;
    }
    public int compareTo(Node o) {
      return o.n - this.n;
    }
  }
  public int[] maxSlidingWindow(int[] nums, int k) {
    if (nums.length == 0) {
      return new int[0];
    }
    int[] ret = new int[nums.length-k+1];
    PriorityQueue<Node> queue = new PriorityQueue<>();
    for (int i = 0; i < k-1; i++) {
      queue.add(new Node(i, nums[i]));
    }
    for (int i = k-1; i < nums.length; i++) {
      queue.add(new Node(i, nums[i]));
      while (true) {
        Node n = queue.poll();
        if (n.index >= i+1-k) {
          ret[i+1-k] = n.n;
          if (n.index != i+1-k) {
            queue.add(n);
          }
          break;
        }
      }
    }
    return ret;
  }
}
