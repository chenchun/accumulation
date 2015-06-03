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

import java.util.Map;
import java.util.TreeMap;

public class ContainsDuplicateIII {
  public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    TreeMap<Integer, Integer> treeMap = new TreeMap<>();
    Map.Entry<Integer, Integer> floor, ceiling;
    for (int i=0; i<nums.length; i++) {
      while ((floor = treeMap.floorEntry(nums[i])) != null) {
        if (i - floor.getValue() > k) {
          treeMap.remove(floor.getKey());
        } else {
          if (Math.abs((long)floor.getKey() - nums[i]) <= t) {
            return true;
          }
          break;
        }
      }
      while ((ceiling = treeMap.ceilingEntry(nums[i])) != null) {
        if (i - ceiling.getValue() > k) {
          treeMap.remove(ceiling.getKey());
        } else {
          if (Math.abs((long)ceiling.getKey() - nums[i]) <= t) {
            return true;
          }
          break;
        }
      }
      treeMap.put(nums[i], i);
    }
    return false;
  }

  public static void main(String[] args) {
    ContainsDuplicateIII c = new ContainsDuplicateIII();
    System.out.println(
        c.containsNearbyAlmostDuplicate(new int[]{4, 1, 6, 3}, 3, 1));
  }
}
