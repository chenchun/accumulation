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
    int left = 0, right = num.length - 1;
    int min = num[0];
    while (left <= right) {
      if (left == right || num[left] < num[right]) {
        min = Math.min(num[left], min);
        break;
      }
      int mid = (left+right)/2;
      if (num[mid] < num[left]) {
        min = Math.min(num[mid], min);
        right = mid-1;
      } else if (num[mid] > num[left]) {
        left = mid+1;
      } else {
        left++;
      }
    }
    return min;
  }
}
