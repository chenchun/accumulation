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

public class SetMatrixZeroes {
  public void setZeroes(int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return;
    }
    boolean firstRow = false, firstCol = false;
    for (int i = 0; i < matrix.length; i++) {
      if (matrix[i][0] == 0) {
        firstCol = true;
      }
    }
    for (int i = 0; i < matrix[0].length; i++) {
      if (matrix[0][i] == 0) {
        firstRow = true;
      }
    }
    for (int i = 1; i < matrix.length; i++) {
      for (int j = 1; j < matrix[0].length; j++) {
        if (matrix[i][j] == 0) {
          matrix[0][j] = 0;
          matrix[i][0] = 0;
        }
      }
    }
    for (int i = 1; i < matrix.length; i++) {
      if (matrix[i][0] == 0) {
        for (int j = 1; j < matrix[0].length; j++) {
          matrix[i][j] = 0;
        }
      }
    }
    for (int i = 1; i < matrix[0].length; i++) {
      if (matrix[0][i] == 0) {
        for (int j = 1; j < matrix.length; j++) {
          matrix[j][i] = 0;
        }
      }
    }
    for (int i = 0; i < matrix.length; i++) {
      if (firstCol) {
        matrix[i][0] = 0;
      }
    }
    for (int i = 0; i < matrix[0].length; i++) {
      if (firstRow) {
        matrix[0][i] = 0;
      }
    }
  }
}
