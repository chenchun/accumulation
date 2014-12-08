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

public class ScrambleString {
  public boolean isScramble(String s1, String s2) {
    if (s1 == null || s2 == null || s1.length() != s2.length()) {
      return false;
    }
    int l = s1.length();
    boolean[][][] dp = new boolean[l][l][l+1];
    for (int i = 0; i < l; i++) {
      for (int j = 0; j < l; j++) {
        dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
      }
    }
    for (int len = 2; len <= l; len++) {
      for (int i = 0; i <= l-len; i++) {
        for (int j = 0; j <= l-len; j++) {
          for (int s = 1; s < len; s++) {
            if ((dp[i][j][s] && dp[i+s][j+s][len-s]) || (dp[i][j+len-s][s] && dp[i+s][j][len-s])) {
              dp[i][j][len] = true;
            }
          }
        }
      }
    }
    return dp[0][0][l];
  }
}
