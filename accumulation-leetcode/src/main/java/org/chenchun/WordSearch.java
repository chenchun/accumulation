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

public class WordSearch {
  public boolean exist(char[][] board, String word) {
    if (board == null || board.length == 0 || board[0].length == 0 || word.length() == 0) {
      return false;
    }
    boolean[][] visited = new boolean[board.length][board[0].length];
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (board[i][j] == word.charAt(0)) {
          visited[i][j] = true;
          if (exist(board, word, 1, visited, i, j)) {
            return true;
          }
          visited[i][j] = false;
        }
      }
    }
    return false;
  }

  public boolean exist(char[][] board, String word, int i, boolean[][] visited, int m, int n) {
    if (word.length() == i) {
      return true;
    }
    int h = board.length, w = board[0].length;
    if (n-1 >= 0 && !visited[m][n-1] && board[m][n-1] == word.charAt(i)) {
      visited[m][n-1] = true;
      if (exist(board, word, i+1, visited, m, n-1)) {
        return true;
      }
      visited[m][n-1] = false;
    }
    if (n+1 < w && !visited[m][n+1] && board[m][n+1] == word.charAt(i)) {
      visited[m][n+1] = true;
      if (exist(board, word, i+1, visited, m, n+1)) {
        return true;
      }
      visited[m][n+1] = false;
    }
    if (m-1 >= 0 && !visited[m-1][n] && board[m-1][n] == word.charAt(i)) {
      visited[m-1][n] = true;
      if (exist(board, word, i+1, visited, m-1, n)) {
        return true;
      }
      visited[m-1][n] = false;
    }
    if (m+1 < h && !visited[m+1][n] && board[m+1][n] == word.charAt(i)) {
      visited[m+1][n] = true;
      if (exist(board, word, i+1, visited, m+1, n)) {
        return true;
      }
      visited[m+1][n] = false;
    }
    return false;
  }
}
