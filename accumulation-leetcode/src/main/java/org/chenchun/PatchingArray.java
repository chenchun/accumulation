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

import java.util.LinkedList;

public class PatchingArray {
  public int minPatches(int[] nums, int n) {
    if (n == 0) {
      return 0;
    }
    LinkedList<Long> list = new LinkedList<>();
    if (nums.length == 0 || nums[0] != 1) {
      list.add(1l);
    }
    for (int i = 0; i < nums.length; i++) {
      list.add((long)nums[i]);
    }
    long max = 1;
    int i = 1;
    while (max < n) {
      for (; i < list.size(); i++) {
        if (max+1 >= list.get(i)) {
          max += list.get(i);
        } else {
          break;
        }
      }
      if (max >= n) {
        break;
      }
      if (i < list.size()) {
        list.add(i, max+1);
      } else {
        list.add(max+1);
      }
      max = 2*max+1;
      i++;
    }
    return list.size() - nums.length;
  }

  public static void main(String[] args) {
    PatchingArray p = new PatchingArray();
    System.out.println(p.minPatches(new int[]{}, 7));
  }
}
