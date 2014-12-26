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
import java.util.List;

public class MaximumGap {
  public int maximumGap(int[] num) {
    if (num == null || num.length < 2) {
      return 0;
    }
    int mask = 1;
    List<Integer> zeroBucket = new ArrayList<>();
    List<Integer> oneBucket = new ArrayList<>();
    List<Integer> sorted = new ArrayList<>(num.length);
    for (int i = 0; i < num.length; i++) {
      sorted.add(num[i]);
    }
    while (mask > 0) {
      for (int i = 0; i < sorted.size(); i++) {
        int n = sorted.get(i);
        if ((n & mask) == mask) {
          oneBucket.add(n);
        } else {
          zeroBucket.add(n);
        }
      }
      sorted.clear();
      sorted.addAll(zeroBucket);
      sorted.addAll(oneBucket);
      zeroBucket.clear();
      oneBucket.clear();
      mask <<= 1;
    }
    int maxGap = 0;
    for (int i = 1; i < sorted.size(); i++) {
      maxGap = Math.max(sorted.get(i) - sorted.get(i-1), maxGap);
    }
    return maxGap;
  }
}
