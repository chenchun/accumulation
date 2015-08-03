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

public class MajorityElementII {
  public List<Integer> majorityElement(int[] nums) {
    List<Integer> list = new ArrayList<>();
    if (nums.length == 0) {
      return list;
    }
    int candi1 = 0, candi2 = 0, n1 = 0, n2 = 0, i = -1;
    while (++i < nums.length) {
      if (n1 == 0 || candi1 == nums[i]) {
        n1++;
        candi1 = nums[i];
      } else if (n2 == 0 || candi2 == nums[i]) {
        n2++;
        candi2 = nums[i];
      } else {
        n1--;
        n2--;
      }
    }
    boolean cnt1 = n1 != 0, cnt2 = n2 != 0;
    n1 = 0;
    n2 = 0;
    for (i = 0; i < nums.length; i++) {
      if (cnt1 && nums[i] == candi1) {
        n1++;
      } else if (cnt2 && nums[i] == candi2) {
        n2++;
      }
    }
    if (n1 > nums.length/3) {
      list.add(candi1);
    }
    if (n2 > nums.length/3) {
      list.add(candi2);
    }
    return list;
  }
}
