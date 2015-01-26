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


//Rabin-Karp
//brute-force
public class StrStrKMP {
  public int strStr(String haystack, String needle) {
    if (haystack == null || needle == null || haystack.length() < needle.length()) {
      return -1;
    }
    if (needle.length() == 0) {
      return 0;
    }
    //KMP algorithms
    int i = 0;
    int j = 0;
    int sLen = haystack.length();
    int pLen = needle.length();
    int[] next = makeNext(needle.toCharArray());
    while (i < sLen && j < pLen) {
      //①如果j = -1，或者当前字符匹配成功（即S[i] == P[j]），都令i++，j++
      if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
        i++;
        j++;
      } else {
        //②如果j != -1，且当前字符匹配失败（即S[i] != P[j]），则令 i 不变，j = next[j]
        //next[j]即为j所对应的next值
        j = next[j];
      }
    }
    if (j == pLen)
      return i - j;
    else
      return -1;
  }

  public int[] makeNext(char[] p){
    int len = p.length;
    int[] next = new int[len];

    next[0] = -1;
    int j = -1;
    int i = 0;
    while (i < len - 1)
    {
      //p[j]表示前缀，p[i]表示后缀
      if (j == -1 || p[i] == p[j]) {
        i++;
        j++;
        next[i] = j;
      } else {
        j = next[j];
      }
    }
    return next;
  }

  public static void main(String[] args) {
    StrStrKMP s = new StrStrKMP();
    System.out.println(s.strStr("ABCDABCE", "ABCE"));
    System.out.println(s.strStr("mississippi", "issip"));
  }
}
