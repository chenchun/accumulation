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
import java.util.Comparator;

public class LargestNumber {
  public String largestNumber(int[] num) {
    if (num == null || num.length == 0) {
      return "";
    }
    String[] nums = new String[num.length];
    for (int i = 0; i < num.length; i++) {
      nums[i] = String.valueOf(num[i]);
    }
    Arrays.sort(nums, new Comparator<String>() {
      public int compare(String s1, String s2) {
        String str1 = s1 + s2, str2 = s2 + s1;
        return str1.compareTo(str2);
      }
    });
    StringBuilder sb = new StringBuilder();
    for (int i = nums.length-1; i >= 0; i--) {
      sb.append(nums[i]);
    }
    while (sb.indexOf("0") == 0 && sb.length() > 1) {
      sb.deleteCharAt(0);
    }
    return sb.toString();
  }
}
