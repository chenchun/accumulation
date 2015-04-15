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
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class WordBreakII {
  public List<String> wordBreak(String s, Set<String> wordDict) {
    int len = s.length();
    List<Integer>[] dp = new List[len+1];
    dp[0] = Collections.emptyList();
    for (int i = 1; i <= len; i++) {
      for (int j = 0; j < i; j++) {
        if (dp[j] != null && wordDict.contains(s.substring(j, i))) {
          if (dp[i] == null) {
            dp[i] = new ArrayList<>();
          }
          dp[i].add(j);
        }
      }
    }
    List<String> list = new ArrayList<>();
    if (dp[len] == null) {
      return list;
    }
    dfs(dp, list, s, null, len);
    return list;
  }

  private void dfs(List<Integer>[] dp, List<String> ret, String s, String part, int index) {
    if (index == 0) {
      ret.add(part);
      return;
    }
    part = part == null? "" : " " + part;
    for (int i = 0; i < dp[index].size(); i++) {
      int start = dp[index].get(i);
      dfs(dp, ret, s, s.substring(start, index) + part, start);
    }
  }
}
