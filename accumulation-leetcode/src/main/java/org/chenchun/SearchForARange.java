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

import java.util.LinkedList;
import java.util.Queue;

public class SearchForARange {
  public int[] searchRange(int[] A, int target) {
    if (A == null || A.length == 0) {
      return new int[]{-1, -1};
    }
    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[]{0, A.length-1});
    Integer begin = null, end = null;
    while (!queue.isEmpty()) {
      int[] arr = queue.poll();
      int left = arr[0], right = arr[1];
      while (left <= right) {
        int mid = (left+right)/2;
        if (A[mid] == target) {
          if (begin == null) {
            begin = mid;
            end = mid;
            queue.add(new int[]{mid+1, right});
            right = mid-1;
          } else {
            if (mid < begin) {
              begin = mid;
              right = mid-1;
            } else if (mid > end) {
              end = mid;
              left = mid+1;
            }
          }
        } else if (A[mid] > target) {
          right = mid-1;
        } else {
          left = mid+1;
        }
      }
    }
    return begin == null? new int[]{-1, -1} : new int[]{begin, end};
  }
}
