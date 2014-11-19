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

public class SudokuSolver {
  public void solveSudoku(char[][] board) {
    solveSudoku(board, 0, 0);
  }

  private boolean solveSudoku(char[][] board, int x, int y) {
    if (x >= 9) {
      return true;
    }
    if (y >= 9) {
      return solveSudoku(board, x + 1, 0);
    }
    if (board[x][y] != '.') {
      return solveSudoku(board, x, y + 1);
    }
    for (int i = 1; i < 10; i++) {
      char c = (char) ('0' + i);
      if (isValid(board, x, y, c)) {
        board[x][y] = c;
        if (solveSudoku(board, x, y + 1)) {
          return true;
        }
        board[x][y] = '.';
      }
    }
    return false;
  }

  private boolean isValid(char[][] board, int x, int y, char c) {
    for (int i = 0; i < board.length; i++) {
      if (i != x) {
        if (board[i][y] == c) {
          return false;
        }
      }
    }
    for (int i = 0; i < board[0].length; i++) {
      if (i != y) {
        if (board[x][i] == c) {
          return false;
        }
      }
    }
    int ox = x / 3 * 3, oy = y / 3 * 3;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (ox + i != x || oy + j != y) {
          if (board[ox + i][oy + j] == c) {
            return false;
          }
        }
      }
    }
    return true;
  }
}
