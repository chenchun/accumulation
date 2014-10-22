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

/**
 *
 */
public class SearchInRotatedSortedArray {
  public int search(int[] A, int target) {
    if (A == null || A.length == 0) {
      return 0;
    }
    int left = 0, right = A.length - 1;
    while (left < right) {
      if (A[left] < A[right] && (target < A[left] || target > A[right])) {
        return -1;
      }
      if (left == right - 1) {
        return A[left] == target ? left : (A[right] == target ? right : -1);
      }
      int mid = (left + right) / 2;
      if (A[mid] == target) {
        return mid;
      }
      if (A[mid] > A[left]) {
        if (target == A[left]) {
          return left;
        } else if (target > A[left] && target < A[mid]) {
          left = left + 1;
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      }
      if (A[mid] < A[right]) {
        if (target == A[right]) {
          return right;
        } else if (target > A[mid] && target < A[right]) {
          left = mid + 1;
          right = right - 1;
        } else {
          right = mid - 1;
        }
      }
    }
    return left == right && A[left] == target ? left : -1;
  }
}
