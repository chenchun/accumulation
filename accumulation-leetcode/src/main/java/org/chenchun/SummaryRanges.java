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

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {
  public List<String> summaryRanges(int[] nums) {
    List<String> list = new ArrayList<>();
    int i = 0;
    while (i < nums.length) {
      int j = 1, b = i;
      boolean add = false;
      while (i+j < nums.length) {
        if (nums[i+j]-nums[i] == j) {
          i = i+j;
          if (i+(j<<1) < nums.length) {
            j <<= 1;
          } else {
            j = 1;
          }
        } else {
          add = true;
          if (j != 1) {
            j = 1;
          } else {
            i = i+j;
            if (b != i-1) {
              list.add(nums[b] + "->" + nums[i-1]);
            } else {
              list.add(String.valueOf(nums[b]));
            }
            break;
          }
        }
      }
      if (!add) {
        if (b != i) {
          list.add(nums[b] + "->" + nums[i]);
        } else {
          list.add(String.valueOf(nums[b]));
        }
        i++;
      }
    }
    return list;
  }
  public static void main(String[] args) {
    SummaryRanges s = new SummaryRanges();
    System.out.println(s.summaryRanges(new int[]{0, 1, 2, 4, 5, 7}));
    System.out.println(s.summaryRanges(new int[]{-1, 1, 2}));
  }
}
