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

public class LongestIncreasingPathinaMatrix {
  private int[][] m;
  private boolean[][] visited;
  private int[][] lens;

  public int longestIncreasingPath(int[][] matrix) {
    if (matrix == null || matrix.length == 0) {
      return 0;
    }
    m = matrix;
    visited = new boolean[matrix.length][matrix[0].length];
    lens = new int[matrix.length][matrix[0].length];
    int max = 0;
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (lens[i][j] == 0) {
          longestIncreasingPath(i, j);
        }
        max = Math.max(max, lens[i][j]);
      }
    }
    return max;
  }

  private int longestIncreasingPath(int i, int j) {
    visited[i][j] = true;
    if (i-1 >= 0 && m[i-1][j] > m[i][j] && !visited[i-1][j]) {
      lens[i][j] = Math.max(lens[i-1][j] == 0? longestIncreasingPath(i-1, j)+1 : lens[i-1][j]+1, lens[i][j]);
    }
    if (j-1 >= 0 && m[i][j-1] > m[i][j] && !visited[i][j-1]) {
      lens[i][j] = Math.max(lens[i][j-1] == 0? longestIncreasingPath(i, j-1)+1 : lens[i][j-1]+1, lens[i][j]);
    }
    if (i+1 < m.length && m[i+1][j] > m[i][j] && !visited[i+1][j]) {
      lens[i][j] = Math.max(lens[i+1][j] == 0? longestIncreasingPath(i+1, j)+1 : lens[i+1][j]+1, lens[i][j]);
    }
    if (j+1 < m[0].length && m[i][j+1] > m[i][j] && !visited[i][j+1]) {
      lens[i][j] = Math.max(lens[i][j+1] == 0? longestIncreasingPath(i, j+1)+1 : lens[i][j+1]+1, lens[i][j]);
    }
    lens[i][j] = Math.max(1, lens[i][j]);
    visited[i][j] = false;
    return lens[i][j];
  }

  public static void main(String[] args) {
    LongestIncreasingPathinaMatrix l = new LongestIncreasingPathinaMatrix();
    System.out.println(l.longestIncreasingPath(
      new int[][]{new int[]{9, 9, 4}, new int[]{6, 6, 8}, new int[]{2, 1, 1}}));
  }
}
