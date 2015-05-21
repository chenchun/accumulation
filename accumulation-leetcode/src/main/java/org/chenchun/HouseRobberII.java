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

public class HouseRobberII {
  public int rob(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    return Math.max(rob(nums, 1, nums.length), rob(nums, 2, nums.length-1)+nums[0]);
  }

  private int rob(int[] nums, int s, int e) {
    if (s >= nums.length || s >= e) {
      return 0;
    }
    int consist = nums[s], notConsist = 0, previousConsist = nums[s];
    for (int i = s+1; i < e; i++) {
      consist = notConsist + nums[i];
      notConsist = Math.max(previousConsist, notConsist);
      previousConsist = consist;
    }
    return Math.max(consist, notConsist);
  }
}
