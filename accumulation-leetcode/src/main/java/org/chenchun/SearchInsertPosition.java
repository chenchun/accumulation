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

public class SearchInsertPosition {
  public int searchInsert(int[] A, int target) {
    if (A == null || A.length == 0) {
      return 0;
    }
    int left = 0, right = A.length-1;
    int begin = -1, end = -1;
    while (left <= right) {
      int mid = (left+right)/2;
      if (A[mid] == target) {
        return mid;
      } else if (A[mid] < target) {
        left = mid+1;
        begin = begin == -1 ? mid+1 : Math.max(begin, mid+1);
      } else {
        right = mid-1;
        end = end == -1? mid-1 : Math.min(end, mid-1);
      }
    }
    if (begin == -1 && end == -1) {
      return 0;
    }
    return begin == -1 ? end : begin;
  }
}
