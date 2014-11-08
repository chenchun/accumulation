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

public class PalindromePartitioningII {
  public int minCut(String s) {
    if (s == null || s.length() <= 1) {
      return 0;
    }
    boolean[][] dp = new boolean[s.length()][s.length()];
    for (int i = 0; i < s.length(); i++) {
      dp[i][i] = true;
    }
    int[] minCut = new int[s.length()];
    minCut[s.length()-1] = 0;
    for (int i = s.length()-2; i >= 0; i--) {
      minCut[i] = 1 + minCut[i+1];
      for (int j = 1; j+i < s.length(); j++) {
        if ((i+1 > i+j-1 || dp[i+1][i+j-1]) && s.charAt(i) == s.charAt(j+i)) {
          dp[i][i+j] = true;
          minCut[i] = Math.min(i+j+1>=s.length()? 0 : minCut[i+j+1] + 1,
              minCut[i]);
        }
      }
    }
    return minCut[0];
  }

  public static void main(String[] args) {
    PalindromePartitioningII p = new PalindromePartitioningII();
//    System.out.println(p.minCut("fifgbeajcacehiicccfecbfhhgfiiecdcjjffbghdidbhbdbfbfjccgbbdcjheccfbhafehieabbdfeigbiaggchaeghaijfbjhi"));
    System.out.println(p.minCut("bb"));
  }
}
