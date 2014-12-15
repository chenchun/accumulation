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

public class Combinations {
  public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> ret = new ArrayList<>();
    if (k <= 0 || n < k) {
      return ret;
    }
    combine(n, k, 1, new ArrayList<Integer>(), ret);
    return ret;
  }

  private void combine(int n, int k, int i, List<Integer> sub, List<List<Integer>> ret) {
    if (k == 0) {
      ret.add(sub);
    } else {
      if (i <= n-k) {
        List<Integer> list = new ArrayList<>(sub);
        combine(n, k, i+1, list, ret);
      }
      sub.add(i);
      combine(n, k-1, i+1, sub, ret);
    }
  }
}
