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

public class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        int r = obstacleGrid.length, c = obstacleGrid[0].length;
        int dp[][] = new int[obstacleGrid.length][obstacleGrid[0].length];
        for (int i = 0; i < r; i++) {
            if (i == 0) {
                dp[i][0] = obstacleGrid[0][0] == 0? 1 : 0;
            } else {
                dp[i][0] = dp[i-1][0] == 1 && obstacleGrid[i][0] == 0? 1 : 0;
            }
        }
        for (int i = 0; i < c; i++) {
            if (i == 0) {
                dp[0][i] = obstacleGrid[0][0] == 0? 1 : 0;
            } else {
                dp[0][i] = dp[0][i-1] == 1 && obstacleGrid[0][i] == 0? 1 : 0;
            }
        }
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[r-1][c-1];
    }
}
