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

public class SearchForARange {
  public int[] searchRange(int[] A, int target) {
    int l = 0, r = A.length - 1, min = -1, max = -1, temp = A.length - 1;
    while (l <= r) {
      int mid = (l + r)/2;
      if (A[mid] == target) {
        if (min == -1) {
          min = mid;
          max = mid;
          temp = r;
        } else {
          min = Math.min(min, mid);
        }
        r = mid - 1;
      } else if (A[mid] > target) {
        r = mid - 1;
      } else {
        l = mid + 1;
      }
    }
    if (max != -1) {
      l = max + 1;
      r = temp;
      while (l <= r) {
        int mid = (l + r)/2;
        if (A[mid] == target) {
          max = Math.max(max, mid);
          l = mid + 1;
        } else if (A[mid] > target) {
          r = mid - 1;
        } else {
          l = mid + 1;
        }
      }
    }
    return new int[] {min, max};
  }
}
