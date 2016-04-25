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
    return findKthLargest(nums, nums.length+1-k, 0, nums.length-1);
  }

  public int findKthLargest(int[] nums, int k, int start, int end) {
    if (start == end) {
      return nums[start];
    }
    int l = start, r = end;
    int p = (nums[l]+nums[r])/2;
    while (l < r) {
      while (l < r && nums[l] <= p) l++;
      while (nums[r] > p) r--;
      if (l < r) {
        int t = nums[l];
        nums[l] = nums[r];
        nums[r] = t;
      }
    }
    if (l-start >= k) {
      return findKthLargest(nums, k, start, l-1);
    } else {
      return findKthLargest(nums, k-l+start, l, end);
    }
  }
}
