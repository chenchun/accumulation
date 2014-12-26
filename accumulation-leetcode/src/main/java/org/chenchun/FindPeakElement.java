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

public class FindPeakElement {
  public int findPeakElement(int[] num) {
    int left = 0;
    int right = num.length;
    while (right - left > 2) {
      int mid = (right+left)/2;
      if (num[mid] > num[mid-1]) {
        if (num[mid] > num[mid+1]) {
          return mid;
        } else {
          left = mid+1;
        }
      } else {
        right = mid;
      }
    }
    if (right - left == 2) {
      return num[left] > num[left+1]? left : left+1;
    }
    return left;
  }
}
