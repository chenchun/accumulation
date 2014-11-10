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

public class LongestPalindromicSubstring {
  public String longestPalindrome(String s) {
    if (s == null || s.length() <= 1) {
      return s;
    }
    boolean[][] dp = new boolean[s.length()][s.length()];
    for (int i = 0; i < s.length(); i++) {
      dp[i][i] = true;
    }
    int b = 0, e = 0;
    for (int i = 1; i < s.length(); i++) {
      for (int j = 0; j + i < s.length(); j++) {
        if (s.charAt(j) == s.charAt(j+i) && (j+1 >= j+i-1 || dp[j+1][j+i-1])) {
          dp[j][j+i] = true;
          b = j;
          e = j+i;
        }
      }
    }
    return s.substring(b, e+1);
  }
  public static void main(String[] args) {
    LongestPalindromicSubstring l = new LongestPalindromicSubstring();
    System.out.println(l.longestPalindrome("abcbaabc"));

  }
}
