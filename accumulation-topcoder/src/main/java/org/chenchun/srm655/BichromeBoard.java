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
package org.chenchun.srm655;

public class BichromeBoard {
  public static final char q = '?';
  public String ableToDraw(String[] board) {
    int r = board.length, c = board[0].length();
    char[][] arr = new char[r][];
    for (int i = 0; i < r; i++) {
      arr[i] = board[i].toCharArray();
    }
    int m = 0, n = 0;
    boolean allSame = true;
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        if (arr[i][j] != q) {
          allSame = false;
          m = i; n = j;
          break;
        }
        if (!allSame) {
          break;
        }
      }
    }
    if (allSame) {
      return "Possible";
    }
    for (int i = n-1; i >= 0; i--) {
      if (arr[m][i] != q) {
        arr[m][i] = color(arr[m][i+1]);
      }
    }
    for (int i = n+1; i < c; i++) {
      if (arr[m][i] != q) {
        arr[m][i] = color(arr[m][i-1]);
      }
    }
    for (int i = 0; i < c; i++) {
      for (int j = m-1; j >= 0; j--) {
        if (arr[j][i] != q) {
          arr[j][i] = color(arr[j+1][i]);
        }
      }
      for (int j = m+1; j < r; j++) {
        if (arr[j][i] != q) {
          arr[j][i] = color(arr[j-1][i]);
        }
      }
    }
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        if (!check(arr, i, j)) {
          return "Impossible";
        }
      }
    }
    return "Possible";
  }

  public boolean check(char[][] board, int i, int j) {
    int r = board.length, c = board[0].length;
    char s = board[i][j];
    if (i - 1 >= 0 && board[i-1][j] == s) {
      return false;
    }
    if (i + 1 < r && board[i+1][j] == s) {
      return false;
    }
    if (j - 1 >= 0 && board[i][j-1] == s) {
      return false;
    }
    if (j + 1 < c && board[i][j+1] == s) {
      return false;
    }
    return true;
  }

  public char color(char c) {
    if (c == 'W') {
      return 'B';
    }
    return 'W';
  }
}
