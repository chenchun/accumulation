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

/**
 * O(n) time and O(n) space
 */
public class BestTimetoBuyandSellStockIII {
  public int maxProfit(int[] prices) {
    if (prices == null || prices.length <= 1) {
      return 0;
    }
    int l = prices.length;
    int[] historyProfit = new int[l];
    int lowest = prices[0], highest = prices[l-1];
    int maxProfit = 0;
    for (int i = 0; i < l; i++) {
      lowest = Math.min(lowest, prices[i]);
      historyProfit[i] = i > 0? Math.max(historyProfit[i-1], prices[i] - lowest) : 0;
    }
    for (int i = l-1; i >= 0; i--) {
      highest = Math.max(highest, prices[i]);
      maxProfit = Math.max(maxProfit, highest - prices[i] + (i - 1 > 0? historyProfit[i-1] : 0));
    }
    return maxProfit;
  }

  public static void main(String[] args) {
    BestTimetoBuyandSellStockIII b = new BestTimetoBuyandSellStockIII();
    System.out.println(b.maxProfit(new int[]{1,2,4,2,5,7,2,4,9,0}));
  }
}
