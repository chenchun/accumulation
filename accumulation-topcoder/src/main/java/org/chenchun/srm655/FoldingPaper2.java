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
package org.chenchun.srm655;

public class FoldingPaper2 {
  public int solve(int W, int H, int A) {
    if (A > W*H) {
      return -1;
    }
    int min = Math.min(W, H), max = Math.max(W, H);
    int minFolding = Integer.MAX_VALUE;
    for (int i = 1; i <= (A+1)/2; i++) {
      if (A % i == 0) {
        int minPossible = Math.min(i, A/i), maxPossible = Math.max(i, A/i);
        if (minPossible <= min && maxPossible <= max) {
          minFolding = Math.min(minFolding, solve(min, minPossible)+solve(max, maxPossible));
        }
      }
    }
    return minFolding == Integer.MAX_VALUE ? -1 : minFolding;
  }

  //s >= end
  private int solve(int s, int end) {
    if (s == end) {
      return 0;
    }
    int count = 0;
    while (true) {
      count++;
      int c = 0;
      if (s % 2 == 1) {
        s -= 1;
        c = 1;
      }
      if (s/2 + c <= end) {
        return count;
      } else {
        s = s/2 + c;
      }
    }
  }

  public static void main(String[] args) {
    FoldingPaper2 f = new FoldingPaper2();
    System.out.println(f.solve(5, 3, 12));
    System.out.println(f.solve(2, 2, 3));
    System.out.println(f.solve(4, 4, 1));
    System.out.println(f.solve(127, 129, 72));
    System.out.println(f.solve(1, 100000, 100000));
    System.out.println(f.solve(1, 1, 2));
  }

}
