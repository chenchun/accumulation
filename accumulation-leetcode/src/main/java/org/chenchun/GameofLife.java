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

public class GameofLife {
  public void gameOfLife(int[][] board) {
    int r = board.length, c = board[0].length;
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        int live = 0;
        if (i-1 >= 0) {
          live += current(board[i-1][j]);
          if (j-1 >= 0) {
            live += current(board[i-1][j-1]);
          }
          if (j+1 < c) {
            live += current(board[i-1][j+1]);
          }
        }
        if (i+1 < r) {
          live += current(board[i+1][j]);
          if (j-1 >= 0) {
            live += current(board[i+1][j-1]);
          }
          if (j+1 < c) {
            live += current(board[i+1][j+1]);
          }
        }
        if (j-1 >= 0) {
          live += current(board[i][j-1]);
        }
        if (j+1 < c) {
          live += current(board[i][j+1]);
        }
        if ((board[i][j] == 1 && live >=2 && live <= 3)
          || (board[i][j] == 0 && live == 3)) {
          board[i][j] = becomeLive(board[i][j]);
        }
      }
    }
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        board[i][j] = next(board[i][j]);
      }
    }
  }

  private int current(int i) {
    return i & 1;
  }

  private int next(int i) {
    return i >> 1;
  }

  private int becomeLive(int i) {
    return i | 2;
  }
}
