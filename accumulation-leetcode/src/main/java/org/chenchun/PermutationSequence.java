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
import java.util.List;

public class PermutationSequence {
  public String getPermutation(int n, int k) {
    int m = 1, j = n;
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      list.add(i+1);
    }
    while (--j > 0) {
      m*=j;
    }
    k--;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n-1; i++) {
      sb.append(list.remove(k/m));
      k = k%m;
      m/=(n-i-1);
    }
    return sb.append(list.get(0)).toString();
  }
}
