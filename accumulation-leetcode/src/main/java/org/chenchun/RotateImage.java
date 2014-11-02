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

public class RotateImage {
  public void rotate(int[][] matrix) {
    int length = matrix.length;
    for (int i = 0; i < length; i++) {
      for (int j = 0; j < length - i - 1; j++) {
        int y = length-1-i, x = length-1-j;
        int t = matrix[x][y];
        matrix[x][y] = matrix[i][j];
        matrix[i][j] = t;
      }
    }
    for (int i = 0; i < length; i++) {
      for (int j = 0; j < length/2; j++) {
        int t = matrix[length-j-1][i];
        matrix[length-j-1][i] = matrix[j][i];
        matrix[j][i] = t;
      }
    }
  }
}
