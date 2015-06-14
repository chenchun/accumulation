/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.chenchun;

public class MaximalSquare {
  public int maximalSquare(char[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return 0;
    }
    int r = matrix.length, c = matrix[0].length;
    int[][] dp = new int[r][c];
    int ret = 0;
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        dp[i][j] = matrix[i][j] == '0' ? 0 : 1;
        ret = Math.max(ret, dp[i][j]);
      }
    }
    int max = Math.max(r, c);
    for (int l = 2; l <= max; l++) {
      for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
          if (dp[i][j] == l-1
              && i+1 < r && dp[i+1][j] == l-1
              && j+1 < c && dp[i][j+1] == l-1
              && dp[i+1][j+1] == l-1) {
            dp[i][j] = l;
            ret = Math.max(ret, l);
          }
        }
      }
    }
    return ret*ret;
  }
}
