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

import java.util.Arrays;

/**
 *
 */
public class ThreeSumClosest {
  public int threeSumClosest(int[] num, int target) {
    int closest = Integer.MAX_VALUE;
    int ret = Integer.MAX_VALUE;
    if (num.length >= 3) {
      Arrays.sort(num);
      for (int i = 0; i < num.length; i++) {
        int sum = num[i] - target;
        int left = i+1, right = num.length-1;
        while (left < right) {
          int part = num[left] + num[right] + sum;
          if (part == 0) {
            return target;
          } else if (part < 0) {
            if (Math.abs(part) < closest) {
              ret = num[i] + num[left] + num[right];
              closest = Math.abs(part);
            }
            left++;
          } else {
            if (Math.abs(part) < closest) {
              ret = num[i] + num[left] + num[right];
              closest = Math.abs(part);
            }
            right--;
          }
        }
      }
    }
    return ret;
  }
}