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

public class MinimumSizeSubarraySum {
  public int minSubArrayLen(int s, int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    int len = nums.length;
    int part = nums[0], start = 0, min = nums[0] >= s ? 1 : Integer.MAX_VALUE;
    for (int i = 1; i < len; i++) {
      part += nums[i];
      while (part - nums[start] >= s) {
        part -= nums[start];
        start++;
      }
      if (part >= s) {
        min = Math.min(i-start+1, min);
      }
    }
    return min == Integer.MAX_VALUE? 0 : min;
  }
}
