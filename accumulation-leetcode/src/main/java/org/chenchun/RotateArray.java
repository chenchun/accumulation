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

public class RotateArray {
  public void rotate(int[] nums, int k) {
    if (nums == null || nums.length == 0 || k % nums.length == 0) {
      return;
    }
    int len = nums.length;
    rotate(nums, 0, len);
    rotate(nums, 0, k%len);
    rotate(nums, k%len, len);
  }

  public void rotate(int[] nums, int s, int e) {
    for (int i = s, j = e-1; i < j; i++, j--) {
      int t = nums[i];
      nums[i] = nums[j];
      nums[j] = t;
    }
  }
}
