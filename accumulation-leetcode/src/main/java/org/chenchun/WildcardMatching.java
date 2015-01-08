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
    if (s == null || p == null) {
      return false;
    }
    int is = 0, ip = 0;
    int star = -1, ss = -1;
    while (is < s.length()) {
      char cs = s.charAt(is);
      if (ip < p.length() && p.charAt(ip) == '*') {
        star = ++ip;
        ss = is;
      } else if (ip < p.length() && (p.charAt(ip) == '?' || p.charAt(ip) == cs)) {
        ip++;
        is++;
      } else {
        if (star != -1) {
          is = ++ss;
          ip = star;
        } else {
          return false;
        }
      }
    }
    while (ip < p.length() && p.charAt(ip) == '*') {
      ip++;
    }
    return ip == p.length() && is == s.length();
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
