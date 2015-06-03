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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSumIII {
  public List<List<Integer>> combinationSum3(int k, int n) {
    Set<List<Integer>> set = new HashSet<>();
    combination(k, n, 0, new HashSet<Integer>(), set);
    return new ArrayList<List<Integer>>(set);
  }

  public void combination(int k, int n, int last, Set<Integer> part, Set<List<Integer>> set) {
    if (k == 1) {
      if (n <= 9) {
        part.add(n);
        set.add(new ArrayList<Integer>(part));
      }
    } else {
      double upper = Math.min(n*1.0/k, 9);
      for (int i = last+1; i < upper; i++) {
        Set<Integer> newPart = new HashSet<>(part);
        newPart.add(i);
        combination(k-1, n-i, i, newPart, set);
      }
    }
  }
}
