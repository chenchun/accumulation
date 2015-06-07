/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.chenchun;

public class KthLargestElementinanArray {
  public int findKthLargest(int[] nums, int k) {
    int left = 0, right = nums.length-1;
    while (true) {
      int i = left, j = right;
      int pivot = nums[i++];
      while (i <= j) {
        while (nums[i] >= pivot && i < right) {
          i++;
        }
        while (nums[j] < pivot) {
          j--;
        }
        if (i <= j) {
          int t = nums[i];
          nums[i] = nums[j];
          nums[j] = t;
          i++;
          j--;
        }
      }
      if (i-left == k) {
        return pivot;
      } else if (i-left < k) {
        k = k - i + left;
        left = i;
      } else {
        right = i-1;
        left = left+1;
      }
    }
  }

  public static void main(String[] args) {
//    System.out.println(new KthLargestElementinanArray().findKthLargest(new int[]{2, 1, 4, 3, 5}, 1));
//    System.out.println(new KthLargestElementinanArray().findKthLargest(new int[]{2, 1, 4, 3, 5}, 2));
//    System.out.println(new KthLargestElementinanArray().findKthLargest(new int[]{2, 1, 4, 3, 5}, 3));
//    System.out.println(new KthLargestElementinanArray().findKthLargest(new int[]{2, 1, 4, 3, 5}, 4));
//    System.out.println(new KthLargestElementinanArray().findKthLargest(new int[]{2, 1, 4, 3, 5}, 5));
//    System.out.println(new KthLargestElementinanArray().findKthLargest(new int[]{2, 1}, 2));
    System.out.println(new KthLargestElementinanArray().findKthLargest(new
        int[]{1,1}, 2));
  }
}
