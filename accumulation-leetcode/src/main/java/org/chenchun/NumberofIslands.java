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

public class NumberofIslands {
  public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) {
      return 0;
    }
    int r = grid.length, c = grid[0].length;
    int count = 0;
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        if (grid[i][j] == '1') {
          visitIslands(grid, i, j);
          count++;
        }
      }
    }
    return count;
  }

  public void visitIslands(char[][] grid, int r, int c) {
    grid[r][c] = '2';
    if (r-1 >= 0 && grid[r-1][c] == '1') {
      visitIslands(grid, r-1, c);
    }
    if (r+1 < grid.length && grid[r+1][c] == '1') {
      visitIslands(grid, r+1, c);
    }
    if (c-1 >= 0 && grid[r][c-1] == '1') {
      visitIslands(grid, r, c-1);
    }
    if (c+1 < grid[0].length && grid[r][c+1] == '1') {
      visitIslands(grid, r, c+1);
    }
  }
}
