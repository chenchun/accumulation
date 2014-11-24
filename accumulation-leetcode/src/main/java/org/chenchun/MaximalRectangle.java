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

public class MaximalRectangle {
  public int maximalRectangle(char[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return 0;
    }
    int max = 0;
    int[][] recs = new int[matrix.length][matrix[0].length];
    for (int i = 0; i < matrix.length; i++) {
      int[] rec = new int[matrix[0].length];
      for (int j = 0; j < matrix[0].length; j++) {
        rec[j] = i == 0? (matrix[0][j] == '1'? 1 : 0) : (matrix[i][j] == '1'? recs[i-1][j]+1 : 0);
      }
      recs[i] = rec;
      max = Math.max(largestRectangleArea(rec), max);
    }
    return max;
  }

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
}
