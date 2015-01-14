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
import java.util.Set;

public class WordBreakII {
  public List<String> wordBreak(String s, Set<String> dict) {
    List<String> list = new ArrayList<>();
    if (s == null || s.length() == 0 || dict.size() == 0) {
      return list;
    }
    int l = s.length();
    List[] dp = new List[l+1];
    dp[0] = new ArrayList<Integer>();
    for (int i = 1; i <= l; i++) {
      for (int j = 0; j < i; j++) {
        if (dict.contains(s.substring(j, i)) && dp[j] != null) {
          if (dp[i] == null) {
            dp[i] = new ArrayList<Integer>();
          }
          dp[i].add(j);
        }
      }
    }
    if (dp[l] == null) {
      return list;
    }
    word(dp, l, "", s, list);
    return list;
  }

  private void word(List[] dp, int end, String part, String s, List<String> list) {
    if (end != 0) {
      List<Integer> indexs = (List<Integer>) dp[end];
      for (int i = 0; i < dp[end].size(); i++) {
        int b = indexs.get(i);
        word(dp, b, s.substring(b, end) + (part.isEmpty()? part : " "+part), s, list);
      }
    } else {
      list.add(part);
    }
  }
}
