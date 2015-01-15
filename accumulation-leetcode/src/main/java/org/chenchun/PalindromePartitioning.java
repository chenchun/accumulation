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

public class PalindromePartitioning {
  public List<List<String>> partition(String s) {
    List<List<String>> ret = new ArrayList<List<String>>();
    if (s == null || s.length() == 0) {
      return ret;
    }
    int l = s.length();
    boolean[][] dp = new boolean[l][l];
    for (int i = 0; i < l; i++) {
      dp[i][i] = true;
    }
    for (int i = 1; i < l; i++) {
      for (int j = 0; i+j < l; j++) {
        if (s.charAt(j) == s.charAt(j+i) && (j+1 >= j+i-1 || dp[j+1][j+i-1])) {
          dp[j][j+i] = true;
        }
      }
    }
    travel(dp, s, 0, new ArrayList<String>(), ret);
    return ret;
  }

  private void travel(boolean[][] dp, String s, int b, List<String> part, List<List<String>> ret) {
    if (b == s.length()) {
      ret.add(part);
    } else {
      for (int i = b; i < s.length(); i++) {
        if (dp[b][i]) {
          List<String> cloned = new ArrayList<>(part);
          cloned.add(s.substring(b, i+1));
          travel(dp, s, i+1, cloned, ret);
        }
      }
    }
  }
}
