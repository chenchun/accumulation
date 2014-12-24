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

public class FirstMissingPositive {
  public int firstMissingPositive(int[] A) {
    for (int i = 0; i < A.length; i++) {
      if (A[i] > 0 && A[i] <= A.length && A[i] != i+1) {
        int t = A[A[i]-1];
        if (t != A[i]) {
          A[A[i]-1] = A[i];
          A[i] = t;
          i--;
        }
      }
    }
    for (int i = 0; i < A.length; i++) {
      if (A[i] != i+1) {
        return i+1;
      }
    }
    return A.length+1;
  }
}
