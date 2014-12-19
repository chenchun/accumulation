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

public class WildcardMatching {
  public boolean isMatch(String s, String p) {
    if (s == null || p == null || (p.length() == 0 && s.length() != 0)) {
      return false;
    }
    if (p.length() == 0 && s.length() == 0) {
      return true;
    }
    boolean dp[][] = new boolean[s.length()][p.length()];
    int j = p.length()-1, i = s.length()-1;
    while (j >= 0) {
      if (i < 0) {
        break;
      }
      char pj = p.charAt(j), si = s.charAt(i);
      if (pj == '*') {
        boolean match = i == s.length()-1;
        for (int k = i+1; k < s.length(); k++) {
          if (dp[k][j+1]) {
            match = true;
            break;
          }
        }
        if (match) {
          for (int k = 0; k <= i; k++) {
            dp[k][j] = true;
          }
        }
      }
      if (pj == '?' || si == pj) {
        if (j == p.length()-1 || i == s.length()-1) {
          dp[i][j] = true;
        } else if (dp[i+1][j+1]) {
          dp[i][j] = true;
        }
      }
      i--;
      j--;
    }
    if (j >= 0) {
      boolean match = false;
      for (int k = 0; k < s.length(); k++) {
        if (dp[k][j+1]) {
          match = true;
        }
      }
      if (!match) {
        return false;
      }
      while (j >= 0) {
        if (p.charAt(j) != '*') {
          return false;
        }
        j--;
      }
      return true;
    }
    return dp[0][0];
  }

  public static void main(String[] args) {
    WildcardMatching w = new WildcardMatching();
    System.out.println(w.isMatch("ab", "*"));
    System.out.println(w.isMatch("aa", "aaa"));
    System.out.println(w.isMatch("aa", "?*"));
    System.out.println(w.isMatch("aaa", "aa"));
    System.out.println(w.isMatch("aa", "a*"));
    System.out.println(w.isMatch("aa", "a??"));
    System.out.println(w.isMatch("aa", "a?"));
    System.out.println(w.isMatch("aab", "c*a*b"));
    System.out.println(w.isMatch("", ""));
    System.out.println(w.isMatch
        ("abbbaaaaaaaabbbabaaabbabbbaabaabbbbaabaabbabaabbabbaabbbaabaabbabaabaabbbbaabbbaabaaababbbbabaaababbaaa", "ab**b*bb*ab**ab***b*abaa**b*a*aaa**bba*aa*a*abb*a*a"));
  }
}
