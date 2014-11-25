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

public class InterleavingString {
  public boolean isInterleave(String s1, String s2, String s3) {
    int l1 = s1.length(), l2 = s2.length(), l3 = s3.length();
    if (l1 + l2 != l3) {
      return false;
    }
    if (l3 == 0) {
      return true;
    }
    boolean[][] dp = new boolean[l1+1][l2+1];
    for (int i = 1; i <= l1; i++) {
      if (s1.charAt(i-1) == s3.charAt(i-1)) {
        dp[i][0] = true;
      } else {
        break;
      }
    }
    for (int i = 1; i <= l2; i++) {
      if (s2.charAt(i-1) == s3.charAt(i-1)) {
        dp[0][i] = true;
      } else {
        break;
      }
    }
    for (int i = 2; i <= l3; i++) {
      for (int j = 0; j <= i; j++) {
        if (j-1 >= 0 && j-1 <=l1 && i-j <= l2 && dp[j-1][i-j]) {
          if (j-1 < l1 && s3.charAt(i-1) == s1.charAt(j-1)) {
            dp[j][i-j] = true;
          }
          if (i-j < l2 && s3.charAt(i-1) == s2.charAt(i-j)) {
            dp[j-1][i-j+1] = true;
          }
        }
        if (j <= l1 && i-j-1 >= 0 && i-j-1 <=l2 && dp[j][i-j-1]) {
          if (j < l1 && s3.charAt(i-1) == s1.charAt(j)) {
            dp[j+1][i-j-1] = true;
          }
          if (i-j-1 < l2 && s3.charAt(i-1) == s2.charAt(i-j-1)) {
            dp[j][i-j] = true;
          }
        }
      }
    }
    return dp[l1][l2];
  }
}
