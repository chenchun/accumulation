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
import java.util.Map;

public class SubstringWithConcatenationOfAllWords {
  public List<Integer> findSubstring(String S, String[] L) {
    List<Integer> list = new ArrayList<>();
    if (S != null && S.length() != 0 && L != null && L.length != 0) {
      Map<String, Integer> map = new HashMap<>();
      for (int i = 0; i < L.length; i++) {
        String str = L[i];
        if (!map.containsKey(str)) {
          map.put(str, 0);
        }
        map.put(str, map.get(str)+1);
      }
      int l1 = S.length(), l2 = L[0].length(), l3 = l1-l2*L.length;
      for (int i = 0; i <= l3; i++) {
        int j = i;
        Map<String, Integer> subMap = new HashMap<>();
        while (j+l2 <= l1 && j-i < l2*L.length) {
          String word = S.substring(j, j+l2);
          if (!map.containsKey(word)) {
            break;
          } else {
            if (!subMap.containsKey(word)) {
              subMap.put(word, 0);
            }
            subMap.put(word, subMap.get(word)+1);
            if (map.get(word) < subMap.get(word)) {
              break;
            }
          }
          j+=l2;
        }
        if (map.equals(subMap)) {
          list.add(i);
        }
      }
    }
    return list;
  }

  public static void main(String[] args) {
    SubstringWithConcatenationOfAllWords s = new SubstringWithConcatenationOfAllWords();
    s.findSubstring("aaa", new String[] {"a", "a"});
  }
}
