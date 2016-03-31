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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSum {
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    Set<List<Integer>> ret = new HashSet<List<Integer>>();
    Arrays.sort(candidates);
    combinationSum(candidates, target, new ArrayList<Integer>(), 0, ret);
    return new ArrayList<List<Integer>>(ret);
  }

  public boolean combinationSum(int[] candidates, int target, List<Integer> nums, int index, Set<List<Integer>> ret) {
    if (target == 0) {
      List<Integer> list = new ArrayList<Integer>();
      for (int i = 0; i < index; i++) {
        list.add(nums.get(i));
      }
      Collections.sort(list);
      ret.add(list);
      return true;
    } else if (target < 0) {
      return false;
    }
    for (int i = 0; i < candidates.length; i++) {
      if (nums.size() > index) {
        nums.set(index, candidates[i]);
      } else {
        nums.add(candidates[i]);
      }
      if (!combinationSum(candidates, target - candidates[i], nums, index+1, ret)) {
        if (i+1 < candidates.length && target - candidates[i+1] >= 0) {
          continue;
        }
        return false;
      }
    }
    return true;
  }
}
