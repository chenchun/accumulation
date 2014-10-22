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
public class FindMinimumInRotatedSortedArrayII {

  public int findMin(int[] num) {
    if (num == null || num.length == 0) {
      return 0;
    }
    return findMin(num, 0, num.length - 1);
  }

  public int findMin(int[] num, int left, int right) {
    if (left < right) {
      while (left < right) {
        if (num[left] < num[right]) {
          return num[left];
        }
        if (right == left + 1) {
          return Math.min(num[left], num[right]);
        }
        int mid = (left + right) / 2;
        if (num[mid] == num[left] && num[mid] == num[right]) {
          return Math.min(findMin(num, left, mid - 1), findMin(num, mid + 1, right));
        } else if (num[mid] < num[left]) {
          right = mid;
          left = left + 1;
        } else if (num[mid] >= num[right]) {
          left = mid + 1;
        } else {
          break;
        }
      }
    }
    return num[left];
  }
}
