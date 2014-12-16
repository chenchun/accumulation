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

public class EditDistance {
  //min[i, j] = min{min[i-1, j-1]+f(i, j), min[i-1, j]+1, min[i, j-1]+1}
  public int minDistance(String word1, String word2) {
    if (word1 == null || word2 == null) {
      return 0;
    }
    int l1 = word1.length(), l2 = word2.length();
    if (l1 == 0 || l2 == 0) {
      return l1+l2;
    }
    int[][] dp = new int[l1+1][l2+1];
    for (int i = 0; i <= l1; i++) {
      dp[i][0] = i;
    }
    for (int i = 0; i <= l2; i++) {
      dp[0][i] = i;
    }
    for (int i = 1; i <= l1; i++) {
      for (int j = 1; j <= l2; j++) {
        dp[i][j] = Math.min(dp[i-1][j-1]+(word1.charAt(i-1) == word2.charAt(j-1)? 0 : 1), dp[i-1][j]+1);
        dp[i][j] = Math.min(dp[i][j], dp[i][j-1]+1);
      }
    }
    return dp[l1][l2];
  }
}
