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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
public class FourSum {
  public List<List<Integer>> fourSum(int[] num, int target) {
    Set<List<Integer>> ret = new HashSet<List<Integer>>();
    if (num.length >= 4) {
      Arrays.sort(num);
      for (int i = 0; i < num.length; i++) {
        for (int j = i + 1; j < num.length; j++) {
          int sum = target - (num[i] + num[j]);
          int left = j+1, right = num.length-1;
          while (left < right) {
            int part = num[left] + num[right];
            if (part == sum) {
              List<Integer> arr = new ArrayList<>();
              arr.add(num[i]);
              arr.add(num[j]);
              arr.add(num[left]);
              arr.add(num[right]);
              ret.add(arr);
              left++;
              right--;
            } else if (part < sum) {
              left++;
            } else {
              right--;
            }
          }
        }
      }
    }
    return new ArrayList<>(ret);
  }

  public static void main(String[] args) {
    FourSum f = new FourSum();
    List<List<Integer>> lists = f.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
    Util.print(lists);
  }
}
