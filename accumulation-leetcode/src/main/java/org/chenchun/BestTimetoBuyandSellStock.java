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

public class BestTimetoBuyandSellStock {
  public int maxProfit(int[] prices) {
    if (prices == null || prices.length <= 1) {
      return 0;
    }
    int l = prices.length;
    int[] min = new int[l], max = new int[prices.length];
    for (int i = 0; i < l; i++) {
      min[i] = i - 1 >= 0 ? Math.min(min[i - 1], prices[i]) : prices[i];
      max[l - 1 - i] = i - 1 >= 0 ? Math.max(max[l - i], prices[l - 1 - i]) : prices[l - 1 - i];
    }
    int ret = max[0] - min[0];
    for (int i = 1; i < l; i++) {
      ret = Math.max(max[i] - min[i], ret);
    }
    return ret;
  }
}
