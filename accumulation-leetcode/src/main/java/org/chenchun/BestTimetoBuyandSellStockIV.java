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

public class BestTimetoBuyandSellStockIV {
  public int maxProfit(int k, int[] prices) {
    if (k <= 0 || prices == null || prices.length <= 1) {
      return 0;
    }
    int len = prices.length;
    int gap = 0, buy = prices[0], sale = prices[0], profits = 0, lastBuyIndex = -1, buyIndex = 0;
    for (int j = 1; j < len; j++) {
      if (prices[j] < buy) {
        buyIndex = j;
        buy = prices[j];
        sale = buy;
      } else if (prices[j] > sale) {
        if (buyIndex != lastBuyIndex) {
          gap++;
          lastBuyIndex = buyIndex;
        }
        profits = profits-sale+prices[j];
        sale = prices[j];
        buy = sale;
      }
    }
    if (k >= gap) {
      return profits;
    }
    int[][] profit = new int[k][len];
    for (int i = 0; i < k; i++) {
      profit[i][0] = 0;
      int t = -prices[0];
      for (int j = 1; j < len; j++) {
        profit[i][j] = Math.max(profit[i][j-1], t+prices[j]);
        t = Math.max(t, (i > 0 ? profit[i-1][j-1] : 0)-prices[j]);
      }
    }
    return profit[k-1][len-1];
  }

  public static void main(String[] args) {
    BestTimetoBuyandSellStockIV b = new BestTimetoBuyandSellStockIV();
    System.out.println(b.maxProfit(13, new int[]{3, 4, 1, 6, 3, 8, 0, 4}));
//    System.out.println(b.maxProfit(1, new int[]{3, 4}));

  }

}
