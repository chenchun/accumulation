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
import java.util.HashMap;
import java.util.List;

public class SubstringWithConcatenationOfAllWords {
  public List<Integer> findSubstring(String S, String[] L) {
    List<Integer> list = new ArrayList<Integer>();
    if (L == null || S == null || L.length == 0 || L[0].length()*L.length > S.length()) {
      return list;
    }
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    for (String s : L) {
      if (!map.containsKey(s)) {
        map.put(s, 0);
      }
      map.put(s, map.get(s)+1);
    }
    int l = L[0].length();
    for (int i = 0; i <= S.length() - L.length*l; i++) {
      HashMap<String, Integer> m = (HashMap<String, Integer>) map.clone();
      int j = i;
      while (m.size()>0) {
        String s = S.substring(j, j+l);
        if (m.containsKey(s)) {
          m.put(s, m.get(s)-1);
          if (m.get(s) == 0) {
            m.remove(s);
          }
          j+=l;
        } else {
          break;
        }
      }
      if (m.size() == 0) {
        list.add(i);
      }
    }
    return list;
  }
}
