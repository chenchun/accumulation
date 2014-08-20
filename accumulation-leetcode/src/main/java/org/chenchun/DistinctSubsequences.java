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

public class DistinctSubsequences {
  public int numDistinct(String S, String T) {
    if (S == null || T == null || S.length() < T.length()) {
      return 0;
    }
    // dp[i][j] 表示S的前i个字符中包含T的前j个字符的次数
    // if (S[i-1] == T[j-1]) dp[i][j] = dp[i-1][j] /*不使用S[i-1]*/ + dp[i-1][j-1]
    // else dp[i][j] = dp[i-1][j]
    int[][] dp = new int[S.length()+1][T.length()+1];
    dp[0][0] = 1;
    for (int i = 0; i <= S.length(); i++) {
      dp[i][0] = 1;
    }
    for (int i = 1; i <= S.length(); i++) {
      for (int j = 1; j <= T.length() && j <= i; j++) {
        if (S.charAt(i-1) == T.charAt(j-1)) {
          dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
        } else {
          dp[i][j] = dp[i-1][j];
        }
      }
    }
    return dp[S.length()][T.length()];
  }

  public static void main(String[] args) {
    DistinctSubsequences d = new DistinctSubsequences();
    System.out.println(d.numDistinct("d", "d"));
    System.out.println(d.numDistinct("dd", "d"));
    System.out.println(d.numDistinct("ddd", "dd"));
  }
}
