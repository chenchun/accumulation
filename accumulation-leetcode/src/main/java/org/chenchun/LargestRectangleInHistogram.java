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

import java.util.Stack;

public class LargestRectangleInHistogram {

  public int largestRectangleArea(int[] height) {
    if (height == null || height.length == 0) {
      return 0;
    }
    Stack<Integer> s = new Stack<Integer>();
    Stack<Integer> h = new Stack<Integer>();
    int max = height[0];
    for (int i = 0; i < height.length; i++) {
      if (s.isEmpty() || height[i] >= h.peek()) {
        s.push(i);
        h.push(height[i]);
      } else {
        int last = s.peek();
        while (!s.isEmpty() && h.peek() > height[i]) {
          Integer integer = s.pop();
          last = integer;
          Integer hp = h.pop();
          max = Math.max(max, (i - integer) * hp);
        }
        s.push(last);
        h.push(height[i]);
      }
    }
    while (!s.isEmpty()) {
      Integer integer = s.pop();
      Integer hp = h.pop();
      max = Math.max(max, (height.length-integer)*hp);
    }
    return max;
  }

  public static void main(String[] args) {
    LargestRectangleInHistogram l = new LargestRectangleInHistogram();
//    System.out.println(l.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
//    System.out.println(l.largestRectangleArea(new int[]{1}));
//    System.out.println(l.largestRectangleArea(new int[]{2, 1, 2}));
    System.out.println(l.largestRectangleArea(new int[]{4,2,0,3,2,5}));
  }
}
