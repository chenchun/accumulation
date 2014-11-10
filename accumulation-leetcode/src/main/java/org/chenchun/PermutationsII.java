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

public class PermutationsII {
  public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
    if (num == null) {
      return null;
    }
    Arrays.sort(num);
    ArrayList<Integer> nums = new ArrayList<Integer>();
    for (int i = 0; i < num.length; i++) {
      nums.add(num[i]);
    }
    HashSet<ArrayList<Integer>> ret = new HashSet<ArrayList<Integer>>();
    permuteUnique(nums, new ArrayList<Integer>(), ret);
    return new ArrayList<ArrayList<Integer>>(ret);
  }

  public void permuteUnique(ArrayList<Integer> nums, ArrayList<Integer> ret, HashSet<ArrayList<Integer>> results) {
    Integer last = null;
    for (int i = 0; i < nums.size(); i++) {
      Integer integer = nums.get(i);
      if (!integer.equals(last)) {
        ArrayList<Integer> numsCopy = (ArrayList<Integer>) nums.clone();
        numsCopy.remove(i);
        ArrayList<Integer> retCopy = (ArrayList<Integer>) ret.clone();
        retCopy.add(integer);
        permuteUnique(numsCopy, retCopy, results);
      }
      last = integer;
    }
    if (nums.size() == 0) {
      results.add(ret);
    }
  }
}
