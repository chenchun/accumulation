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
package org.chenchun.other;

// A B coints = {1, 5, 2, 2, 5, 10, 2, 5}
// 有一堆硬币，随机排列，A B 两人拿硬币，每人每次只能从头拿一枚或者从尾拿一枚，求先手的最大收益
public class MaxCoin {

  public int maxCoin(int[] coins) {
    if (coins == null) {
      return 0;
    }
    if (coins.length == 1) {
      return coins[0];
    }
    int len = coins.length;
    //dp[x][y] = {m, i}从第x个硬币到第y个硬币，最大收益是m，选择的第i个硬币
    int[][][] dp = new int[len][len][2];
    for (int i = 0; i < len; i++) {
      dp[i][i] = new int[]{coins[i], i};
    }
    for (int i = 1; i < len; i++) {
      if (coins[i-1] > coins[i]) {
        dp[i-1][i] = new int[]{coins[i-1], i-1};
      } else {
        dp[i-1][i] = new int[]{coins[i], i};
      }
    }
    for (int i = 2; i < len; i++) {
      for (int j = 0; i+j < len; j++) {
        //选择第j个硬币
        if (dp[j+1][i+j][1] == j+1) {
          //从j+1到i+j个硬币选择第j+1个硬币
          dp[j][i+j] = new int[]{coins[j] + dp[j+2][i+j][0], j};
        } else {
          //从j+1到i+j个硬币选择第i+j个硬币
          dp[j][i+j] = new int[]{coins[j] + dp[j+1][i+j-1][0], j};
        }
        //选择第i+j个硬币
        int[] temp = new int[] {coins[i+j], i+j};
        if (dp[j][i+j-1][1] == j) {
          //从j到i+j-1个硬币选择第j个硬币
          temp[0] += dp[j+1][i+j-1][0];
        } else {
          //从j到i+j-1个硬币选择第i+j-1个硬币
          temp[0] += dp[j][i+j-2][0];
        }
        if (temp[0] > dp[j][i+j][0]) {
          dp[j][i+j] = temp;
        }
      }
    }
    return dp[0][len-1][0];
  }

  public static void main(String[] args) {
    MaxCoin c = new MaxCoin();
    System.out.println(c.maxCoin(new int[]{5, 10, 2}));
    System.out.println(c.maxCoin(new int[]{5, 10, 2, 5}));
    System.out.println(c.maxCoin(new int[]{10, 20, 2, 1}));
    System.out.println(c.maxCoin(new int[]{1, 5, 2, 2, 5, 10, 2, 5}));
  }
}
