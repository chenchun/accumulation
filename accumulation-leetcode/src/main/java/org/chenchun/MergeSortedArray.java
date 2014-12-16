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

public class MergeSortedArray {
  public void merge(int A[], int m, int B[], int n) {
    int p = m+n-1, i = m-1, j = n-1;
    while (p >= 0) {
      if (i < 0) {
        A[p] = B[j];
        j--;
      } else if (j < 0) {
        A[p] = A[i];
        i--;
      } else {
        if (A[i] > B[j]) {
          A[p] = A[i];
          i--;
        } else {
          A[p] = B[j];
          j--;
        }
      }
      p--;
    }
  }
}
