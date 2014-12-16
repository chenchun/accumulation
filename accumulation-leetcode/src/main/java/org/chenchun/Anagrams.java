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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Anagrams {
  public List<String> anagrams(String[] strs) {
    List<String> ret = new ArrayList<>();
    if (strs == null || strs.length <= 1) {
      return ret;
    }
    Map<String, List<String>> map = new HashMap<>();
    for (int i = 0; i < strs.length; i++) {
      String sorted = sort(strs[i]);
      if (!map.containsKey(sorted)) {
        map.put(sorted, new ArrayList<String>());
      }
      if (map.get(sorted).size() == 2) {
        ret.add(strs[i]);
      } else {
        map.get(sorted).add(strs[i]);
        if (map.get(sorted).size() == 2) {
          ret.addAll(map.get(sorted));
        }
      }
    }
    return ret;
  }

  private String sort(String s) {
    char[] arr = s.toCharArray();
    Arrays.sort(arr);
    return new String(arr);
  }
}
