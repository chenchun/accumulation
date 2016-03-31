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

public class FindtheDuplicateNumber {
  public int findDuplicate(int[] nums) {
    //binary search 1-n, starting n/2
    //if more than twice bigger than n/2, search n3/4 next time
    int start = 1, end = nums.length - 1;
    while (start < end) {
      double mid = (start+end)/2.0;
      int leftCount = 0, rightCount = 0, midCount = 0;
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] < mid && nums[i] >= start) {
          leftCount++;
        } else if (nums[i] > mid && nums[i] <= end) {
          rightCount++;
        } else if (nums[i] == mid) {
          midCount++;
          if (midCount > 1) {
            return (start+end)/2;
          }
        }
      }
      if (leftCount < rightCount) {
        start = (start+end+1)/2;
      } else {
        end = (start+end-1)/2;
      }
    }
    return start;
  }

  public static void main(String[] args) {
    FindtheDuplicateNumber f = new FindtheDuplicateNumber();
    System.out.println(f.findDuplicate(new int[]{2, 2, 2, 3, 4}));
    System.out.println(f.findDuplicate(new int[]{1, 2, 3, 4, 3}));
    System.out.println(f.findDuplicate(new int[]{1, 2, 3, 4, 4}));
    System.out.println(f.findDuplicate(new int[]{3,2,5,14,5,5,19,18,11,10,1,4,5,5,5,5,12,5,17,5}));
  }
}
