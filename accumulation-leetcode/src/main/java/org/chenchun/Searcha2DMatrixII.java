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

public class Searcha2DMatrixII {
  //O(m+n) < O(mlogn)???
  public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length < 1 || matrix[0].length <1) {
      return false;
    }
    int r = 0, c = matrix[0].length-1;
    while (r < matrix.length && c >= 0) {
      if (matrix[r][c] == target) {
        return true;
      } else if (matrix[r][c] < target) {
        r++;
      } else {
        c--;
      }
    }
    return false;
  }
  //T(mn) = T(1/2mn)+T(1/4mn)
}
